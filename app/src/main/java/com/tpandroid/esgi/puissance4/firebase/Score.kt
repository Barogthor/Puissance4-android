package com.tpandroid.esgi.puissance4.firebase

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class Score {
    var easy_defeat: Long? = null
    var easy_victory: Long? = null
    var normal_defeat: Long? = null
    var normal_victory: Long? = null
    var hard_victory: Long? = null
    var hard_defeat: Long? = null

    constructor() {}

    constructor(
        easy_defeat: Long?,
        easy_victory: Long?,
        normal_defeat: Long?,
        normal_victory: Long?,
        hard_victory: Long?,
        hard_defeat: Long?
    ) {
        this.easy_defeat = easy_defeat
        this.easy_victory = easy_victory
        this.normal_defeat = normal_defeat
        this.normal_victory = normal_victory
        this.hard_victory = hard_victory
        this.hard_defeat = hard_defeat
    }

    override fun toString(): String {
        return "Score{" +
                "easy_defeat=" + easy_defeat +
                ", easy_victory=" + easy_victory +
                ", normal_defeat=" + normal_defeat +
                ", normal_victory=" + normal_victory +
                ", hard_victory=" + hard_victory +
                ", hard_defeat=" + hard_defeat +
                '}'.toString()
    }

    companion object {
        private val a = 0
    }
}

