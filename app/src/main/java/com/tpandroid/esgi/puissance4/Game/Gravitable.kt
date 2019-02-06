package com.tpandroid.esgi.puissance4.Game

interface Gravitable {
    fun fall(board: Array<Board.Token>, x : Int, y : Int)
}

class NewtonGravity : Gravitable {
    override fun fall(board: Array<Board.Token>, x : Int, y : Int) {
        repeat(x - 1) i@{ i ->
            repeat(y) j@{ j ->
                val piece = board[i * 7 + j]
                val next_piece = board[(i + 1) * 7 + j]

                if(piece == Board.Token.Empty) { return@j }
                if(next_piece != Board.Token.Empty) { return@j }

                board[(i + 1) * 7 + j] = piece
                board[i * 7 + j] = Board.Token.Empty
            }
        }
    }
}
