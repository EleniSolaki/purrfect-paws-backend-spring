package gr.Solaki.AnimalAdoption.response;

import gr.Solaki.AnimalAdoption.model.User;

public class LoginMessage {
    String message;
    Boolean status;
    Long userid;
    String userUsername;
    String userEmail;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    public Long getUserId() {
        return userid;
    }

    public void setUser(Long userid) {
        this.userid = userid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public LoginMessage(String message, Boolean status, User userid, User userUsername, User userEmail) {
        this.message = message;
        this.status = status;
        this.userid = userid.getId();
        this. userUsername = userUsername.getUsername();
        this.userEmail = userEmail.getEmail();
    }

    @Override
    public String toString() {
        return "LoginMessage{" +
                "message='" + message + '\'' +
                ", status=" + status +
                ", userid=" + userid +
                ", userUsername='" + userUsername + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}
