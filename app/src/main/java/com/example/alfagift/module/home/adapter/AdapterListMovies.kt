package com.example.alfagift.module.home.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.alfagift.BuildConfig
import com.example.alfagift.R
import com.example.alfagift.databinding.ItemMovieBinding
import com.example.alfagift.model.ResultDiscoverMovies
import com.example.alfagift.module.home.HomeViewModel

class AdapterListMovies(private val activity: AppCompatActivity, val viewModel: HomeViewModel) :
    RecyclerView.Adapter<AdapterListMovies.ViewHolder>() {
    private var data = ArrayList<ResultDiscoverMovies>()
    fun setData(dataList: List<ResultDiscoverMovies>) {
        data.addAll(dataList)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder = ViewHolder(
        ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.view) {
            val dateRelease =
                activity.applicationContext.getString(R.string.release) + data[position].releaseDate
            textTitleMovie.text = data[position].title
            textGenreMovie.text = dateRelease
            Glide.with(imgPoster)
                .load(BuildConfig.imgUrl + data[position].posterPath)
                .into(imgPoster)
            cardItemMovies.setOnClickListener {
                viewModel.goToDetailMovies(activity, data[position])
            }

        }
    }

    override fun getItemCount(): Int = data.size


    class ViewHolder(val view: ItemMovieBinding) : RecyclerView.ViewHolder(view.root)
}
