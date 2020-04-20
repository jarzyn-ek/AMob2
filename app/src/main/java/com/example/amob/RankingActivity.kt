package com.example.amob

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.ranking_activity.*

class RankingActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ranking_activity)

        var NAMES=  arrayOf<TextView>(this.textView1, this.textView3, this.textView5, this.textView7, this.textView9, this.textView11, this.textView13, this.textView15, this.textView17, this.textView20)
        var SCORES = arrayOf<TextView>(this.textView2, this.textView4, this.textView6, this.textView8, this.textView10, this.textView12, this.textView14, this.textView16, this.textView18, this.textView20)

        var i = 0
        for (score in DatabaseHandler(this).allScores) {
            NAMES[i].text = score.name
            SCORES[i].text = score.score.toString()
            i++
            if (i > 9) {
                break
            }
        }

        newGame.setOnClickListener() {
            val intent = Intent(this, MainActivity::class.java)
            finish()
            startActivity(intent)
        }
    }
}