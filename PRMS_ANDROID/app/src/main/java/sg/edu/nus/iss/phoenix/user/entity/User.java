package sg.edu.nus.iss.phoenix.user.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Cloneable, Serializable {

    /**
     * eclipse identifier
     */
    //private static final long serialVersionUID = -5500218812568593553L;

    /**
     * Persistent Instance variables. This data is directly
     * mapped to the columns of database table.
     */
    //private int userId;
    private int userId;
    private String name;
    private String password;
    private String emailID;
    private String joiningDate;
    private List<Role> listRoles = new ArrayList<Role>();
    private List<Integer> roleId = new ArrayList<Integer>();

    /**
     * Constructors.
     * The first one takes no arguments and provides the most simple
     * way to create object instance. The another one takes one
     * argument, which is the primary key of the corresponding table.
     */

    public User () {

    }

    public User (String nameIn) {

        this.name = nameIn;

    }

    public User (int userIdIn) {

        this.userId = userIdIn;

    }

    public User (int userIdIn, String nameIn, String passwordIn, String emailIDIn, String joiningDateIn) {
        this.userId = userIdIn;
        this.name = nameIn;
        this.password = passwordIn;
        this.emailID = emailIDIn;
        this.joiningDate = joiningDateIn;
    }

    public User(int userId, String name, String password) {
        this.userId = userId;
        this.name = name;
        this.password = password;
    }

    public User(int userId, String name) {
        this.userId=userId;
        this.name=name;
    }

    public User(String name, String password,String emailID,String joiningDate) {
        this.emailID=emailID;
        this.name=name;
        this.password=password;
        this.joiningDate=joiningDate;
    }



    public User(int userId,List<Integer> roleId)
    {
        this.userId = userId;
        this.roleId = roleId;
    }

    public User(int userIdIn, String nameIn, String emailIDIn, String joiningDateIn) {
        this.userId = userIdIn;
        this.name = nameIn;
        this.emailID = emailIDIn;
        this.joiningDate = joiningDateIn;
    }

    /**
     * Get- and Set-methods for persistent variables. The default
     * behaviour does not make any checks against malformed data,
     * so these might require some manual additions.
     * @return
     */
    public int getUserId() {
        return this.userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String nameIn) {
        this.name = nameIn;
    }

    public String getPassword() {
        return this.password;
    }
    public void setPassword(String passwordIn) {
        this.password = passwordIn;
    }

    public String getEmailID() {
        return this.emailID;
    }
    public void setEmailID(String emailIDIn) {
        this.emailID = emailIDIn;
    }

    public String getJoiningDate() {
        return this.joiningDate;}

    public void setJoiningDate(String joiningDateIn) {
        this.joiningDate = joiningDateIn;
    }

    public List<Role> getRoles()
    {
        return this.listRoles;
    }
    public void setRoles(List<Role> list)
    {
        this.listRoles = list;
    }

    public List<Integer> getRoleId()
    {
        return this.roleId;
    }
    public void setRoleId(List<Integer> list)
    {
        this.roleId = list;
    }
}