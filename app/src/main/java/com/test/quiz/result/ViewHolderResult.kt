package com.test.quiz.result

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.quiz.R
import com.test.quizbook.mock.Question

class ViewHolderResult(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val picture: ImageView
    private val name: TextView
    private val result: TextView

    init {
        picture = itemView.findViewById(R.id.picture_result_item)
        name = itemView.findViewById(R.id.name_result_item)
        result = itemView.findViewById(R.id.result_item)
    }

    fun bind(data: Question, image: Int, context: Context) {
        picture.setImageDrawable(context.getDrawable(image))
//        picture.setImageDrawable(AppCompatResources.getDrawable(context, image))
        name.text = data.name
        result.text = data.correctAnswer.toString()
    }
}