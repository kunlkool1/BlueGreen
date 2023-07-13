package entity;

public class Account {

    private String username;
    private String password;
    private String email;
    private boolean isAdmin;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;


    public Account() {
    }

    public Account(String username, String password, String email, boolean isAdmin, String firstName, String lastName, String phone, String address) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    public String getUsername() {
        return username.toLowerCase().trim();
    }

    public void setUsername(String username) {
        this.username = username.toLowerCase().trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email.toLowerCase().trim();
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase().trim();
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
 
    
    
    @Override
    public String toString() {
        return "Account{" + "username=" + username + ", password=" + password + ", isAdmin=" + isAdmin + '}';
    }

}
