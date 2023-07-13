package entity;

import java.util.Date;
import java.util.List;

public class Product {
    
    private String name;
    private String description;
    private double price;
    private int discount;
    private String thumbnail;
    private List<String> listImg;
    private Date addedDate;
    private String category;
    private String brand;
    private String color;
    
    public Product(){  
    }

    public Product(String name, String description, double price, int discount, String thumbnail, List<String> listImg, Date addedDate, String category, String brand, String color) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.thumbnail = thumbnail;
        this.listImg = listImg;
        this.addedDate = addedDate;
        this.category = category;
        this.brand = brand;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getDiscount() {
        return discount;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public List<String> getListImg() {
        return listImg;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public String getCategory() {
        return category;
    }

    public String getBrand() {
        return brand;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setListImg(List<String> listImg) {
        this.listImg = listImg;
    }
    
    public String listUrl(){
        StringBuilder sb = new StringBuilder();
        for (String i : listImg) {
            sb.append(i);
            sb.append("\n");
        }
        System.out.println(sb.toString().trim());
        return sb.toString().trim();
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    public double finalPrice() {
        return price - price * discount / 100;
    }
}