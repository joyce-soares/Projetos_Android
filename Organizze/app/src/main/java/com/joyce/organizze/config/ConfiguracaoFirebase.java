package com.joyce.organizze.config;

import com.google.firebase.auth.FirebaseAuth;

public class ConfiguracaoFirebase {

    private static FirebaseAuth auth;  //sendo static ele vai sempre ser o mesmo independente de quantas instancias eu crie

    //retorna a instancia do firabaseAuth
    public static FirebaseAuth getFirebaseAuth(){

        if(auth == null) {
            auth = FirebaseAuth.getInstance();
        }
            return auth;
    }
}
