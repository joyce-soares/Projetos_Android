package com.joyce.organizze.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfiguracaoFirebase {

    private static FirebaseAuth auth;  //sendo static ele vai sempre ser o mesmo independente de quantas instancias eu crie
    private static DatabaseReference db;

    //retorna a instancia do firabaseAuth
    public static FirebaseAuth getFirebaseAuth(){

        if(auth == null) {
            auth = FirebaseAuth.getInstance();
        }
            return auth;
    }

    //retorna a instancia do firebaseDatabase
    public static DatabaseReference getFirebaseDb(){

        if(db == null) {
           db = FirebaseDatabase.getInstance().getReference();
        }
        return db;
    }
}