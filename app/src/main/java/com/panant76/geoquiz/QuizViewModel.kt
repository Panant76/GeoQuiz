package com.panant76.geoquiz

import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {

    private val questionBank = listOf(
        Questions(R.string.question_australia, true, null),
        Questions(R.string.question_oceans, true, null),
        Questions(R.string.question_mideast, false, null),
        Questions(R.string.question_africa, false, null),
        Questions(R.string.question_americas, true, null),
        Questions(R.string.question_asia, true, null)
    )

    var currentIndex = 0

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }
     fun moveToPrev(){
         val diff = currentIndex - 1
         currentIndex = if (diff == -1) questionBank.size - 1 else diff % questionBank.size
     }
}