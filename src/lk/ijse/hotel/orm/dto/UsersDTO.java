package lk.ijse.hotel.orm.dto;

public class UsersDTO {

    private String id;
    private String userName;
    private String password;
    private String contact;

    public UsersDTO() {
    }

    public UsersDTO(String id, String userName, String password, String contact) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.contact = contact;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

}
