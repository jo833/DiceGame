package com.example.dicegame

import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import java.util.*


class NewGame : AppCompatActivity() {
    //dice images from https://www.wpclipart.com/recreation/games/dice/
    val dice_ids = listOf<Int>(
        R.drawable.die_face_1,
        R.drawable.die_face_2,
        R.drawable.die_face_3,
        R.drawable.die_face_4,
        R.drawable.die_face_5,
        R.drawable.die_face_6
    )

    //dice values
    val dice = listOf<Int>(1, 2, 3, 4, 5, 6)

    lateinit var prefs: SharedPreferences
    lateinit var imageViewComputer1: ImageView
    lateinit var imageViewComputer2: ImageView
    lateinit var imageViewComputer3: ImageView
    lateinit var imageViewComputer4: ImageView
    lateinit var imageViewComputer5: ImageView
    lateinit var imageViewHuman1: ImageView
    lateinit var imageViewHuman2: ImageView
    lateinit var imageViewHuman3: ImageView
    lateinit var imageViewHuman4: ImageView
    lateinit var imageViewHuman5: ImageView
    var computerTally = 0
    lateinit var throwButton: Button
    lateinit var scoreButton: Button
    lateinit var generator: Random
    var humanScore: Int = 0
    var computerScore: Int = 0
    lateinit var gameScore: TextView
    var dice1Human = 0
    var dice2Human = 0
    var dice3Human = 0
    var dice4Human = 0
    var dice5Human = 0
    var dice1Computer = 0
    var dice2Computer = 0
    var dice3Computer = 0
    var dice4Computer = 0
    var dice5Computer = 0
    var intTargetScore = 108
    var humanWin = 0
    var computerWin = 0
    var newGame = true
    var isATie = false
    lateinit var winTally: TextView
    lateinit var targetScore: EditText

