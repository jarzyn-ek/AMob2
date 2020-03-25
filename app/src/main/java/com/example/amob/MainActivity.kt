package com.example.amob

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    val MAX = 20
    var liczbaStrzalow = 0
    var liczbaStrzalowTrafionych = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        init()

        input.setOnClickListener {
           game()
        }

        nowaGra.setOnClickListener {
            end()
        }
    }

    fun init() {
        spozaZakresu.visibility = View.INVISIBLE
        informacjaOWygranej.textSize = 12F
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
            }
            else {
                informacjaOWygranej.text = "Przykro nam! Twoja liczba " + input.text + " jest mniejsza od wylosowanej " + wylosowanaLiczba  + " :("
            }
        }
        numerStrzalu.text = "Numer strzału: " + liczbaStrzalowTrafionych + "/" + liczbaStrzalow
    }
}
