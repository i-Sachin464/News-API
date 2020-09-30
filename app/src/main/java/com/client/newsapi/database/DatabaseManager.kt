package com.client.newsapi.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.client.newsapi.model.News
import org.json.JSONObject
import java.util.*

class DatabaseManager(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_NEWS_TABLE = ("CREATE TABLE " +
                TABLE_NEWS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_TITLE + " TEXT," + COLUMN_DESCRIPTION + " TEXT," +
                COLUMN_IMAGE_URL + " TEXT," + COLUMN_URL + " TEXT," +
                COLUMN_PUBLISHEDAT + " TEXT," + COLUMN_CONTENT + " TEXT," + COLUMN_AUTHOR + " TEXT" + ")")
        db.execSQL(CREATE_NEWS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }

    fun insertNews(news: News) {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(COLUMN_TITLE, news.title)
        values.put(COLUMN_DESCRIPTION, news.description)
        values.put(COLUMN_IMAGE_URL, news.urlToImage)
        values.put(COLUMN_URL, news.url)
        values.put(COLUMN_PUBLISHEDAT, news.publishedAt)
        values.put(COLUMN_CONTENT, news.content)
        values.put(COLUMN_AUTHOR, news.author)
        db.insert(TABLE_NEWS, null, values)

        db.close()
    }

    fun insertNewsList(news: List<News>) {
        val db = this.writableDatabase
        news.forEach {
            val values = ContentValues()
            values.put(COLUMN_TITLE, it.title)
            values.put(COLUMN_DESCRIPTION, it.description)
            values.put(COLUMN_IMAGE_URL, it.urlToImage)
            values.put(COLUMN_URL, it.url)
            values.put(COLUMN_PUBLISHEDAT, it.publishedAt)
            values.put(COLUMN_CONTENT, it.content)
            values.put(COLUMN_AUTHOR, it.author)
            db.insert(TABLE_NEWS, null, values)
        }
        db.close()
    }

    fun fetchNewsList(): List<News?> {
        val db = this.writableDatabase
        val dataLst = ArrayList<News?>()
        var jObj: JSONObject? = null
        val strQry = ("select * FROM "
                + TABLE_NEWS)
        var news: News? = null
        val cursor: Cursor = db.rawQuery(strQry, null)
        try {
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    news = News(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7)
                    )
                    dataLst.add(news)
                } while (cursor.moveToNext())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            cursor?.close()
        }
        return dataLst

    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "_dbNewsAPI.db"
        private const val TABLE_NEWS = "tbl_news"
        const val COLUMN_ID = "_id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_IMAGE_URL = "urlToImage"
        const val COLUMN_URL = "url"
        const val COLUMN_PUBLISHEDAT = "publishedAt"
        const val COLUMN_CONTENT = "content"
        const val COLUMN_AUTHOR = "author"
    }
}