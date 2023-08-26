package com.test.quiz.quiz

import com.test.quizbook.mock.Question

sealed interface QuizState {
    data class Finish(
        val result: Int,
        val question: List<Question>
    ): QuizState

    object Quiz: QuizState
}
