package entity;

// This class corresponds to the user table in the database.
public class User {
    private int id;
    private String userName;
    private String passWord;
    private String first_name;
    private String last_name;
    private String mail;
    private Role role;

    public User() {

    }

    public User(int id, String userName, String passWord, String first_name, String last_name, String mail, Role role) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.first_name = first_name;
        this.last_name = last_name;
        this.mail = mail;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", mail='" + mail + '\'' +
                ", role=" + role +
                '}';
    }
}
