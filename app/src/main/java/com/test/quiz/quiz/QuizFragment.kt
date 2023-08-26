package com.test.quiz.quiz

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.test.quiz.databinding.FragmentQuizBinding
import com.test.quiz.result.QuestionsAnswer

class QuizFragment : Fragment() {

    companion object {
        fun newInstance() = QuizFragment()
        const val ONE_IMAGE = 1
        const val TWO_IMAGE = 2
        const val THREE_IMAGE = 3
    }

    private lateinit var viewModel: QuizViewModel
    private lateinit var binding: FragmentQuizBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuizBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(QuizViewModel::class.java)
        viewModel.getQuizStateLiveData().observe(viewLifecycleOwner) { quizState ->
            when(quizState) {
                is QuizState.Finish -> {
                    binding.root.findNavController().navigate(
                        QuizFragmentDirections.actionQuizFragmentToResultFragment(
                            quizState.result,
                            QuestionsAnswer(quizState.question)
                        )
                    )
                }

                QuizState.Quiz -> {

                }
            }

        }

        viewModel.getQuestionLiveData().observe(viewLifecycleOwner) { questionState ->
            binding.questionText.text = questionState.question.question
            binding.pictureOne.setImageDrawable(
                resources.getDrawable(getLinkDrawable(questionState.fakePic.get(0)))
            )
            binding.pictureTwo.setImageDrawable(
                resources.getDrawable(getLinkDrawable(questionState.fakePic.get(1)))
            )
            binding.pictureThree.setImageDrawable(
                resources.getDrawable(getLinkDrawable(questionState.fakePic.get(2)))
            )
        }
        viewModel.startQuiz()

        binding.pictureOne.setOnClickListener {
            viewModel.checkAnswer(ONE_IMAGE)
        }
        binding.pictureTwo.setOnClickListener {
            viewModel.checkAnswer(TWO_IMAGE)
        }
        binding.pictureThree.setOnClickListener {
            viewModel.checkAnswer(THREE_IMAGE)
        }
    }

    private fun getLinkDrawable(name: String): Int {
        return activity?.resources?.getIdentifier(
            name,
            "drawable",
            activity?.packageName
        )!!
    }
}