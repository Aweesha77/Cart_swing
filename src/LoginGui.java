import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class LoginGui extends JFrame {

    public static ArrayList<User> userDetails = new ArrayList<>();
    JPanel loginPanel;
    JLabel usernameLabel,passwordLabel;
    JPasswordField passwordField;
    JTextField usernameField;
    JButton loginButton;

    LoginGui(){
        loginPanel = new JPanel();
        this.add(loginPanel);
        loginPanel.setBackground(Color.WHITE);
        loginPanel.setLayout(new FlowLayout());

        usernameLabel = new JLabel("Username:");
        loginPanel.add(usernameLabel);

        usernameField = new JTextField(10);
        loginPanel.add(usernameField);

        passwordLabel = new JLabel("Password:");
        loginPanel.add(passwordLabel);

        passwordField = new JPasswordField(10);
        loginPanel.add(passwordField);

        loginButton=new JButton("Login");
        loginPanel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("LOL");
                loginUser();
            }
        });
    }

    private void loginUser() {

        String userId = usernameLabel.getText();
        String password = new String(passwordField.getPassword());

//        if (WestminsterShoppingManager.isValidUser(userId, password)) {
//            JOptionPane.showMessageDialog(this, "Login successful!");
//        } else {
//            JOptionPane.showMessageDialog(this, "Invalid user ID or password. Please try again.");
//        }

    }}

//    public boolean isValidUser(String userId, String password) {
//        // Check if the user is valid based on the loaded user details
//        // In a real-world application, you might want to use a more secure way to validate passwords
//        // For simplicity, this example just checks if the user ID and password match any stored details
////        for (User user: ) {
////            if (user.getUserName().equals(userId) && user.getUserPassword().equals(password)) {
////                return true;
////            }
////        }
////        return false;
////    }
//
//
////}
//    }
