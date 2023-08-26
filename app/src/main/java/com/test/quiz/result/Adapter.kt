package com.test.quiz.result

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.quiz.R
import com.test.quizbook.mock.Question

class Adapter(
    private val context: Context
): RecyclerView.Adapter<ViewHolderResult>() {
    var data: List<Question> = listOf()
    var dataImage: List<Int> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderResult {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        return ViewHolderResult(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolderResult, position: Int) {
        holder.bind(data = data[position], image = dataImage[position], context = context)
    }
}