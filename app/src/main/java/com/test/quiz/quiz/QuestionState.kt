package com.test.quiz.quiz

import com.test.quizbook.mock.Question

data class QuestionState(
    val question: Question,
    val fakePic: List<String>
)
