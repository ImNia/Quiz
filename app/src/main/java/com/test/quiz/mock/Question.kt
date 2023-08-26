package com.test.quizbook.mock

data class Question(
    val id: Int,
    val question: String = "",
    val name: String = "",
    val author: String = "",
    val image: String = "",
    var correctAnswer: Boolean = false
)
