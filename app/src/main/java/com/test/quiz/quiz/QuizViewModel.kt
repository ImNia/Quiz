package com.test.quiz.quiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.quizbook.mock.Question
import com.test.quizbook.mock.questionsMock

class QuizViewModel : ViewModel() {
    private var questionLiveData = MutableLiveData<QuestionState>()
    fun getQuestionLiveData(): MutableLiveData<QuestionState> = questionLiveData

    private var quizStateLiveData = MutableLiveData<QuizState>()
    fun getQuizStateLiveData(): MutableLiveData<QuizState> = quizStateLiveData


    private val questions = getRandomQuestions()
    private var fakeImage = listOf<String>()
    private var currentQuestion = 0
    private var rightAnswer = 0

    fun startQuiz() {
        quizStateLiveData.postValue(QuizState.Quiz)
        getQuestion()
    }

    fun checkAnswer(index: Int) {
        if (fakeImage.get(index - 1) == questions.get(currentQuestion - 1).image) {
            rightAnswer++
            questions.get(currentQuestion - 1).correctAnswer = true
        }
        getQuestion()
    }

    private fun getQuestion() {
        currentQuestion++
        if (currentQuestion <= COUNT_QUESTION) {
            fakeImage = getImageForDrawing(questions.get(currentQuestion - 1).image)
            questionLiveData.postValue(
                QuestionState(
                    question = questions.get(currentQuestion - 1),
                    fakePic = fakeImage
                )
            )
        } else {
            quizStateLiveData.postValue(
                QuizState.Finish(rightAnswer, questions)
            )
        }
    }

    private fun getRandomQuestions(): List<Question> {
        return questionsMock.shuffled().take(COUNT_QUESTION)
    }

    private fun getImageForDrawing(right: String): List<String> {
        val list = mutableListOf<String>()
        list.add(right)
        while (list.size < 3) {
            val image = getImage()
            if (!list.contains(image)) list.add(image)
        }
        return list.shuffled()
    }

    private fun getImage(): String {
        val images = mutableListOf<String>()
        questionsMock.forEach {
            images.add(it.image)
        }
        return images.random()
    }

    companion object {
        const val COUNT_QUESTION = 10
    }
}