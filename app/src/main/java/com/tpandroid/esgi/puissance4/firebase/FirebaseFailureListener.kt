package com.tpandroid.esgi.puissance4.firebase

import android.util.Log
import com.google.android.gms.tasks.OnFailureListener
import java.lang.Exception

class FirebaseFailureListener: OnFailureListener {
    override fun onFailure(p0: Exception) {
        Log.e("FIREBASE","DAMN YOU FIREBASE")
    }
}