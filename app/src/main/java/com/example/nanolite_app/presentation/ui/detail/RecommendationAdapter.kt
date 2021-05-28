package com.example.nanolite_app.presentation.ui.detail

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nanolite_app.databinding.RecommendationItemBinding
import com.example.nanolite_app.domain.model.Recycler
import com.example.nanolite_app.presentation.ui.recommendation.RecommendationActivity
import com.example.nanolite_app.presentation.ui.recommendation.RecommendationActivity.Companion.EXTRA_RECYCLER

class RecommendationAdapter: RecyclerView.Adapter<RecommendationAdapter.RecommendationViewHolder>() {
    private val list = ArrayList<Recycler>()

    fun setList(aList: ArrayList<Recycler>){
        if(list != null){
            list.clear()
        }
        list.addAll(aList)
    }

    class RecommendationViewHolder(private val binding : RecommendationItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(recommend: Recycler){
            binding.let {
                with(recommend){
                    it.tvReccomend.text = this.link
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecommendationAdapter.RecommendationViewHolder {
        val binding = RecommendationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecommendationViewHolder(binding)

    }

    override fun onBindViewHolder(
        holder: RecommendationAdapter.RecommendationViewHolder,
        position: Int
    ) {
        holder.bind(list[position])

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, RecommendationActivity::class.java)
            intent.putExtra(EXTRA_RECYCLER, list[holder.adapterPosition])
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}