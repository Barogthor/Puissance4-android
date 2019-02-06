package com.tpandroid.esgi.puissance4.Game

import org.junit.Test
import java.util.*


class MinimaxTest {
    companion object {
        val subject = Minimax(arrayOf(CenterAgent(), CountAgent()), Board.Token.Player2, Board.Token.Player1)
        val victories = arrayOf(
            HorizontalWin(),
            VerticalWin(),
            LeftDiagonalWin(),
            RightDiagonalWin()
        )
        val gravity = NewtonGravity()
    }

    @Test
    fun withEmptyBoardDepth1() {
        val complexity = 7
        val board = Board(gravity, victories)

        val players = mapOf(
            Pair(Board.Token.Player1, Board.Token.Player2),
            Pair(Board.Token.Player2, Board.Token.Player1)
        )
        val reader = Scanner(System.`in`)

        var actualPlayer = Board.Token.Player2

        while(board.victory() == null) {
            actualPlayer = players[actualPlayer] ?: Board.Token.Empty

            if(actualPlayer == Board.Token.Player1) {
                println(board.toString())

                val input = reader.nextInt()

                board.insert(input, actualPlayer)

            } else if(actualPlayer == Board.Token.Player2) {
                val input = subject.minimax(complexity, board)

                board.insert(input, actualPlayer)

            } else {
                println("\n\n\n\n\n WTF \n\n\n\n\n")
            }
        }

        println(board.toString())
        println("Victory for $actualPlayer")
    }
}