    // This class generates the game screen and allows for newGame method to be called.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_game)
        prefs = getSharedPreferences("com.example.juliaoghigianassignment1", MODE_PRIVATE)
        humanWin = prefs.getInt("humanWin", humanWin)
        computerWin = prefs.getInt("computerWin", computerWin)
        imageViewComputer1 = findViewById<ImageView>(R.id.imageViewComputer1)
        imageViewComputer2 = findViewById<ImageView>(R.id.imageViewComputer2)
        imageViewComputer3 = findViewById<ImageView>(R.id.imageViewComputer3)
        imageViewComputer4 = findViewById<ImageView>(R.id.imageViewComputer4)
        imageViewComputer5 = findViewById<ImageView>(R.id.imageViewComputer5)
        imageViewHuman1 = findViewById<ImageView>(R.id.imageViewHuman1)
        imageViewHuman2 = findViewById<ImageView>(R.id.imageViewHuman2)
        imageViewHuman3 = findViewById<ImageView>(R.id.imageViewHuman3)
        imageViewHuman4 = findViewById<ImageView>(R.id.imageViewHuman4)
        imageViewHuman5 = findViewById<ImageView>(R.id.imageViewHuman5)
        winTally = findViewById<TextView>(R.id.textViewWinScores)
        gameScore = findViewById<TextView>(R.id.textViewScore)
        throwButton = findViewById<Button>(R.id.throwButton)
        scoreButton = findViewById<Button>(R.id.scoreButton)
        targetScore = findViewById<EditText>(R.id.editTextNumberOfPoints)
        generator = Random()
        throwButton.isClickable = true
        scoreButton.isClickable = true
        displayNewDiceHuman()
        displayNewDiceComputer()
        newGame()
    }

    // This class calls a new game and allows the throw and score buttons to work.
    fun newGame(): Unit {
        var tally = 0
        imageViewHuman1.isClickable = true
        imageViewHuman2.isClickable = true
        imageViewHuman3.isClickable = true
        imageViewHuman4.isClickable = true
        imageViewHuman5.isClickable = true
        imageViewComputer1.isClickable = true
        imageViewComputer2.isClickable = true
        imageViewComputer3.isClickable = true
        imageViewComputer4.isClickable = true
        imageViewComputer5.isClickable = true

        //allows for the program to determine which dice to reroll
        imageViewHuman1.setOnClickListener {
            imageViewHuman1.isClickable = false
            //provides a tint when dice has been selected
            imageViewHuman1.setColorFilter(
                Color.argb(155, 55, 0, 179),
                PorterDuff.Mode.SRC_ATOP
            )
        }
        imageViewHuman2.setOnClickListener {
            imageViewHuman2.isClickable = false
            //provides a tint when dice has been selected
            imageViewHuman2.setColorFilter(
                Color.argb(155, 55, 0, 179),
                PorterDuff.Mode.SRC_ATOP
            )
        }
        imageViewHuman3.setOnClickListener {
            imageViewHuman3.isClickable = false
            //provides a tint when dice has been selected
            imageViewHuman3.setColorFilter(
                Color.argb(155, 55, 0, 179),
                PorterDuff.Mode.SRC_ATOP
            )
        }
        imageViewHuman4.setOnClickListener {
            imageViewHuman4.isClickable = false
            //provides a tint when dice has been selected
            imageViewHuman4.setColorFilter(
                Color.argb(155, 55, 0, 179),
                PorterDuff.Mode.SRC_ATOP
            )
        }
        imageViewHuman5.setOnClickListener {
            imageViewHuman5.isClickable = false
            //provides a tint when dice has been selected
            imageViewHuman5.setColorFilter(
                Color.argb(155, 55, 0, 179),
                PorterDuff.Mode.SRC_ATOP
            )
        }


        throwButton.setOnClickListener {
            //allows for human to change target score
            intTargetScore = try {
                Integer.parseInt(targetScore.text.toString())
            } catch (error: NumberFormatException) {
                101
            }
            //keeps tracks of wins
            winTally.setText("H:$humanWin|C:$computerWin")

            //the first roll of the new round
            if (tally == 0 && newGame && !isATie) {
                computerTally = 0
                displayNewDiceHuman()
                displayNewDiceComputer()
                tally++
                newGame = false

            } else if (!isATie && tally < 3 && !imageViewHuman1.isClickable || !imageViewHuman2.isClickable || !imageViewHuman3.isClickable || !imageViewHuman4.isClickable || !imageViewHuman5.isClickable) {
                displayNewDiceHuman()
                imageViewHuman1.isClickable = true
                imageViewHuman2.isClickable = true
                imageViewHuman3.isClickable = true
                imageViewHuman4.isClickable = true
                imageViewHuman5.isClickable = true
                imageViewHuman1.clearColorFilter()
                imageViewHuman2.clearColorFilter()
                imageViewHuman3.clearColorFilter()
                imageViewHuman4.clearColorFilter()
                imageViewHuman5.clearColorFilter()
                tally++
            } else {
                //mainly for tie guidelines as rerolls are disabled
                displayNewDiceHuman()
                tally++

                if (isATie) {
                    computerPlay()
                    humanScore += dice1Human + dice2Human + dice3Human + dice4Human + dice5Human
                    computerScore += dice1Computer + dice2Computer + dice3Computer + dice4Computer + dice5Computer
                    gameScore.setText("Score: H:" + humanScore + " | C:" + computerScore)
                    endOfGame()
                }

            }

            //limit of rerolls hit
            if (tally == 3) {
                //allows for computer to play
                computerPlay()
                humanScore += dice1Human + dice2Human + dice3Human + dice4Human + dice5Human
                computerScore += dice1Computer + dice2Computer + dice3Computer + dice4Computer + dice5Computer
                gameScore.setText("Score: H:" + humanScore + " | C:" + computerScore)
                tally = 0
                //tie guidelines that disables rerolls
                if (humanScore == computerScore && humanScore >= intTargetScore) {
                    imageViewHuman1.isClickable = false
                    imageViewHuman2.isClickable = false
                    imageViewHuman3.isClickable = false
                    imageViewHuman4.isClickable = false
                    imageViewHuman5.isClickable = false
                    tally = 4
                    isATie = true
                } else {

                    newGame = true
                    endOfGame()


                }
            }

        }

        //calculates player scores
        scoreButton.setOnClickListener {

            //allows computer to play in case the player chooses to score with limited rerolls
            computerPlay()
            newGame = true
            humanScore += dice1Human + dice2Human + dice3Human + dice4Human + dice5Human
            computerScore += dice1Computer + dice2Computer + dice3Computer + dice4Computer + dice5Computer
            gameScore.setText("Score: H:" + humanScore + " | C:" + computerScore)
            computerTally = 0
            imageViewComputer1.isClickable = true
            imageViewComputer2.isClickable = true
            imageViewComputer3.isClickable = true
            imageViewComputer4.isClickable = true
            imageViewComputer5.isClickable = true
            tally = 0

            //signals that there is a tie
            if (humanScore == computerScore && humanScore >= intTargetScore) {
                imageViewHuman1.isClickable = false
                imageViewHuman2.isClickable = false
                imageViewHuman3.isClickable = false
                imageViewHuman4.isClickable = false
                imageViewHuman5.isClickable = false
                tally = 4
                isATie = true
            } else {
                endOfGame()

            }
        }

    }

    //determines if there is a winner to the game
    fun endOfGame(): Unit {
        if (humanScore >= intTargetScore || computerScore >= intTargetScore) {
            //calls pop up if human wins
            if (humanScore > computerScore) {
                var popupWindowWin: View = layoutInflater.inflate(R.layout.winpopup, null)
                var popupWindow = PopupWindow(this)
                popupWindow.contentView = popupWindowWin
                popupWindow.showAtLocation(popupWindowWin, Gravity.CENTER, 0, 0)
                popupWindowWin.setOnClickListener { popupWindow.dismiss() }
                throwButton.isClickable = false //disables game
                scoreButton.isClickable = false //disables game
                humanWin++
                winTally.setText("H:$humanWin|C:$computerWin")

            } else {
                //computer wins popup
                var popupWindowLose: View = layoutInflater.inflate(R.layout.losepopup, null)

                var popupWindow = PopupWindow(this)
                popupWindow.contentView = popupWindowLose
                popupWindow.showAtLocation(popupWindowLose, Gravity.CENTER, 0, 0)
                popupWindowLose.setOnClickListener { popupWindow.dismiss() }
                throwButton.isClickable = false //disables game
                scoreButton.isClickable = false //disables game
                computerWin++
                winTally.setText("H:$humanWin|C:$computerWin")
            }
        }
    }

    //generates new dice image for the player
    fun displayNewDiceHuman(): Unit {

        val imgDice1Human_index = generator.nextInt(dice.size)

        val imgDice2Human_index = generator.nextInt(dice.size)

        val imgDice3Human_index = generator.nextInt(dice.size)

        val imgDice4Human_index = generator.nextInt(dice.size)

        val imgDice5Human_index = generator.nextInt(dice.size)

        if (imageViewHuman1.isClickable || isATie) {
            imageViewHuman1.setImageResource(dice_ids[imgDice1Human_index])
            dice1Human = dice[imgDice1Human_index]
        }
        if (imageViewHuman2.isClickable || isATie) {
            imageViewHuman2.setImageResource(dice_ids[imgDice2Human_index])
            dice2Human = dice[imgDice2Human_index]
        }
        if (imageViewHuman3.isClickable || isATie) {
            imageViewHuman3.setImageResource(dice_ids[imgDice3Human_index])
            dice3Human = dice[imgDice3Human_index]
        }
        if (imageViewHuman4.isClickable || isATie) {
            imageViewHuman4.setImageResource(dice_ids[imgDice4Human_index])
            dice4Human = dice[imgDice4Human_index]
        }
        if (imageViewHuman5.isClickable || isATie) {
            imageViewHuman5.setImageResource(dice_ids[imgDice5Human_index])
            dice5Human = dice[imgDice5Human_index]
        }

    }

    //generates new dice image for the computer
    fun displayNewDiceComputer(): Unit {

        val imgDice1Computer_index = generator.nextInt(dice.size)

        val imgDice2Computer_index = generator.nextInt(dice.size)

        val imgDice3Computer_index = generator.nextInt(dice.size)

        val imgDice4Computer_index = generator.nextInt(dice.size)

        val imgDice5Computer_index = generator.nextInt(dice.size)

        if (imageViewComputer1.isClickable) {
            imageViewComputer1.setImageResource(dice_ids[imgDice1Computer_index])
            dice1Computer = dice[imgDice1Computer_index]
        }
        if (imageViewComputer2.isClickable) {
            imageViewComputer2.setImageResource(dice_ids[imgDice2Computer_index])
            dice2Computer = dice[imgDice2Computer_index]
        }
        if (imageViewComputer3.isClickable) {
            imageViewComputer3.setImageResource(dice_ids[imgDice3Computer_index])
            dice3Computer = dice[imgDice3Computer_index]
        }
        if (imageViewComputer4.isClickable) {
            imageViewComputer4.setImageResource(dice_ids[imgDice4Computer_index])
            dice4Computer = dice[imgDice4Computer_index]
        }
        if (imageViewComputer5.isClickable) {
            imageViewComputer5.setImageResource(dice_ids[imgDice5Computer_index])
            dice5Computer = dice[imgDice5Computer_index]
        }

    }

    fun computerPlay(): Unit {
        // This is an efficient strategies as it loops through the five dice drawn. From
        // there the dice that are greater than or equal to 3 are selected and the computer
        // rerolls. It is best for the computer to reroll twice as it presents the best
        // opportunity for the highest score. This program works best as it gives the computer
        // higher probability of winning due to continually selecting the best odds. The
        // disadvantage of this programs comes in it has to check each time for the values greater
        // than or equal to three and in the chance the highest possible score (30) is rolled on
        // the first time the code will still reroll. The advantages are that the method allows
        // for the best opportunity for the highest scores and uses all available chances to preform
        // well.
        val listOfComputerDice = listOf<ImageView>(
            imageViewComputer1,
            imageViewComputer2,
            imageViewComputer3,
            imageViewComputer4,
            imageViewComputer5
        )
        val listOfDiceValues =
            listOf<Int>(dice1Computer, dice2Computer, dice3Computer, dice4Computer, dice5Computer)
        while (computerTally < 3 && !isATie) {
            var i = 0
            for (dice in listOfDiceValues) {
                if (dice >= 3) {
                    listOfComputerDice[i].isClickable = false
                }
                i++
            }

            displayNewDiceComputer()
            imageViewComputer1.isClickable = true
            imageViewComputer2.isClickable = true
            imageViewComputer3.isClickable = true
            imageViewComputer4.isClickable = true
            imageViewComputer5.isClickable = true


            computerTally++
        }
        if (isATie) {
            displayNewDiceComputer()
        }
        //code that allows for the program to be completely random
        /* var reRoll = generator.nextBoolean()
         if (reRoll && computerTally < 3 && !isATie) {
             computerTally++
             var numberToReRoll = generator.nextInt(listOfComputerDice.size)
             var i = 0
             while (i < numberToReRoll) {
                 var diceToKeep = generator.nextInt(listOfComputerDice.size)
                 listOfComputerDice[diceToKeep].isClickable = false
                 i++
 
             }
             displayNewDiceComputer()
             imageViewComputer1.isClickable = true
             imageViewComputer2.isClickable = true
             imageViewComputer3.isClickable = true
             imageViewComputer4.isClickable = true
             imageViewComputer5.isClickable = true
             computerPlay()
         } else if (isATie) {
             displayNewDiceComputer()
         }*/

    }

    //code referenced from week 7 slides on an example application for sharedPreferences
    override fun onPause() {
        super.onPause()
        var edit = prefs.edit()
        edit.putInt("humanWin", humanWin)
        edit.putInt("computerWin", computerWin)
        edit.apply()
    }

}