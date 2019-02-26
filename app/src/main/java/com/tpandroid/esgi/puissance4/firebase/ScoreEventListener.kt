package com.tpandroid.esgi.puissance4.firebase

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ScoreEventListener: ValueEventListener{
    var score: Score? = null

    override fun onDataChange(dataSnapshot: DataSnapshot) {
        // Get Post object and use the values to update the UI
        score = dataSnapshot.getValue(Score::class.java)
        Log.i("SCORE",score.toString())
        print("fuck")

    }

    override fun onCancelled(databaseError: DatabaseError) {
        // Getting Post failed, log a message
        Log.w("ScoreListener", "loadPost:onCancelled", databaseError.toException())
        // ...
    }

}