package com.tpandroid.esgi.puissance4.Game.Score

import com.tpandroid.esgi.puissance4.Game.Board
import com.tpandroid.esgi.puissance4.Game.CountAgent
import org.junit.Assert
import org.junit.Test

class CountAgentTest {
    @Test
    fun withNoToken() {
        val board = Array(6 * 7) { Board.Token.Empty }
        val subject = CountAgent()

        Assert.assertEquals(0, subject.calculate(board, 6, 7, Board.Token.Player1))
    }

    @Test
    fun withOneToken() {
        val board = Array(6 * 7) { Board.Token.Empty }
        board[4 + 5 * 7] = Board.Token.Player1
        val subject = CountAgent()

        Assert.assertEquals(0, subject.calculate(board, 6, 7, Board.Token.Player1))
    }

    @Test
    fun withTwoNonAlignedTokens() {
        val board = Array(6 * 7) { Board.Token.Empty }
        board[0 + 5 * 7] = Board.Token.Player1
        board[5 + 5 * 7] = Board.Token.Player1
        val subject = CountAgent()

        Assert.assertEquals(0, subject.calculate(board, 6, 7,Board.Token.Player1))
    }

    @Test
    fun withTwoAlignedHorizontalTokens() {
        val board = Array(6 * 7) { Board.Token.Empty }
        board[0 + 5 * 7] = Board.Token.Player1
        board[1 + 5 * 7] = Board.Token.Player1
        val subject = CountAgent()

        Assert.assertEquals(2, subject.calculate(board, 6, 7,Board.Token.Player1))
    }

    @Test
    fun withTwoAlignedHorizontalWithGapeTokens() {
        val board = Array(6 * 7) { Board.Token.Empty }
        board[0 + 5 * 7] = Board.Token.Player1
        board[2 + 5 * 7] = Board.Token.Player1
        val subject = CountAgent()

        Assert.assertEquals(2, subject.calculate(board, 6, 7,Board.Token.Player1))
    }

    @Test
    fun withTwoAlignedHorizontalWithInterceptionTokens() {
        val board = Array(6 * 7) { Board.Token.Empty }
        board[0 + 5 * 7] = Board.Token.Player1
        board[1 + 5 * 7] = Board.Token.Player2
        board[2 + 5 * 7] = Board.Token.Player1
        val subject = CountAgent()

        Assert.assertEquals(0, subject.calculate(board, 6, 7,Board.Token.Player1))
    }

    @Test
    fun withTwoAlignedVerticalTokens() {
        val board = Array(6 * 7) { Board.Token.Empty }
        board[0 + 5 * 7] = Board.Token.Player1
        board[0 + 4 * 7] = Board.Token.Player1
        val subject = CountAgent()

        Assert.assertEquals(2, subject.calculate(board, 6, 7,Board.Token.Player1))
    }

    @Test
    fun withTwoAlignedVerticalWithInterceptionTokens() {
        val board = Array(6 * 7) { Board.Token.Empty }
        board[0 + 5 * 7] = Board.Token.Player1
        board[0 + 4 * 7] = Board.Token.Player2
        board[0 + 3 * 7] = Board.Token.Player1
        val subject = CountAgent()

        Assert.assertEquals(0, subject.calculate(board, 6, 7,Board.Token.Player1))
    }

    @Test
    fun withVerticalAndHorizontalAndDiagonal() {
        val board = Array(6 * 7) { Board.Token.Empty }
        board[0 + 5 * 7] = Board.Token.Player1
        board[0 + 4 * 7] = Board.Token.Player1
        board[1 + 5 * 7] = Board.Token.Player1
        val subject = CountAgent()

        Assert.assertEquals(6, subject.calculate(board, 6, 7,Board.Token.Player1))
    }

    @Test
    fun withTwoDiagonal() {
        val board = Array(6 * 7) { Board.Token.Empty }
        board[0 + 5 * 7] = Board.Token.Player1
        board[1 + 4 * 7] = Board.Token.Player1
        val subject = CountAgent()

        Assert.assertEquals(2, subject.calculate(board, 6, 7,Board.Token.Player1))
    }

    @Test
    fun withThreeDiagonal() {
        val board = Array(6 * 7) { Board.Token.Empty }
        board[0 + 5 * 7] = Board.Token.Player1
        board[1 + 4 * 7] = Board.Token.Player1
        board[2 + 3 * 7] = Board.Token.Player1
        val subject = CountAgent()

        Assert.assertEquals(5 + 2, subject.calculate(board, 6, 7,Board.Token.Player1))
    }

    fun toString(board : Array<Board.Token>) : String {
        val builder = StringBuilder();

        builder.append("----------\n")
        repeat(6) { i ->
            repeat(7) { j ->
                val piece = board[i * 7 + j]
                var value = '.'

                if(piece == Board.Token.Player1) { value = '1' }
                if(piece == Board.Token.Player2) { value = '2' }

                builder.append("| ${value} ")
            }
            builder.append("\n----------\n")
        }

        return builder.toString()
    }
}
