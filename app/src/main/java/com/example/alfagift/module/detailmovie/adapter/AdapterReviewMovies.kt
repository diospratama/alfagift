package com.example.alfagift.module.detailmovie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.alfagift.databinding.ItemReviewBinding
import com.example.alfagift.model.ResultReviewMovies

class AdapterReviewMovies(private val activity: AppCompatActivity) :
    RecyclerView.Adapter<AdapterReviewMovies.ViewHolder>() {
    private var data = ArrayList<ResultReviewMovies>()
    fun setData(dataList: List<ResultReviewMovies>) {
        data.addAll(dataList)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder = ViewHolder(
        ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.view) {
            textAuthor.text = data[position].author
            textComment.text = data[position].content
            textDateReview.text = data[position].updatedAt

        }
    }

    override fun getItemCount(): Int = data.size


    class ViewHolder(val view: ItemReviewBinding) : RecyclerView.ViewHolder(view.root)
}