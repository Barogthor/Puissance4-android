package com.tpandroid.esgi.puissance4.firebase

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.tpandroid.esgi.puissance4.observer.ScoreObservable
import com.tpandroid.esgi.puissance4.observer.ScoreObserver
import java.util.ArrayList

class ScoreEventListener: ValueEventListener, ScoreObservable{
    private var listObservable: ArrayList<ScoreObserver> = ArrayList()
    var score: Score? = null

    override fun onDataChange(dataSnapshot: DataSnapshot) {
        score = dataSnapshot.getValue(Score::class.java)
        Log.i("SCORE","=================================================================")
        Log.e("SCORE","loadScore:onDataChange")
        notifyObserver()


    }

    override fun onCancelled(databaseError: DatabaseError) {
        // Getting Post failed, log a message
        Log.e("SCORE","loadScore:onCancelled ", databaseError.toException())
        // ...
    }


    override fun addObserver(o: ScoreObserver) {
        if(!listObservable.contains(o))
            listObservable.add(o)
    }

    override fun notifyObserver() {
        for (a in listObservable) {
            a.update(score!!)
        }
    }

    override fun removeObserver(o: ScoreObserver) {
        listObservable.remove(o)
    }

}