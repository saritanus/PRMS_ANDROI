package sg.edu.nus.iss.phoenix.user.entity;

/**
 * Created by sujit ambore
 * on 24/9/2017.
 */

public class User {

    private String userName;
    private String userRoleDescription;

    public User(String userName, String userRoleDescription) {
        this.userName = userName;
        this.userRoleDescription = userRoleDescription;
    }

    public String getUserName() {   return userName;    }

    public String getUserRoleDescription() {
        return userRoleDescription;
    }

    public void setUserRoleDescription(String userRoleDescription) {
        this.userRoleDescription= userRoleDescription;  }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
