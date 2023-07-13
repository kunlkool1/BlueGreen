package dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.AggregateQuerySnapshot;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import context.FirebaseConnection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import entity.Account;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class AccountDAO {

    Firestore db = FirebaseConnection.getFirestoreInstance();

    public Account login(String username, String password) {
        Account account = null;

        try {
            ApiFuture<QuerySnapshot> future = db.collection("Account")
                    .whereEqualTo("username", username)
                    .whereEqualTo("password", password)
                    .get();
            List<QueryDocumentSnapshot> accounts = null;
            accounts = future.get().getDocuments();
            account = accounts.get(0).toObject(Account.class);
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return account;
    }

    public boolean checkExistAccount(String username) {
        try {
            username = username.toLowerCase().trim();
            ApiFuture<QuerySnapshot> future = db.collection("Account")
                    .whereEqualTo("username", username).get();
            List<QueryDocumentSnapshot> accounts = future.get().getDocuments();
            if (!accounts.isEmpty()) {
                return true;
            }
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Map<String, Account> getAllAccount() {
        Map<String, Account> map = new LinkedHashMap<>();

        try {
            // Asynchronously retrieve all documents
            ApiFuture<QuerySnapshot> future = db.collection("Account").get();
            List<QueryDocumentSnapshot> accounts = future.get().getDocuments();
            for (QueryDocumentSnapshot account : accounts) {
                map.put(account.getId(), account.toObject(Account.class));
            }
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return map;
    }

    public void addAccount(Account account) {
        // Add document data with auto-generated id.
        db.collection("Account").document(account.getUsername()).set(account);
    }

    public void updateAccount(String id, String firstName, String lastName, String phone, String address) {
        DocumentReference docref = db.collection("Account").document(id);

        try {
            ApiFuture<WriteResult> future = docref.update("firstName", firstName,
                    "lastName", lastName,
                    "phone", phone,
                    "address", address);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePass(String id, String password) {
        DocumentReference docref = db.collection("Account").document(id);

        try {
            ApiFuture<WriteResult> future = docref.update("password", password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAccount(String username) {
        try {
            // Asynchronously retrieve all documents
            ApiFuture<WriteResult> future = db.collection("Account").document(username).delete();
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int countCustomer(){
        CollectionReference collection = db.collection("Account");
        Query query = collection.whereEqualTo("isAdmin", false);
        AggregateQuerySnapshot snapshot = null;
        try {
            snapshot = query.count().get().get();
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (int) snapshot.getCount();
    }
}
