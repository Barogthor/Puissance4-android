package com.tpandroid.esgi.puissance4.dummy

import java.util.*

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<DummyItem> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, DummyItem> = HashMap()

    private val COUNT = 25

    init {
        var r = Random()
        // Add some sample items.
        for (i:Number in 1..COUNT) {
            addItem(createDummyItem(i,"Toto",r.nextInt(50),r.nextInt(50)))
        }
    }

    private fun addItem(item: DummyItem) {
        ITEMS.add(item)
        ITEM_MAP.put(item.rank.toString(), item)
    }

    private fun createDummyItem(rank: Number, player: String, victory: Number, defeat: Number ): DummyItem {
        return DummyItem(rank, player, victory,defeat)
    }


    /**
     * A dummy item representing a piece of content.
     */
    data class DummyItem(val rank: Number, val player: String, val victory: Number, val defeat: Number) {
        override fun toString(): String = "$player[$victory : $defeat]"
    }
}
