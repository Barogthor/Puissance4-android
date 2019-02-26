package com.tpandroid.esgi.puissance4.firebase

import android.util.Log
import com.google.firebase.database.*
import com.tpandroid.esgi.puissance4.observer.ScoreObservable
import com.tpandroid.esgi.puissance4.observer.ScoreObserver
import java.sql.Timestamp

class ScoreFirebase {
    companion object {
        private var database = FirebaseDatabase.getInstance()
    }

    private val scoreListener = ScoreEventListener()
    private lateinit var scoreRef: DatabaseReference

    constructor(user: String){
        scoreRef = database.reference.child("users").child(user)
    }

    fun updateScore(score: Score?){
        if(score!=null) {
            println("[DEBUG] UPDATE")
            scoreRef.setValue(score).addOnFailureListener(FirebaseFailureListener())
                .addOnSuccessListener(FirebaseSucessListener())
        }
    }

    fun getUserScore(scoreObserver: ScoreObserver){
        var score: Score? = null
        scoreListener.addObserver(scoreObserver)
        scoreRef.addListenerForSingleValueEvent(scoreListener)
        updateTimestamp()
    }

    fun updateTimestamp(){
        scoreRef.child("timestamp_read").setValue(System.currentTimeMillis()/1000)
            .addOnFailureListener(FirebaseFailureListener())
            .addOnSuccessListener(FirebaseSucessListener())
    }

}