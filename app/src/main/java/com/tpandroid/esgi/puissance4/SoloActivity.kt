package com.tpandroid.esgi.puissance4

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class SoloActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.solo_layout)
    }


    fun playEasy(view: View?){
        var intent = Intent(this,PlayBoardActivity::class.java).apply {  }
        startActivity(intent)
    }

    fun playNormal(view: View?){
        var intent = Intent(this,PlayBoardActivity::class.java).apply {  }
        startActivity(intent)
    }

    fun playHard(view: View?){
        var intent = Intent(this,PlayBoardActivity::class.java).apply {  }
        startActivity(intent)
    }

}
