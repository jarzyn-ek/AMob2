package com.example.amob

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

val DATABASE_NAME = "AMOB"
val TABLE_NAME = "USERS"
val COL_ID = "ID"
val COL_NAME = "NICK"
val COL_SCORE = "SCORE"

class DatabaseHandler(context: Context) :SQLiteOpenHelper(context, DATABASE_NAME,null,1) {
    override fun onCreate(db: SQLiteDatabase?) {

        val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " VARCHAR(50)," +
                COL_SCORE + " INTEGER)"

        db?.execSQL(createTable);
    }

    val allScores:List<ScoreTableRow>
        get() {
            val listScoreTableRow = ArrayList<ScoreTableRow>()
            val selectQuery = "SELECT * FROM " + TABLE_NAME + " ORDER BY SCORE DESC"
            val db = this.writableDatabase
            val cursor = db.rawQuery(selectQuery,null)
            if (cursor.moveToFirst()) {
                do {
                    val scoreTableRow=ScoreTableRow()
                    scoreTableRow.id = cursor.getInt(cursor.getColumnIndex(COL_ID))
                    scoreTableRow.name= cursor.getString(cursor.getColumnIndex(COL_NAME))
                    scoreTableRow.score = cursor.getInt(cursor.getColumnIndex(COL_SCORE))

                    listScoreTableRow.add(scoreTableRow)
                }while(cursor.moveToNext())
            }
            return listScoreTableRow
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(nickname : String,score : Int) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_NAME,nickname)
        cv.put(COL_SCORE,score)
        var result = db.insert(TABLE_NAME, null, cv)
    }

}