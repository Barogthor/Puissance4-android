package com.tpandroid.esgi.puissance4.Game

import android.util.Log
import java.io.File
import java.io.FileWriter
import java.util.*

class Cache(val directory: File) {
    fun getScore(difficulty: Int): Pair<Int, Int> {
        val file = loadFile(difficulty)
        return loadScoreFromFile(file)
    }

    fun setScore(difficulty: Int, score: Pair<Int, Int>) : Boolean {
        val file = loadFile(difficulty)
        return writeScoreToFile(file, score)
    }

    fun incrVictory(difficulty: Int): Boolean {
        val file = loadFile(difficulty)
        val score = loadScoreFromFile(file)
        return writeScoreToFile(file, Pair(score.first + 1, score.second))
    }

    fun incrDefeat(difficulty: Int): Boolean {
        val file = loadFile(difficulty)
        val score = loadScoreFromFile(file)
        return writeScoreToFile(file, Pair(score.first, score.second + 1))
    }

    private fun loadFile(difficulty: Int): File {
        return File(directory, difficulty.toString())
    }

    private fun loadScoreFromFile(file: File): Pair<Int, Int> {
        val scanner = Scanner(file)

        val victories = try {
            scanner.nextInt()
        } catch (e : InputMismatchException) {
            0
        }

        val defeat = try {
            scanner.nextInt()
        } catch (e : InputMismatchException) {
            0
        }

        scanner.close()

        return Pair(victories, defeat)
    }

    private fun writeScoreToFile(file : File, score : Pair<Int, Int>) : Boolean {
        val writer = FileWriter(file)

        writer.write("\n")
        writer.write(score.first)
        writer.write(score.second + 1)

        writer.close()

        return true
    }
}
