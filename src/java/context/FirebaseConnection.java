package context;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.FileInputStream;

public class FirebaseConnection {
    public static Firestore getFirestoreInstance() {
        // Define the path to the Google Services JSON file
        String serviceAccountFile = "D:\\LibraryJavaWeb\\service-account2.json";

        try {
            // Create the FirebaseOptions object
            FileInputStream serviceAccount = new FileInputStream(serviceAccountFile);
            FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                .setDatabaseUrl("https://blue-green-644b3.firebaseio.com/")
                .setDatabaseUrl("https://bluegreen-store.firebaseio.com/")
//                .setDatabaseUrl("https://bluegreen2.firebaseio.com/")
                .build();

            // Initialize Firebase
            FirebaseApp.initializeApp(options);

            System.out.println("Firebase Cloud Firestore connection established successfully.");

        } catch (Exception e) {
            System.err.println("Error establishing Firebase Cloud Firestore connection: " + e.getMessage());
        }
        return FirestoreClient.getFirestore();
    }
}
