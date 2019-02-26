package com.tpandroid.esgi.puissance4

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import com.tpandroid.esgi.puissance4.Game.Cache
import java.io.File

/**
 * Implementation of App Widget functionality.
 */
class LaderBoardWidget : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    companion object {

        internal fun updateAppWidget(
            context: Context, appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {

            val widgetText = context.getString(R.string.appwidget_text)
            // Construct the RemoteViews object
            val views = RemoteViews(context.packageName, R.layout.lader_board_widget)
//            views.setTextViewText(R.id.appwidget_text, widgetText)

            val cache = Cache(context.filesDir)
            val easy = cache.getScore(1)
            val medium = cache.getScore(2)
            val hard = cache.getScore(3)


            views.setTextViewText(R.id.easy_v, easy.first.toString())
            views.setTextViewText(R.id.easy_d, easy.second.toString())

            views.setTextViewText(R.id.medium_v, medium.first.toString())
            views.setTextViewText(R.id.medium_d, medium.second.toString())

            views.setTextViewText(R.id.hard_v, hard.first.toString())
            views.setTextViewText(R.id.hard_d, hard.second.toString())


            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}
