package com.panant76.geoquiz

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var revButton: ImageButton
    private lateinit var nextButton: ImageButton
    private lateinit var questionTextView: TextView

    private val questionBank = listOf(
        Questions(R.string.question_australia, true, null),
        Questions(R.string.question_oceans, true, null),
        Questions(R.string.question_mideast, false, null),
        Questions(R.string.question_africa, false, null),
        Questions(R.string.question_americas, true, null),
        Questions(R.string.question_asia, true, null)
    )

    private var currentIndex = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        trueButton.setOnClickListener {
            checkAnswer(true)
            updateQuestion()
        }

        falseButton = findViewById(R.id.false_button)
        falseButton.setOnClickListener {
            checkAnswer(false)
            updateQuestion()
        }

        revButton = findViewById(R.id.rev_button)
        revButton.setOnClickListener {
            val diff = currentIndex - 1
            currentIndex = if (diff == -1) questionBank.size - 1 else diff % questionBank.size
            updateQuestion()
        }

        nextButton = findViewById(R.id.next_button)
        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()

        }

        questionTextView = findViewById(R.id.question_text_view)
        updateQuestion()

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)

        if (questionBank[currentIndex].usrAnswer != null) {
            trueButton.isEnabled = false
            falseButton.isEnabled = false
        } else {
            trueButton.isEnabled = true
            falseButton.isEnabled = true
        }
    }

    private var counterTrue = 0
    private fun checkAnswer(userAnswer: Boolean) {

        val correctAnswer = questionBank[currentIndex].answer

        val messageResId = if (userAnswer == correctAnswer) {
            counterTrue++
            R.string.correct_toast

        } else {
            R.string.incorrect_toast
        }
        questionBank[currentIndex].usrAnswer = userAnswer

        val valueMean = (counterTrue.toDouble() / questionBank.size) * 100
        if (currentIndex == questionBank.size - 1) {
            Toast.makeText(
                this,
                "Правильных ответов " + valueMean.toString() + "%",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
        }
    }


}

