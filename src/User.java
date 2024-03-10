import java.io.Serializable;

public class User implements Serializable {
    private String userName;
    private String userPassword;
    private int history;

    public User(String userName, String userPassword, int history) {    //constructor
        this.userName=userName;
        this.userPassword=userPassword;
        this.history=history;
    }

    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {

        this.userName = userName;
    }

    public String getUserPassword() {

        return userPassword;
    }

    public void setUserPassword(String userPassword) {

        this.userPassword = userPassword;
    }

    public int getHistory() {
        return history;
    }

    public void setHistory(int history) {
        this.history = history;
    }





}
