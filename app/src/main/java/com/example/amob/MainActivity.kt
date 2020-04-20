package com.example.amob
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    val MAX = 20
    var liczbaStrzalow = 0
    var liczbaStrzalowTrafionych = 0
    var liczbaPunktow = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        TimeUnit.SECONDS.sleep(3L);
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        init()

        input.setOnClickListener {
           game()
        }

        nowaGra.setOnClickListener {
            end()
        }

        nickname.setOnClickListener {
            showRanking()
        }
    }

    fun init() {
        spozaZakresu.visibility = View.INVISIBLE
        informacjaOWygranej.textSize = 12F
        nickname.visibility = View.INVISIBLE
    }

    fun spozaZakresu() {
        spozaZakresu.visibility = View.VISIBLE
        informacjaOWygranej.visibility = View.INVISIBLE
    }

    fun wZakresie() {
        spozaZakresu.visibility = View.INVISIBLE
        informacjaOWygranej.visibility = View.VISIBLE
        liczbaStrzalow += 1
    }

    fun updateNumberOfPoints() {
        if (liczbaStrzalow==1) {
            liczbaPunktow += 5
        }
        else if (liczbaStrzalow in 2..4) {
            liczbaPunktow += 3
        }
        else if (liczbaStrzalow in 5..6) {
            liczbaPunktow += 2
        }
        else if (liczbaStrzalow in 7..10) {
            liczbaPunktow += 1
        }
        input.visibility = View.INVISIBLE
        nickname.visibility = View.VISIBLE
    }

    fun showRanking() {
        var db = DatabaseHandler(this)
        db.insertData(nickname.text.toString(),liczbaPunktow)

        val intent = Intent(this, RankingActivity::class.java)
        finish()
        startActivity(intent)
    }

    fun end() {
        val intent = intent
        finish()
        startActivity(intent)
    }

    fun game() {
        var wylosowanaLiczba = Random.nextInt(0, MAX)

        if (input.text.toString().toInt() > MAX) {
            spozaZakresu()
        } else {
            wZakresie()
            if (input.text.toString().toInt() > wylosowanaLiczba) {
                liczbaStrzalowTrafionych += 1
                informacjaOWygranej.text = "Gratulacje! Twoja liczba " + input.text + " jest większa od wylosowanej " + wylosowanaLiczba  + " !!! :)"
                updateNumberOfPoints()
            }
            else {
                informacjaOWygranej.text = "Przykro nam! Twoja liczba " + input.text + " jest mniejsza od wylosowanej " + wylosowanaLiczba  + " :("
            }
        }
        numerStrzalu.text = "Numer strzału: " + liczbaStrzalowTrafionych + "/" + liczbaStrzalow
        if (liczbaStrzalow==10) {
            updateNumberOfPoints()
        }
    }
}
