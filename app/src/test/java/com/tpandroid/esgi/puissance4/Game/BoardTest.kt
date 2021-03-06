package com.tpandroid.esgi.puissance4.Game

import org.junit.Test

class BoardTest {
    @Test
    fun insert() {
        val victories = arrayOf(
            HorizontalWin(),
            VerticalWin(),
            LeftDiagonalWin(),
            RightDiagonalWin()
        )

        val board = Board(NewtonGravity(), victories)

        board.insert(3, Board.Token.Player1)
        board.insert(1, Board.Token.Player1)
        board.insert(2, Board.Token.Player1)
        board.insert(2, Board.Token.Player1)

        println(board)
        println(board.victory())
        board.insert(1, Board.Token.Player1)
        board.insert(1, Board.Token.Player1)
        println(board)
        println(board.victory())

        board.insert(1, Board.Token.Player2)
        board.insert(2, Board.Token.Player2)
        board.insert(3, Board.Token.Player2)
        board.insert(4, Board.Token.Player2)
        println(board)
        println(board.victory())

        board.insert(4, Board.Token.Player1)
        board.insert(5, Board.Token.Player2)
        board.insert(5, Board.Token.Player2)
        board.insert(5, Board.Token.Player1)
        board.insert(6, Board.Token.Player2)
        board.insert(6, Board.Token.Player2)
        board.insert(6, Board.Token.Player2)
        board.insert(6, Board.Token.Player1)
        println(board)
        println(board.victory())
    }
}
