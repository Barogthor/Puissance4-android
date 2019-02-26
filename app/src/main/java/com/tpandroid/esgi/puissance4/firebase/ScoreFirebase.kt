package com.tpandroid.esgi.puissance4.firebase

import android.util.Log
import com.google.firebase.database.*

class ScoreFirebase {


    companion object {
        private var database = FirebaseDatabase.getInstance()
    }


    private lateinit var scoreRef: DatabaseReference

    constructor(user: String){
        scoreRef = database.reference.child(user)
    }

    fun updateScore(score: Score?){
        if(score!=null)
            scoreRef.setValue(score)
    }

    fun getUserScore(): Score?{
        var score: Score? = null
        val scoreListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                score = dataSnapshot.getValue(Score::class.java)
                print("========================fuck=======================")
                println(score)
                // ...
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("ScoreListener", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        scoreRef.addListenerForSingleValueEvent(scoreListener)
        return score
    }

}