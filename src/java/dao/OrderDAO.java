package dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.AggregateQuerySnapshot;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import context.FirebaseConnection;
import entity.Cart;
import entity.Order;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDAO {

    Firestore db = FirebaseConnection.getFirestoreInstance();

    public void addOrder(String account, String firstname, String lastname, String phone, String address, Cart cart) throws InterruptedException, ExecutionException {

        Date date = new Date();

        Order order = new Order(account, date, firstname, lastname, phone, address, cart, "Processing");

        ApiFuture<DocumentReference> addedDocRef = db.collection("Order").add(order);
    }

    public int countOrder() {
        CollectionReference collection = db.collection("Order");
        AggregateQuerySnapshot snapshot = null;
        try {
            snapshot = collection.count().get().get();
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (int) snapshot.getCount();
    }

    public Map<String, Order> getAllOrder() {
        Map<String, Order> map = new LinkedHashMap<>();

        try {
            // Asynchronously retrieve all documents
            ApiFuture<QuerySnapshot> future = db.collection("Order")
                    .orderBy("date", Query.Direction.DESCENDING).get();
            List<QueryDocumentSnapshot> orders = future.get().getDocuments();
            for (QueryDocumentSnapshot order : orders) {
                map.put(order.getId(), order.toObject(Order.class));
            }
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return map;
    }

    public Map<String, Order> getOrdersByDate(Date startDate) {
        Map<String, Order> map = new LinkedHashMap<>();
        Date endDate = new Date(startDate.getTime() + (24 * 60 * 60 * 1000));

        try {
            // Asynchronously retrieve all documents
            ApiFuture<QuerySnapshot> future = db.collection("Order")
                    .orderBy("date", Query.Direction.DESCENDING)
                    .whereGreaterThanOrEqualTo("date", startDate)
                    .whereLessThan("date", endDate).get();
            List<QueryDocumentSnapshot> orders = future.get().getDocuments();
            for (QueryDocumentSnapshot order : orders) {
                map.put(order.getId(), order.toObject(Order.class));
            }
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return map;
    }

    public double getAllMoney() {
        Map<String, Order> map = getAllOrder();
        double money = 0;
        for (Order o : map.values()) {
            System.out.println(o);
            money += o.getCart().totalMoney();
        }
        return money;
    }

    public Map<String, Order> getOrdersByAccount(String username) {
        Map<String, Order> map = getAllOrder();

        for (Iterator<Map.Entry<String, Order>> it = map.entrySet().iterator(); it.hasNext();) {
            Map.Entry<String, Order> entry = it.next();
            Order order = entry.getValue();
            if (!order.getAccount().equalsIgnoreCase(username)) {
                it.remove();
            }
        }
        return map;
    }

    public void updateStatus(String id, String status) {
        DocumentReference docref = db.collection("Order").document(id);
        ApiFuture<WriteResult> future = docref.update("status", status);
    }

    public String getValue12Month() {
        Calendar cal = Calendar.getInstance();
        double[] values = new double[12]; // Tạo mảng để lưu trữ giá trị 12 tháng

        // Vòng lặp để lấy giá trị theo 12 tháng
        for (int i = 0; i < 12; i++) {
            cal.set(Calendar.MONTH, i); // Thiết lập tháng để lấy giá trị
            cal.set(Calendar.DAY_OF_MONTH, 1);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            Date monthStartDate = cal.getTime();

            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            Date monthEndDate = cal.getTime();

            // Lấy giá trị tương ứng với tháng đó và lưu vào mảng
            double monthValue = getValueByMonth(monthStartDate, monthEndDate);
            values[i] = monthValue;
        }
        return Arrays.toString(values);
    }

    public String getOrder12Month() {
        Calendar cal = Calendar.getInstance();
        double[] orders = new double[12]; // Tạo mảng để lưu trữ giá trị 12 tháng

        // Vòng lặp để lấy giá trị theo 12 tháng
        for (int i = 0; i < 12; i++) {
            cal.set(Calendar.MONTH, i); // Thiết lập tháng để lấy giá trị
            cal.set(Calendar.DAY_OF_MONTH, 1);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            Date monthStartDate = cal.getTime();

            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            Date monthEndDate = cal.getTime();

            // Lấy giá trị tương ứng với tháng đó và lưu vào mảng
            double monthOrder = getOrderByMonth(monthStartDate, monthEndDate);
            orders[i] = monthOrder;
        }
        return Arrays.toString(orders);
    }

    private double getOrderByMonth(Date monthStartDate, Date monthEndDate) {
        AggregateQuerySnapshot snapshot = null;
        try {
            snapshot = db.collection("Order")
                    .orderBy("date", Query.Direction.DESCENDING)
                    .whereGreaterThanOrEqualTo("date", monthStartDate)
                    .whereLessThan("date", monthEndDate).count().get().get();
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return snapshot.getCount();

    }

    private double getValueByMonth(Date monthStartDate, Date monthEndDate) {
        ApiFuture<QuerySnapshot> future = db.collection("Order")
                .orderBy("date", Query.Direction.DESCENDING)
                .whereGreaterThanOrEqualTo("date", monthStartDate)
                .whereLessThan("date", monthEndDate).get();
        System.out.println(monthStartDate + "   " + monthEndDate);
        List<QueryDocumentSnapshot> orders;
        double money = 0;
        try {
            orders = future.get().getDocuments();
            for (QueryDocumentSnapshot order : orders) {
                money += order.toObject(Order.class).getCart().totalMoney();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return money;
    }
    
        
    public void deleteOrder(String id) {
        try {
            ApiFuture<WriteResult> future = db.collection("Order").document(id).delete();
        } catch (Exception ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
