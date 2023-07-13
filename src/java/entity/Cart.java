package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable{

    private List<Item> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public Cart(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Item getItemById(String id){
        for(Item item : items){
            if(item.getId().equals(id))
                return item;
        }
        return null;
    }

    public void addItem(Item item){
        if(getItemById(item.getId()) != null){
            Item m = getItemById(item.getId());
            m.setQuantity(m.getQuantity() + item.getQuantity());
        }else
            items.add(item);
    }

    public void deleteItem(String id){
        if(getItemById(id) !=null){
            items.remove(getItemById(id));
        }
    }

    public int size(){
        int total=0;
        for(Item item: items){
            total+= item.getQuantity();
        }
        return total;
    }

    public double totalMoney(){
        double money = 0;
        for(Item item: items)
            money += item.getQuantity() * item.getProduct().finalPrice();
        return money + 20;
    }

}