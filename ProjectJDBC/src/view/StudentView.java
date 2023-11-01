package view;

import java.util.List;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import entity.Student;

public class StudentView extends JFrame implements ActionListener, ListSelectionListener {

    private static final long serialVersionUID = 1L;
    private JButton addStudentBtn;
    private JButton editStudentBtn;
    private JButton deleteStudentBtn;
    private JButton clearBtn;
    private JButton sortStudentGPABtn;
    private JButton sortStudentNameBtn;
    private JScrollPane jScrollPaneStudentTable;
    private JScrollPane jScrollPaneAddress;
    private JTable studenTable;

    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel ageLabel;
    private JLabel addressLabel;
    private JLabel gpaLabel;

    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextArea addressTA;
    private JTextField gpaField;

    // định nghĩa các cột của bảng student
    private String[] columnNames = new String[] {
            "ID", "Name", "Age", "Address", "GPA"
    };

    // định nghĩa dữ liệu mặc định của bảng student là rỗng
    private Object data = new Object[][] {};

    public StudentView() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // khởi tạo các phím chức năng
        addStudentBtn = new JButton("Add");
        editStudentBtn = new JButton("Edit");
        deleteStudentBtn = new JButton("Delete");
        clearBtn = new JButton("Clear");
        sortStudentGPABtn = new JButton("Sort By GPA");
        sortStudentNameBtn = new JButton("Sort By Name");

        // khởi tạo bảng student
        jScrollPaneStudentTable = new JScrollPane();
        studenTable = new JTable();

        // khởi tạo các label
        idLabel = new JLabel("Id");
        nameLabel = new JLabel("Name");
        ageLabel = new JLabel("Age");
        addressLabel = new JLabel("Address");
        gpaLabel = new JLabel("GPA");

        // khởi tạo các trường nhập dữ liệu cho student
        idField = new JTextField(5);
        nameField = new JTextField(15);
        ageField = new JTextField(5);
        addressTA = new JTextArea();
        addressTA.setColumns(15);
        addressTA.setRows(5);
        jScrollPaneAddress = new JScrollPane();
        jScrollPaneAddress.setViewportView(addressTA);
        gpaField = new JTextField(5);

        // cài đặt các cột và data cho bảng student
        studenTable.setModel(new DefaultTableModel((Object[][]) data, columnNames));
        jScrollPaneStudentTable.setViewportView(studenTable);
        jScrollPaneStudentTable.setPreferredSize(new Dimension(480, 300));

        // tạo spring layout
        SpringLayout layout = new SpringLayout();

        // tạo đối tượng panel để chứa các thành phần của màn hình quản lý Student
        JPanel panel = new JPanel();
        panel.setSize(800, 420);
        panel.setLayout(layout);
        panel.add(jScrollPaneStudentTable);

        panel.add(addStudentBtn);
        panel.add(editStudentBtn);
        panel.add(deleteStudentBtn);
        panel.add(clearBtn);
        panel.add(sortStudentGPABtn);
        panel.add(sortStudentNameBtn);

        panel.add(idLabel);
        panel.add(nameLabel);
        panel.add(ageLabel);
        panel.add(addressLabel);
        panel.add(gpaLabel);

        panel.add(idField);
        panel.add(nameField);
        panel.add(ageField);
        panel.add(jScrollPaneAddress);
        panel.add(gpaField);

        // cài đặt vị trí các thành phần trên màn hình login
        String WEST = SpringLayout.WEST;
        String NORTH = SpringLayout.NORTH;

        layout.putConstraint(WEST, idLabel, 10, WEST, panel);
        layout.putConstraint(NORTH, idLabel, 10, NORTH, panel);
        layout.putConstraint(WEST, nameLabel, 10, WEST, panel);
        layout.putConstraint(NORTH, nameLabel, 40, NORTH, panel);
        layout.putConstraint(WEST, ageLabel, 10, WEST, panel);
        layout.putConstraint(NORTH, ageLabel, 70, NORTH, panel);
        layout.putConstraint(WEST, addressLabel, 10, WEST, panel);
        layout.putConstraint(NORTH, addressLabel, 100, NORTH, panel);
        layout.putConstraint(WEST, gpaLabel, 10, WEST, panel);
        layout.putConstraint(NORTH, gpaLabel, 200, NORTH, panel);

