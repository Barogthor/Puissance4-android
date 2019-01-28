package com.tpandroid.esgi.puissance4.Game

import org.junit.Test

import org.junit.Assert.*

class ScoreCenterTest {
    @Test
    fun withNoTokenInMiddle() {
        val board = Array(6 * 7) { Board.Token.Empty }
        val subject = CenterAgent()

        assertEquals(subject.calculate(board, 7, 6, Board.Token.Player1), 0)
    }

    @Test
    fun withOneTokenInMiddle() {
        val board = Array(6 * 7) { Board.Token.Empty }
        board[4 + 5 * 7] = Board.Token.Player1
        val subject = CenterAgent()

        assertEquals(subject.calculate(board, 7, 6, Board.Token.Player1), 4)
    }

    @Test
    fun withTwoTokenInMiddle() {
        val board = Array(6 * 7) { Board.Token.Empty }
        board[4 + 5 * 7] = Board.Token.Player1
        board[4 + 4 * 7] = Board.Token.Player1
        val subject = CenterAgent()

        assertEquals(subject.calculate(board, 7, 6, Board.Token.Player1), 8)
    }

    @Test
    fun withOneTokenInMiddleAndMisc() {
        val board = Array(6 * 7) { Board.Token.Empty }
        board[4 + 5 * 7] = Board.Token.Player1
        board[3 + 5 * 7] = Board.Token.Player1
        board[5 + 5 * 7] = Board.Token.Player1
        val subject = CenterAgent()

        assertEquals(subject.calculate(board, 7, 6, Board.Token.Player1), 4)
    }

    @Test
    fun withWrongToken() {
        val board = Array(6 * 7) { Board.Token.Empty }
        board[4 + 5 * 7] = Board.Token.Player2
        val subject = CenterAgent()

        assertEquals(subject.calculate(board, 7, 6, Board.Token.Player1), 0)
    }
}
