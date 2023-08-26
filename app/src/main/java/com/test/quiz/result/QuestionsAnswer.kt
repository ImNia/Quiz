package com.test.quiz.result

import com.test.quizbook.mock.Question
import java.io.Serializable

data class QuestionsAnswer(
    val list: List<Question>
): Serializable
