import java.awt.EventQueue;
import javax.swing.JFrame;
import controller.LoginController;
import view.LoginView;

public class ProjectMainApp extends JFrame {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                LoginView view = new LoginView();
                LoginController controller = new LoginController(view);

                controller.showLoginView();
            }
        });
    }
}
