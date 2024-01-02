package pbo_final_boss.model.User;
//class abstracts

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class User {
    private String userName;
    private String password;
    private String role;

    public User() {
    }

    public User(String userName, String password, String role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
