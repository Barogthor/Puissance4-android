package com.tpandroid.esgi.puissance4.observer

interface ScoreObservable {
    fun addObserver(o: ScoreObserver)
    fun removeObserver(o: ScoreObserver)
    fun notifyObserver()
}
