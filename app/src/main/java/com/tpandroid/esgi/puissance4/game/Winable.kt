package com.tpandroid.esgi.puissance4.game

interface Winable {
    enum class Tokens {
        Vertical,
        Horizontal,
        Diagonal
    }

    fun win(board: Array<Board.Token>, x : Int, y : Int) : Boolean

    fun token() : Winable.Tokens
}

class HorizontalWin : Winable {
    override fun win(board: Array<Board.Token>, x: Int, y: Int): Boolean {
        repeat(x) i@{ i ->
            var token_counter = 0
            var previous_piece = Board.Token.Empty

            repeat(y) j@{ j ->
                val token = board[i * 7 + j]

                if(token == Board.Token.Empty) {
                    token_counter = 0
                    previous_piece = token
                    return@j
                } else if(previous_piece == Board.Token.Empty || previous_piece != token) {
                    token_counter = 0
                    previous_piece = token
                }

                if(previous_piece == token) { token_counter += 1 }
                if(token_counter == 4) { return true }
            }
        }

        return false
    }

    override fun token(): Winable.Tokens {
        return Winable.Tokens.Horizontal
    }
}
