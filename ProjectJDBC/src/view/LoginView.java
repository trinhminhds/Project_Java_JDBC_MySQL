package view;

import java.awt.event.*;
import javax.swing.*;
import entity.User;

public class LoginView extends JFrame implements ActionListener {
    private JLabel userNameLabel;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JTextField userNameField;
    private JButton loginBtn;

    public LoginView() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        userNameLabel = new JLabel("UserName");
        passwordLabel = new JLabel("Password");
        userNameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        loginBtn = new JButton();

        loginBtn.setText("Login");
        loginBtn.addActionListener(this);

        // tạo spring layout
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();

        // tạo đối tượng panel để chứa các thành phần của màn hình login
        panel.setSize(400, 300);
        panel.setLayout(layout);
        panel.add(userNameLabel);
        panel.add(passwordLabel);
        panel.add(userNameField);
        panel.add(passwordField);
        panel.add(loginBtn);

        // cài đặt vị trí các thành phần trên màn hình login
        String WEST = SpringLayout.WEST;
        String NORTH = SpringLayout.NORTH;

        layout.putConstraint(WEST, userNameLabel, 20, WEST, panel);
        layout.putConstraint(NORTH, userNameLabel, 80, NORTH, panel);
        layout.putConstraint(WEST, passwordLabel, 20, WEST, panel);
        layout.putConstraint(NORTH, passwordLabel, 105, NORTH, panel);
        layout.putConstraint(WEST, userNameField, 80, WEST, userNameLabel);
        layout.putConstraint(NORTH, userNameField, 80, NORTH, panel);
        layout.putConstraint(WEST, passwordField, 80, WEST, passwordLabel);
        layout.putConstraint(NORTH, passwordField, 105, NORTH, panel);
        layout.putConstraint(WEST, loginBtn, 80, WEST, passwordLabel);
        layout.putConstraint(NORTH, loginBtn, 130, NORTH, panel);

        // add panel tới JFrame
        this.add(panel);
        this.pack();

        // cài đặt các thuộc tính cho JFrame
        this.setTitle("Login");
        this.setSize(400, 300);
        this.setResizable(false);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public User getUser() {
        return new User(userNameField.getText(),
                String.copyValueOf(passwordField.getPassword()));
    }

    public void actionPerformed(ActionEvent e) {
    }

    public void addLoginListener(ActionListener listener) {
        loginBtn.addActionListener(listener);
    }

}
