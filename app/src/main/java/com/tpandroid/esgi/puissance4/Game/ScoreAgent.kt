package com.tpandroid.esgi.puissance4.Game

abstract class ScoreAgent {
    abstract fun calculate(board: Array<Board.Token>, x: Int, y: Int, actual : Board.Token) : Int
}

class CenterAgent : ScoreAgent() {
    companion object {
        val INCREMENTER = 4
    }

    override fun calculate(board: Array<Board.Token>, x: Int, y: Int, actual : Board.Token) : Int {
        var counter = 0
        repeat(y) {
            if(board[2 + it * x] == actual) { counter += INCREMENTER }
        }

        return counter
    }
}

class CountAgent : ScoreAgent() {
    companion object {
        val INCREMENT_COUNTER = mapOf(
            Pair(0, 0),
            Pair(1, 0),
            Pair(2, 2),
            Pair(3, 5),
            Pair(4, 1000)
        )
    }

    override fun calculate(board: Array<Board.Token>, x: Int, y: Int, actual: Board.Token): Int {
        var counter = 0
        for(i in 0..(x - 1)) {
            for(j in 0..(y - 1)) {
                val piece = board[i * 7 + j]
                if(piece != actual) { continue }

                counter += horizontal(board, x, y, i, j, 1, actual)
                counter += vertical(board, x, y, i, j, 1, actual)
                counter += diagonal(board, x, y, i, j, 1, actual)
                counter += diagonal(board, x, y, i, j, -1, actual)
            }
        }

        return counter
    }

    private fun horizontal(board: Array<Board.Token>, x: Int, y: Int, i : Int, j : Int, direction: Int, actual: Board.Token) : Int {
        var counter = 0

        var threshold = 0
        for(k in 0..3) {
            if(j + k * direction >= 7 || j + k * direction < 0) { break }
            val encounter = board[i * 7 + j + k * direction]

            if(encounter == actual) {
                threshold += 1

                counter -= INCREMENT_COUNTER[threshold - 1] ?: 0
                counter += INCREMENT_COUNTER[threshold] ?: 0
            } else if(encounter == Board.Token.Empty) {
                continue
            } else {
                break
            }
        }

        return counter
    }

    private fun vertical(board: Array<Board.Token>, x: Int, y: Int, i : Int, j : Int, direction: Int, actual: Board.Token) : Int {
        var counter = 0

        var threshold = 0
        for(k in 0..3) {
            if(i + k * direction >= 6 || i + k * direction < 0) { break }
            val encounter = board[(i + k * direction) * 7 + j]

            if(encounter == actual) {
                threshold += 1

                counter -= INCREMENT_COUNTER[threshold - 1] ?: 0
                counter += INCREMENT_COUNTER[threshold] ?: 0
            } else if(encounter == Board.Token.Empty) {
                continue
            } else {
                break
            }
        }

        return counter
    }

    private fun diagonal(board: Array<Board.Token>, x: Int, y: Int, i : Int, j : Int, direction: Int, actual: Board.Token) : Int {
        var threshold = 0
        for(k in 0..3) {
            if(i + k >= 6 || i + k * direction < 0) { break }
            if(j + k * direction >= 7 || j + k * direction < 0) { break }
            val encounter = board[(i + k) * 7 + (j + k * direction)]

            if(encounter == actual) {
                threshold += 1
            } else if(encounter == Board.Token.Empty) {
                continue
            } else {
                break
            }
        }

        return INCREMENT_COUNTER[threshold] ?: 0
    }
}
