package com.tpandroid.esgi.puissance4.firebase

import android.util.Log
import com.google.android.gms.tasks.OnSuccessListener

class FirebaseSucessListener: OnSuccessListener<Void> {
    override fun onSuccess(p0: Void?) {
        Log.i("FIREBASE","HAIL FIREBASE !!!!!!!")
    }
}