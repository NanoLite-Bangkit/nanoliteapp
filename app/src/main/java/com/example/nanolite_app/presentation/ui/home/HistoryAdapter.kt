package com.example.nanolite_app.presentation.ui.home

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.nanolite_app.databinding.HistoryItemBinding
import com.example.nanolite_app.domain.model.Waste
import com.example.nanolite_app.presentation.ui.detail.DetailWasteActivity
import com.example.nanolite_app.presentation.ui.detail.DetailWasteActivity.Companion.EXTRA_WASTE

class HistoryAdapter: RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    private val list = ArrayList<Waste>()

    fun setList(aList: ArrayList<Waste>){
        if(list != null){
            list.clear()
        }
        list.addAll(aList)
    }

    class HistoryViewHolder(private val binding: HistoryItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(waste: Waste){
            binding.let {
                with(waste){
                    it.tvWasteName.text = this.trashName
                    it.tvClassification.text = this.classification
                    it.tvDate.text = this.date
                    Glide.with(binding.root)
                            .load(Uri.parse(this.imageUri))
                            .centerCrop()
                            .into(it.ivWaste)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding  = HistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(list[position])

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, DetailWasteActivity::class.java)
            intent.putExtra(EXTRA_WASTE, list[holder.adapterPosition])
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}