package dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import context.FirebaseConnection;
import entity.Account;
import entity.Cart;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CartDAO {

    Firestore db = FirebaseConnection.getFirestoreInstance();

    public void addCart(Cart cart, Account account) {
        ApiFuture<WriteResult> future = db.collection("Cart")
                .document(account.getUsername()).set(cart);
    }

    public Cart getCart(Account account) {
        Cart cart = null;
        String username = account.getUsername();

        try {
            ApiFuture<DocumentSnapshot> future = db.collection("Cart").document(username).get();
            cart = future.get().toObject(Cart.class);
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cart;

    }

    public void deleteCart(Account account) {
        String username = account.getUsername();
        try {
            ApiFuture<WriteResult> future = db.collection("Cart").document(username).delete();

        } catch (Exception ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
