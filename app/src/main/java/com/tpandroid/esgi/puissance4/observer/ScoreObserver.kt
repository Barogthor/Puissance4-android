package com.tpandroid.esgi.puissance4.observer

import com.tpandroid.esgi.puissance4.firebase.Score

interface ScoreObserver {
    fun update(score: Score)
}
