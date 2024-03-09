import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class LoginGUI extends JFrame {
    static String enteredUsername;

    public static ArrayList<User> userDetails = new ArrayList<>();
    JPanel loginPanel,usernamePanel,passwordPanel;
    JLabel usernameLabel,passwordLabel;
    JPasswordField passwordField;
    JTextField usernameField;
    JButton loginButton,nonRegisterButton;

    LoginGUI(){

        Font myFont = new Font("Arial ", Font.PLAIN, 12);

        usernamePanel=new JPanel();
        passwordPanel=new JPanel();
        loginPanel = new JPanel();

        usernameLabel = new JLabel("Username:");
        usernamePanel.add(usernameLabel);

        usernameField = new JTextField(10);    //text field to enter username
        usernamePanel.add(usernameField);

        passwordLabel = new JLabel("Password:");
        passwordPanel.add(passwordLabel);

        passwordField = new JPasswordField(10);    //password field
        passwordPanel.add(passwordField);

        loginButton=new JButton("Login");    //create login button
        loginPanel.add(loginButton);
        loginButton.setPreferredSize(new Dimension(120, 40));
        loginButton.setForeground(new Color(115, 92, 156));
        loginButton.setFont(myFont);
        loginButton.setBorder(new LineBorder(new Color(115, 92, 156), 2));

        nonRegisterButton=new JButton("Non-Register Login");    //create non register button
        loginPanel.add(nonRegisterButton);
        nonRegisterButton.setForeground(new Color(115, 92, 156));
        nonRegisterButton.setPreferredSize(new Dimension(120, 40));
        nonRegisterButton.setBorder(new LineBorder(new Color(115, 92, 156), 2));
        nonRegisterButton.setFont(myFont);
        nonRegisterButton.setBackground(Color.WHITE);

        this.setLayout(new GridLayout(3,1));
        this.add(usernamePanel);
        this.add(passwordPanel);
        this.add(loginPanel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enteredUsername = usernameField.getText();
                char[] enteredPassword = passwordField.getPassword();

                User loggedInUser = login(enteredUsername, new String(enteredPassword));
                if(loggedInUser==null){
                    JOptionPane.showMessageDialog(null, "Login Failed!");   //if username and password field empty this message will pop up
                    LoginGUI loginFrame = new LoginGUI();
                    loginFrame.setTitle("Login");               //if login fails again create a login gui
                    loginFrame.setVisible(true);
                    loginFrame.setSize(350, 200);
                    loginFrame.setLocationRelativeTo(null);

                }

                usernameField.setText("");    // Clear fields after login attempt
                passwordField.setText("");
                dispose();

            }
        });

        nonRegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showShoppingCart();     //direct to the westminster GUI
                dispose();
            }
        });
    }

    public boolean checkUserForDiscount() {
        for (User user : WestminsterShoppingManager.userList) {
        }
        return true;
    }

    private void showShoppingCart() {
        // Create a simple shopping cart frame (you can customize this according to your needs)
        WestminsterGUI frame = new WestminsterGUI();
        frame.setTitle("Westminster Shopping Centre");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(750, 500); // resize this
        frame.setLocationRelativeTo(null);

        // shoppingCartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private User login(String enteredUsername, String enteredPassword) {
        for (User user : WestminsterShoppingManager.userList) {
            if (user.getUserName().equals(enteredUsername) && user.getUserPassword().equals(enteredPassword)) {
                if (user.getHistory()==0){     //if user history=0 means didn't buy any products prevoiusly
                    //System.out.println("eligible for 10%");     //dialog box
                    JOptionPane.showMessageDialog(null, "Login Succeed\neligible for 10% discount!");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Login Succeed!");
                }
                showShoppingCart();
                return user;
            }
        }
        return null;
    }

}
