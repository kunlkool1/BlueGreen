package entity;

public class Item {
    private String id;
    private Product product;
    private int quantity;

    public Item() {
    }

    public Item(String id, Product product, int quantity ) {
        this.id =id;
        this.product = product;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public double total() {
        return product.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", product=" + product + ", quantity=" + quantity + '}';
    }



}