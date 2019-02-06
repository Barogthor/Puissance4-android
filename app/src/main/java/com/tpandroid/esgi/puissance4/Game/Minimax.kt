package com.tpandroid.esgi.puissance4.Game

class Minimax(
    private val agents : Array<ScoreAgent>,
    private val maxPlayer : Board.Token,
    private val minPlayer : Board.Token
) {
    fun minimax(depth : Int, board : Board) : Int {
        val result = max(depth, board, Int.MIN_VALUE, Int.MAX_VALUE, -1)

        println("Result for ${result.first} --> ${result.second}")

        return result.first
    }

    private fun max(depth : Int, board : Board, alpha : Int, beta : Int, fromPosition : Int) : Pair<Int, Int> {
        if(depth == 0) { return Pair(fromPosition, board.scoring(agents, maxPlayer, minPlayer)) }
        if(board.victory() != null) { return Pair(fromPosition, board.scoring(agents, maxPlayer, minPlayer)) }
        var best = Pair(fromPosition, Int.MIN_VALUE)
        var localAlpha = alpha

        for(i in 0..6) {
            val boardClone = board.clone() as Board

            if(!boardClone.insert(i, maxPlayer)) { continue }

            val value = min(depth - 1, boardClone, localAlpha, beta, i)

            if(best.second < value.second) { best = value }
            localAlpha = maxOf(localAlpha, best.second)

            if(beta <= localAlpha) { break }
        }

        return if(fromPosition == -1) best else Pair(fromPosition, best.second)
    }

    private fun min(depth : Int, board : Board, alpha : Int, beta : Int, fromPosition : Int) : Pair<Int, Int> {
        if(depth == 0) { return Pair(fromPosition, board.scoring(agents, maxPlayer, minPlayer)) }
        if(board.victory() != null) { return Pair(fromPosition, board.scoring(agents, maxPlayer, minPlayer)) }
        var best = Pair(fromPosition, Int.MAX_VALUE)
        var localBeta = beta

        for(i in 0..6) {
            val boardClone = board.clone() as Board

            if(!boardClone.insert(i, minPlayer)) { continue }

            val value = max(depth - 1, boardClone, alpha, localBeta, i)

            if(best.second > value.second) { best = value }
            localBeta = minOf(localBeta, best.second)

            if(localBeta <= alpha) { break }
        }

        return if(fromPosition == -1) best else Pair(fromPosition, best.second)
    }
}
