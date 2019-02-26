package com.tpandroid.esgi.puissance4

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.tpandroid.esgi.puissance4.firebase.Score
import com.tpandroid.esgi.puissance4.firebase.ScoreFirebase
import com.tpandroid.esgi.puissance4.observer.ScoreObserver

class ScoreActivity : AppCompatActivity(), ScoreObserver {
    val scoreFirebase = ScoreFirebase("florian")

    var wasVictorious: Boolean? = null
    var typeAI: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.score_layout)

        if(intent.hasExtra("victory")){
            wasVictorious=intent.getBooleanExtra("victory",false)
            if(intent.hasExtra("complexity"))
                when(intent.getIntExtra("complexity",5)){
                    2->typeAI="easy"
                    4->typeAI="normal"
                    5->typeAI="hard"
                }
        }

        scoreFirebase.getUserScore(this)
    }

    override fun update(score: Score) {
        if(intent.hasExtra("complexity")){
            intent.removeExtra("complexity")
            intent.removeExtra("victory")
            if(wasVictorious!=null){
                when(typeAI){
                    "easy" -> {
                        if(wasVictorious!!)
                            score.easy_victory++
                        else
                            score.easy_defeat++
                    }
                    "normal" -> {
                        if(wasVictorious!!)
                            score.normal_victory++
                        else
                            score.normal_defeat++
                    }
                    "hard" -> {
                        if(wasVictorious!!)
                            score.hard_victory++
                        else
                            score.hard_defeat++
                    }
                }
            }
            scoreFirebase.updateScore(score)
            scoreFirebase.getUserScore(this)
        } else {
            val easyD = findViewById<TextView>(R.id.tv_facilDefaite)
            easyD.text = score.easy_defeat.toString()
            val easyV = findViewById<TextView>(R.id.tv_facilVictoire)
            easyV.text = score.easy_victory.toString()
            val normalD = findViewById<TextView>(R.id.tv_normalDefaite)
            normalD.text = score.normal_defeat.toString()
            val normalV = findViewById<TextView>(R.id.tv_normalVictoire)
            normalV.text = score.normal_victory.toString()
            val hardD = findViewById<TextView>(R.id.tv_difficileDefaite)
            hardD.text = score.hard_defeat.toString()
            val hardV = findViewById<TextView>(R.id.tv_difficileVictoire)
            hardV.text = score.hard_victory.toString()
        }
    }
}
