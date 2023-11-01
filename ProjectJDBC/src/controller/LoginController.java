package controller;

import java.awt.event.*;
import entity.User;
import view.LoginView;
import view.StudentView;

public class LoginController {

    private LoginView loginView;
    private StudentView studentView;

    public LoginController(LoginView view) {
        this.loginView = view;
        view.addLoginListener(new LoginListener());
    }

    public void showLoginView() {
        loginView.setVisible(true);
    }

    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            User user = loginView.getUser();
            if (checkUser(user)) {
                studentView = new StudentView();
                StudentController sc = new StudentController(studentView);
                sc.showStudentView();
                loginView.setVisible(false);
            } else
                loginView.showMessage("Username or password are incorrect!");
        }

        public boolean checkUser(User user) {
            if (user != null) {
                if ("admin".equals(user.getUserName()) && "admin".equals(user.getPassword()))
                    return true;
            }
            return false;
        }
    }

}
