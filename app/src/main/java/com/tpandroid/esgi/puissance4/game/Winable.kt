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

class VerticalWin : Winable {
    override fun win(board: Array<Board.Token>, x: Int, y: Int): Boolean {
        repeat(y) j@{ j ->
            var token_counter = 0
            var previous_piece = Board.Token.Empty

            repeat(x) i@{ i ->
                val token = board[i * 7 + j]

                if(token == Board.Token.Empty) {
                    token_counter = 0
                    previous_piece = token
                    return@i
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
        return Winable.Tokens.Vertical
    }
}

class LeftDiagonalWin : Winable {
    override fun win(board: Array<Board.Token>, x: Int, y: Int): Boolean {
        repeat(x - 3) i@{ i ->
            repeat(y - 3) j@{ j ->
                val piece = board[i * 7 + j]
                if(piece == Board.Token.Empty) { return@j }
                if(board[(i + 1) * 7 + (j + 1)] == piece &&
                    board[(i + 2) * 7 + (j + 2)] == piece &&
                    board[(i + 3) * 7 + (j + 3)] == piece) {
                    return true
                }
            }
        }
        return false
    }

    override fun token(): Winable.Tokens {
        return Winable.Tokens.Diagonal
    }
}

class RightDiagonalWin : Winable {
    override fun win(board: Array<Board.Token>, x: Int, y: Int): Boolean {
        for(i in 0..(x - 3)) {
            for(j in 3..y) {
                val piece = board[i * 7 + j]
                if(piece == Board.Token.Empty) { continue }
                if(board[(i + 1) * 7 + (j - 1)] == piece &&
                    board[(i + 2) * 7 + (j - 2)] == piece &&
                    board[(i + 3) * 7 + (j - 3)] == piece) {
                    return true
                }
            }
        }
        return false
    }

    override fun token(): Winable.Tokens {
        return Winable.Tokens.Diagonal
    }
}
