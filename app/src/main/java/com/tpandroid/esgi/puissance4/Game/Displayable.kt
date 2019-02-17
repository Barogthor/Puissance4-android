package com.tpandroid.esgi.puissance4.Game

import android.graphics.drawable.Drawable
import android.widget.GridLayout

interface Displayable {
    fun draw(board: Array<Board.Token>, x : Int, y : Int)
}

class GridLayoutDisplayer(val grid : GridLayout, val outputs : Map<Board.Token, Drawable>) : Displayable {
    override fun draw(board: Array<Board.Token>, x : Int, y : Int) {
        repeat(x) { i ->
            repeat(y) { j ->
                val index = i * y + j
                grid.getChildAt(j * x + i).background = outputs[board[i * y + j]]
            }
        }
    }
}
