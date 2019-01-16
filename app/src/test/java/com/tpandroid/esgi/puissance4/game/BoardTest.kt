package com.tpandroid.esgi.puissance4.game

import org.junit.Test

import org.junit.Assert.*

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
        board.insert(3, Board.Token.Player1)

        println(board)
        println(board.victory())
        board.insert(1, Board.Token.Player1)
        board.insert(1, Board.Token.Player1)
        println(board)
        println(board.victory())
    }
}