        layout.putConstraint(WEST, idField, 100, WEST, panel);
        layout.putConstraint(NORTH, idField, 10, NORTH, panel);
        layout.putConstraint(WEST, nameField, 100, WEST, panel);
        layout.putConstraint(NORTH, nameField, 40, NORTH, panel);
        layout.putConstraint(WEST, ageField, 100, WEST, panel);
        layout.putConstraint(NORTH, ageField, 70, NORTH, panel);
        layout.putConstraint(WEST, jScrollPaneAddress, 100, WEST, panel);
        layout.putConstraint(NORTH, jScrollPaneAddress, 100, NORTH, panel);
        layout.putConstraint(WEST, gpaField, 100, WEST, panel);
        layout.putConstraint(NORTH, gpaField, 200, NORTH, panel);

        layout.putConstraint(WEST, jScrollPaneStudentTable, 300, WEST, panel);
        layout.putConstraint(NORTH, jScrollPaneStudentTable, 10, NORTH, panel);

        layout.putConstraint(WEST, addStudentBtn, 20, WEST, panel);
        layout.putConstraint(NORTH, addStudentBtn, 240, NORTH, panel);
        layout.putConstraint(WEST, editStudentBtn, 60, WEST, addStudentBtn);
        layout.putConstraint(NORTH, editStudentBtn, 240, NORTH, panel);
        layout.putConstraint(WEST, deleteStudentBtn, 60, WEST, editStudentBtn);

        layout.putConstraint(WEST, clearBtn, 240, WEST, deleteStudentBtn);
        layout.putConstraint(NORTH, clearBtn, 80, NORTH, panel);

        layout.putConstraint(NORTH, deleteStudentBtn, 240, NORTH, panel);
        layout.putConstraint(WEST, sortStudentGPABtn, 300, WEST, panel);
        layout.putConstraint(NORTH, sortStudentGPABtn, 330, NORTH, panel);
        layout.putConstraint(WEST, sortStudentNameBtn, 115, WEST, sortStudentGPABtn);
        layout.putConstraint(NORTH, sortStudentNameBtn, 330, NORTH, panel);

        this.add(panel);
        this.pack();
        this.setTitle("Studen Information");
        this.setSize(800, 420);

        // disable Edit and Delete buttons
        editStudentBtn.setEnabled(false);
        deleteStudentBtn.setEnabled(false);

        // enable Add button
        addStudentBtn.setEnabled(true);

    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    // hiển thị danh sách sinh viên vào bảng studentTable
    public void showListStudent(List<Student> list) {
        int size = list.size();

        // tạo một mảng 2 chiều với số dòng là số lượng sinh viên (size)
        // và số cột là 5.
        Object[][] Students = new Object[size][5];
        for (int i = 0; i < size; i++) {
            Students[i][0] = list.get(i).getId();
            Students[i][1] = list.get(i).getName();
            Students[i][2] = list.get(i).getAge();
            Students[i][3] = list.get(i).getAddress();
            Students[i][4] = list.get(i).getGpa();
        }
        studenTable.setModel(new DefaultTableModel(Students, columnNames));
    }

    // điền thông tin cửa hang được chọn từ bảng sinh viên
    // vào các trường tương ứng của sinh viên
    public void fillStudentFromSelectedRow() {
        // lấy chỉ số của hàng được chọn
        int row = studenTable.getSelectedRow();
        if (row >= 0) {
            idField.setText(studenTable.getModel().getValueAt(row, 0).toString());
            nameField.setText(studenTable.getModel().getValueAt(row, 1).toString());
            ageField.setText(studenTable.getModel().getValueAt(row, 2).toString());
            addressTA.setText(studenTable.getModel().getValueAt(row, 3).toString());
            ageField.setText(studenTable.getModel().getValueAt(row, 4).toString());

            // enable Edit and Delete buttons
            editStudentBtn.setEnabled(true);
            editStudentBtn.setEnabled(true);

            // disable Add button
            addStudentBtn.setEnabled(false);
        }
    }

