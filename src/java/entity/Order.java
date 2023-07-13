package entity;

import java.util.Date;

public class Order {

    private String account;
    private Date date;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private Cart cart;
    private String status;

    public Order() {
    }

    public Order(String account, Date date, String firstname, String lastName, String phone, String address, Cart cart, String status) {
        this.account = account;
        this.date = date;
        this.firstName = firstname;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.cart = cart;
        this.status = status;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}