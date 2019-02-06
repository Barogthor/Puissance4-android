package com.tpandroid.esgi.puissance4

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_layout)

    }


    fun goToSolo(view: View?){
        var intent = Intent(this,SoloActivity::class.java).apply {  }
        startActivity(intent)
    }

    fun goToMulti(view: View?){
        var intent = Intent(this,MultiActivity::class.java).apply {  }
        startActivity(intent)
    }

    fun goToProfil(view: View?){
        var intent = Intent(this,ProfilActivity::class.java).apply {  }
        startActivity(intent)
    }
}
