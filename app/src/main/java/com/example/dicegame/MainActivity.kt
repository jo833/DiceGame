package com.example.dicegame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.PopupWindow

/*
* Dice rolling game with option to either have an opponent that plays to win or one that randomly
* selects the dice.
*
* Author: Julia Oghigian
* GitHub: jo833
 */
class MainActivity : AppCompatActivity() {
    lateinit var aboutButton: Button
    lateinit var newGameButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        aboutButton = findViewById<Button>(R.id.about)
        newGameButton = findViewById<Button>(R.id.newGame)

        aboutButton.setOnClickListener {
            //allows for pop up to show up
            var popupWindowAbout: View = layoutInflater.inflate(R.layout.about_popup, null)
            var popupWindow = PopupWindow(this)
            popupWindow.contentView = popupWindowAbout
            popupWindow.showAtLocation(popupWindowAbout, Gravity.CENTER, 0, 0)
            popupWindowAbout.setOnClickListener { popupWindow.dismiss() }
        }
        newGameButton.setOnClickListener {
            //starts new game
            val i = Intent(this, NewGame::class.java)
            startActivity(i)
        }

    }
}