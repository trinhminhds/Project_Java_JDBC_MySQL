package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import connector.MyConnector;
import entity.Student;

public class StudentDao {
    private List<Student> listStudents;

    public StudentDao() {

        this.listStudents = readListStudents();
        if (listStudents == null) {
            listStudents = new ArrayList<Student>();
        }
    }

    public List<Student> readListStudents() {

        List<Student> list = new ArrayList<Student>();
        MyConnector myConnector = new MyConnector();
        myConnector.initConnector();

        try {

            Statement statement = myConnector.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM SINHVIEN");

            while (resultSet.next()) {
                int id = resultSet.getInt("MASINHVIEN");
                String name = resultSet.getString("HOTEN");
                int age = resultSet.getInt("TUOI");
                String addr = resultSet.getString("DIACHI");
                Float gpa = resultSet.getFloat("GPA");

                Student student = new Student(id, name, age, addr, gpa);
                list.add(student);
            }

            myConnector.connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean add(Student student) {

        boolean flag = false;
        MyConnector myConnector = new MyConnector();
        myConnector.initConnector();

        try {
            PreparedStatement statement = myConnector.connection
                    .prepareStatement("INSERT INTO SINHVIEN(MASINHVIEN,HOTEN,TUOI,DIACHI,GPA) VALUES (?,?,?,?,?)");

            statement.setInt(1, student.getId());
            statement.setString(2, student.getName());
            statement.setInt(3, student.getAge());
            statement.setString(4, student.getAddress());
            statement.setFloat(5, student.getGpa());

            statement.executeUpdate();

            myConnector.connection.close();

            listStudents.add(student);

            flag = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean edit(Student student) {

        boolean flag = false;
        int size = listStudents.size();

        for (int i = 0; i < size; i++) {
            if (listStudents.get(i).getId() == student.getId()) {
                MyConnector myConnector = new MyConnector();
                myConnector.initConnector();

                try {
                    PreparedStatement statement = myConnector.connection.prepareStatement(
                            "UPDATE SINHVIEN SET HOTEN = ?, TUOI = ?, DIACHI = ?, GPA = ? WHERE MASINHVIEN = ?;");

                    statement.setString(1, student.getName());
                    statement.setInt(2, student.getAge());
                    statement.setString(3, student.getAddress());
                    statement.setFloat(4, student.getGpa());
                    statement.setInt(5, student.getId());

                    statement.executeUpdate();

                    myConnector.connection.close();

                    listStudents.get(i).setName(student.getName());
                    listStudents.get(i).setAge(student.getAge());
                    listStudents.get(i).setAddress(student.getAddress());
                    listStudents.get(i).setGpa(student.getGpa());

                    flag = true;

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        return flag;
    }

    public boolean delete(Student student) {
        boolean isFound = false;

        int size = listStudents.size();
        for (int i = 0; i < size; i++) {
            if (listStudents.get(i).getId() == student.getId()) {
                student = listStudents.get(i);
                isFound = true;
                break;
            }
        }
        if (isFound) {
            MyConnector myConnector = new MyConnector();
            myConnector.initConnector();

            try {
                PreparedStatement statement = myConnector.connection
                        .prepareStatement("DELETE FROM SINHVIEN WHERE MASINHVIEN = ?");

                statement.setInt(1, student.getId());
                statement.executeUpdate();
                myConnector.connection.close();
                listStudents.remove(student);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    public void sortStudentByGPA() {
        Collections.sort(listStudents, new Comparator<Student>() {
            public int compare(Student student1, Student student2) {
                if (student1.getGpa() > student2.getGpa()) {
                    return 1;
                }
                return -1;
            }
        });
    }

    public void sortStudentByName() {
        Collections.sort(listStudents, new Comparator<Student>() {
            public int compare(Student student1, Student student2) {
                return student1.getName().compareTo(student2.getName());
            }
        });

    }

    public List<Student> getListStudents() {
        return listStudents;
    }

    public void setListStudents(List<Student> listStudents) {
        this.listStudents = listStudents;
    }

}
