package com.test.quiz.result

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.quiz.R
import com.test.quiz.databinding.FragmentResultBinding
import com.test.quizbook.mock.Question

class ResultFragment : Fragment() {

    private lateinit var viewModel: ResultViewModel
    private lateinit var binding: FragmentResultBinding

    private val args by navArgs<ResultFragmentArgs>()
    private val result by lazy { args.answer }
    private val questions by lazy { args.questionsAnswer }

    private lateinit var adapter: Adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultBinding.inflate(layoutInflater)
        adapter = Adapter(requireContext())

        binding.countResult.text = result.toString()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ResultViewModel::class.java)

        binding.buttonResult.setOnClickListener {
            binding.root.findNavController().navigate(
                R.id.action_resultFragment_to_menuFragment
            )
        }
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = adapter

        adapter.data = questions.list
        adapter.dataImage = getIdImages(questions.list)
        adapter.notifyDataSetChanged()
    }

    private fun getIdImages(data: List<Question>): List<Int> {
        val list = mutableListOf<Int>()
        data.forEach {
            list.add(getLinkDrawable(it.image))
        }
        return list
    }

    private fun getLinkDrawable(name: String): Int {
        return activity?.resources?.getIdentifier(
            name,
            "drawable",
            activity?.packageName
        )!!
    }
}