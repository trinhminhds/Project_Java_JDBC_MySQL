package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import dao.StudentDao;
import entity.Student;
import view.StudentView;

public class StudentController {
    private StudentDao studentDao;
    private StudentView studentView;

    public StudentController(StudentView view) {
        this.studentView = view;
        studentDao = new StudentDao();

        view.addAddStudentListener(new AddStudentListener());
        view.addEditStudenListener(new EditStudentListener());
        view.addDeleteStudenListener(new DeleteStudentListener());
        view.addClearListener(new ClearStudentListener());
        view.addSortStudentGPAListener(new SortStudentGPAListener());
        view.addSortStudentNameListener(new SortStudentNameListener());
        view.addListStudentSelectionListener(new ListStudentSelectionListener());

    }

    public void showStudentView() {
        List<Student> studentList = studentDao.getListStudents();
        studentView.setVisible(true);
        studentView.showListStudent(studentList);
    }

    class AddStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Student student = studentView.getStudentInfo();

            if (student != null) {
                if (studentDao.add(student)) {
                    studentView.showStudent(student);
                    studentView.showListStudent(studentDao.getListStudents());
                    studentView.showMessage("Add successfullly.");
                } else
                    studentView.showMessage("Cannot Add.");

            }
        }

    }

    class EditStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Student student = studentView.getStudentInfo();
            if (student != null) {
                if (studentDao.edit(student)) {
                    studentView.showStudent(student);
                    studentView.showListStudent(studentDao.getListStudents());
                    studentView.showMessage("Update successfully!");
                } else
                    studentView.showMessage("Cannot Update!");
            }
        }
    }

    class DeleteStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Student student = studentView.getStudentInfo();
            if (student != null) {
                if (studentDao.delete(student)) {
                    studentView.clearStudentInfo();
                    studentView.showListStudent(studentDao.getListStudents());
                    studentView.showMessage("Remove Successfully");
                } else
                    studentView.showMessage("Cannot remove!");
            }
        }

    }

    class ClearStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            studentView.clearStudentInfo();
        }
    }

    class SortStudentGPAListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            studentDao.sortStudentByGPA();
            studentView.showListStudent(studentDao.getListStudents());
        }
    }

    class SortStudentNameListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            studentDao.sortStudentByName();
            studentView.showListStudent(studentDao.getListStudents());
        }
    }

    class ListStudentSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            studentView.fillStudentFromSelectedRow();
        }
    }

}