    // xóa thông tin sinh viên
    public void clearStudentInfo() {
        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        addressTA.setText("");
        gpaField.setText("");

        // disable Edit and Delete buttons
        editStudentBtn.setEnabled(false);
        deleteStudentBtn.setEnabled(false);

        // enable Add button
        addStudentBtn.setEnabled(true);
    }

    // hiện thị thông tin sinh viên
    public void showStudent(Student student) {
        idField.setText("" + student.getId());
        nameField.setText(student.getName());
        ageField.setText("" + student.getAge());
        addressTA.setText(student.getAddress());
        gpaField.setText("" + student.getGpa());

        // enable Edit and Delete buttons
        editStudentBtn.setEnabled(true);
        deleteStudentBtn.setEnabled(true);

        // enable Edit and Delete buttons
        addStudentBtn.setEnabled(false);
    }

    // lấy thông tin sinh viên
    public Student getStudentInfo() {

        // validate student
        if (!validateId() || !validateName() || !validateAge() || !validateAddress() || !validateGPA())
            return null;

        try {

            Student student = new Student();
            student.setId(Integer.parseInt(idField.getText()));
            student.setName(nameField.getText().trim());
            student.setAge(Byte.parseByte(ageField.getText().trim()));
            student.setAddress(addressTA.getText().trim());
            student.setGpa(Float.parseFloat(gpaField.getText().trim()));
            return student;
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }

    private boolean validateId() {

        String id = idField.getText();
        if (id == null || "".equals(id.trim())) {
            idField.requestFocus();
            showMessage("Id cannot be empty");
            return false;
        }

        return true;
    }

    private boolean validateName() {
        String name = nameField.getText();
        if (nameField == null || "".equals(name.trim())) {
            nameField.requestFocus();
            showMessage("Name cannot be empty");
            return false;
        }
        return true;
    }

    private boolean validateAddress() {

        String address = addressTA.getText();
        if (addressTA == null || "".equals(address.trim())) {
            addressTA.requestFocus();
            showMessage("Address cannot be empty");
            return false;
        }
        return true;
    }

    private boolean validateAge() {
        try {
            Byte age = Byte.parseByte(ageField.getText().trim());
            if (age < 0 || age > 100) {
                ageField.requestFocus();
                showMessage("Age wrong format! It should be between 0 and 100.");
                return false;
            }
        } catch (Exception e) {
            ageField.requestFocus();
            showMessage("Age wrong format!");
            return false;
        }
        return true;
    }

    private boolean validateGPA() {
        try {

            Float gpa = Float.parseFloat(gpaField.getText().trim());
            if (gpa < 0 || gpa > 10) {
                gpaField.requestFocus();
                showMessage("GPA wrong format! It should by between 0 and 10");
                return false;
            }
        } catch (Exception e) {
            gpaField.requestFocus();
            showMessage("GPA wrong format!");
            return false;
        }

        return true;
    }

    public void actionPerformed(ActionEvent e) {

    }

    public void valueChanged(ListSelectionEvent e) {

    }

    public void addAddStudentListener(ActionListener listener) {
        addStudentBtn.addActionListener(listener);
    }

    public void addEditStudenListener(ActionListener listener) {
        editStudentBtn.addActionListener(listener);
    }

    public void addDeleteStudenListener(ActionListener listener) {
        deleteStudentBtn.addActionListener(listener);
    }

    public void addClearListener(ActionListener listener) {
        clearBtn.addActionListener(listener);
    }

    public void addSortStudentGPAListener(ActionListener listener) {
        sortStudentGPABtn.addActionListener(listener);
    }

    public void addSortStudentNameListener(ActionListener listener) {
        sortStudentNameBtn.addActionListener(listener);
    }

    public void addListStudentSelectionListener(ListSelectionListener listener) {
        studenTable.getSelectionModel().addListSelectionListener(listener);
    }

}
