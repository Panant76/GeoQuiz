package com.panant76.geoquiz

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders

private const val TAG = "MainActivity"
private const val KEY_INDEX = "index"

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var prevButton: ImageButton
    private lateinit var cheatButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var questionTextView: TextView

    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProviders.of(this).get(QuizViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)

        val currentIndex = savedInstanceState?.getInt(KEY_INDEX, 0) ?: 0
        quizViewModel.currentIndex = currentIndex

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

        prevButton = findViewById(R.id.prev_button)
        prevButton.setOnClickListener {
            quizViewModel.moveToPrev()
            updateQuestion()
        }

        nextButton = findViewById(R.id.next_button)
        nextButton.setOnClickListener {
            quizViewModel.moveToNext()
            updateQuestion()

        }

        questionTextView = findViewById(R.id.question_text_view)
        updateQuestion()

        cheatButton = findViewById(R.id.cheat_button)
        cheatButton.setOnClickListener {
            val answerIsTrue=quizViewModel.currentQuestionAnswer
            val intent = CheatActivity.newIntent(this@MainActivity, answerIsTrue)
            startActivity(intent)
        }
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

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        Log.i(TAG, "onSaveInstanceState")
        savedInstanceState.putInt(KEY_INDEX, quizViewModel.currentIndex)
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
        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)

//        if (quizViewModel.questionBank[currentIndex].usrAnswer != null) {
//            trueButton.isEnabled = false
//            falseButton.isEnabled = false
//        } else {
//            trueButton.isEnabled = true
//            falseButton.isEnabled = true
//        }
    }

    private var counterTrue = 0
    private fun checkAnswer(userAnswer: Boolean) {

        val correctAnswer = quizViewModel.currentQuestionAnswer

        val messageResId = if (userAnswer == correctAnswer) {
            counterTrue++
            R.string.correct_toast

        } else {
            R.string.incorrect_toast
        }
//        questionBank[currentIndex].usrAnswer = userAnswer
//
//        val valueMean = (counterTrue.toDouble() / questionBank.size) * 100
//        if (currentIndex == questionBank.size - 1) {
//            Toast.makeText(
//                this,
//                "Правильных ответов " + valueMean.toString() + "%",
//                Toast.LENGTH_SHORT
//            ).show()
//        } else {
           Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
//        }
    }


}

