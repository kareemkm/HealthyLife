package com.task.healthylife.firebase

import com.google.firebase.auth.FirebaseAuth

object FirebaseAuth {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun register(userName: String, password: String, onResult: (Boolean) -> Unit) {
        firebaseAuth.createUserWithEmailAndPassword(userName, password)
            .addOnCompleteListener { success ->
                onResult(success.isSuccessful)
            }
    }

    fun login(userName: String,password: String,onResult: (Boolean) -> Unit){
        firebaseAuth.signInWithEmailAndPassword(userName,password)
            .addOnCompleteListener{success ->
                onResult(success.isSuccessful)
            }
    }

    fun logout(){
        firebaseAuth.signOut()
    }

    fun getCurrentUser() = firebaseAuth.currentUser

}