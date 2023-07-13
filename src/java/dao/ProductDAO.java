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
import entity.Brand;
import entity.Category;
import entity.Product;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDAO {

    Firestore db = FirebaseConnection.getFirestoreInstance();

    public String getCategoryName(String id) {
        String name = "";

        try {
            ApiFuture<DocumentSnapshot> future = db.collection("Category").document(id).get();
            DocumentSnapshot category = future.get();
            name = category.getString("name");
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name;
    }

    public Map<String, Category> getAllCategory() {
        Map<String, Category> map = new HashMap();

        try {
            // Asynchronously retrieve all documents
            ApiFuture<QuerySnapshot> future = db.collection("Category").get();
            List<QueryDocumentSnapshot> categories = future.get().getDocuments();
            for (QueryDocumentSnapshot category : categories) {
                map.put(category.getId(), category.toObject(Category.class));
            }
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return map;
    }

    public Map<String, Brand> getAllBrand() {
        Map<String, Brand> map = new LinkedHashMap<>();

        try {
            // Asynchronously retrieve all documents
            ApiFuture<QuerySnapshot> future = db.collection("Brand").orderBy("name").get();
            List<QueryDocumentSnapshot> brands = future.get().getDocuments();
            for (QueryDocumentSnapshot brand : brands) {
                map.put(brand.getId(), brand.toObject(Brand.class));
            }
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return map;
    }

    public Map<String, Product> getAllProduct() {
        Map<String, Product> map = new HashMap();

        try {
            // Asynchronously retrieve all documents
            ApiFuture<QuerySnapshot> future = db.collection("Product").get();
            List<QueryDocumentSnapshot> products = future.get().getDocuments();
            for (QueryDocumentSnapshot product : products) {
                map.put(product.getId(), product.toObject(Product.class));
            }
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return map;
    }

    public Map<String, Product> getAllProductByCateId(String id) {
        Map<String, Product> map = new HashMap();

        try {
            // Asynchronously retrieve all documents
            ApiFuture<QuerySnapshot> future = db.collection("Product").whereEqualTo("cateId", id).get();
            List<QueryDocumentSnapshot> products = future.get().getDocuments();
            for (QueryDocumentSnapshot product : products) {
                map.put(product.getId(), product.toObject(Product.class));
            }
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return map;
    }

    public Product getProductById(String id) {
        Product product = null;

        try {
            // Asynchronously retrieve all documents
            ApiFuture<DocumentSnapshot> future = db.collection("Product").document(id).get();
            product = future.get().toObject(Product.class);
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return product;
    }

    public Map<String, Product> getAllSpecialOffer() {
        Map<String, Product> map = new HashMap<>();

        try {
            // Asynchronously retrieve all documents
            ApiFuture<QuerySnapshot> future = db.collection("Product")
                    .orderBy("discount", Query.Direction.DESCENDING)
                    .limit(16).get();
            List<QueryDocumentSnapshot> products = future.get().getDocuments();
            for (QueryDocumentSnapshot product : products) {
                map.put(product.getId(), product.toObject(Product.class));
            }
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return map;
    }

    public Map<String, Product> getAllNewRelease() {
        Map<String, Product> map = new HashMap<>();

        try {
            // Asynchronously retrieve all documents
            ApiFuture<QuerySnapshot> future = db.collection("Product")
                    .orderBy("addedDate", Query.Direction.DESCENDING)
                    .limit(16).get();
            List<QueryDocumentSnapshot> products = future.get().getDocuments();
            for (QueryDocumentSnapshot product : products) {
                map.put(product.getId(), product.toObject(Product.class));
            }
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return map;
    }

    public void addNewProduct(String name, String description,
            String category, String brand, String color,
            String thumbnailUrl, String listImg, double price, int discout) {

        String[] strSplit = listImg.split("\n");
        List<String> strListImg = new ArrayList<String>(Arrays.asList(strSplit));
        Product p = new Product(name, description, price, discout, thumbnailUrl, strListImg, new Date(), category, brand, color);
        ApiFuture<DocumentReference> future = db.collection("Product").add(p);
    }

    public void updateProduct(String id, String name, String description,
            String category, String brand, String color,
            String thumbnailUrl, String listImg, double price, int discount) {
        DocumentReference docref = db.collection("Product").document(id);
        String[] strSplit = listImg.split("\n");
        ArrayList<String> strListImg = new ArrayList<String>(
                Arrays.asList(strSplit));
        ApiFuture<WriteResult> future = docref
                .update("name", name,
                        "description", description,
                        "category", category,
                        "brand", brand,
                        "color", color,
                        "thumbnail", thumbnailUrl,
                        "listImg", strListImg,
                        "price", price,
                        "discount", discount);
    }

    public void deleteProduct(String id) {
        try {
            // Asynchronously retrieve all documents
            ApiFuture<WriteResult> future = db.collection("Product").document(id).delete();

        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int countProduct() {
        CollectionReference collection = db.collection("Product");
        AggregateQuerySnapshot snapshot = null;
        try {
            snapshot = collection.count().get().get();
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (int) snapshot.getCount();
    }

    public Map<String, Product> getProductByFilter(String min, String max, String brands[],String categories[], String colors[], String search) {
        Map<String, Product> map = new HashMap<>();
        CollectionReference productRef = db.collection("Product");

        try {
            Query query = productRef;
            if (min != null && !min.isEmpty()) {
                double minPrice = Double.parseDouble(min);
                query = query.whereGreaterThanOrEqualTo("price", minPrice);
            }
            
            if (max != null && !max.isEmpty()) {
                double maxPrice = Double.parseDouble(max);
                query = query.whereLessThanOrEqualTo("price", maxPrice);
            }
            
            if (brands != null) {
                query = query.whereIn("brand", Arrays.asList(brands));
            }
            
            if (categories != null) {
                query = query.whereIn("category", Arrays.asList(categories));
            }
            
            if (colors != null) {
                query = query.whereIn("color", Arrays.asList(colors));
            }
            
            List<QueryDocumentSnapshot> products = query.get().get().getDocuments();
            for (QueryDocumentSnapshot product : products) {
                map.put(product.getId(), product.toObject(Product.class));
            }
            
            if (search != null && !search.isEmpty()) {
                for (Iterator<Map.Entry<String, Product>> it = map.entrySet().iterator(); it.hasNext(); ) {
                    Map.Entry<String, Product> entry = it.next();
                    Product pro = entry.getValue();
                    if (!pro.getName().toLowerCase().contains(search.toLowerCase())) {
                        it.remove();
                    }
                }
            }
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return map;
    }
    
    public Map<String, Product> getAllProductByName(String value) {
        Map<String, Product> matchingProducts = new HashMap<>();
        
        try {
            // Create a map to hold the matching products
            ApiFuture<QuerySnapshot> future = db.collection("Product").get();
            List<QueryDocumentSnapshot> products = future.get().getDocuments();

            // Loop through the documents and add matching products to the map
            for (QueryDocumentSnapshot document : products) {
                // Deserialize the document into a Product object
                Product product = document.toObject(Product.class);

                // Check if the name field contains or equals the search value
                if (value != null) {
                    if (product.getName().toLowerCase().contains(value.toLowerCase())) {
                        matchingProducts.put(document.getId(), product);
                    }
                } else {
                    matchingProducts.put(document.getId(), product);
                }
            }
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return matchingProducts;
    }

}
