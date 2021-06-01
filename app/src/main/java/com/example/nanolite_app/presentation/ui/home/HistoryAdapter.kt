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
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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
                    it.tvDate.text = formatDate(this.date)
                    Glide.with(binding.root)
                            .load(Uri.parse(this.imageUri))
                            .centerCrop()
                            .into(it.ivWaste)
                }
            }
        }

        private fun formatDate(date: String): String{
            var formattedDate = ""
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                val localDateTime = LocalDateTime.parse(date)
                val dateFormatter = DateTimeFormatter.ofPattern("EEE, d MMM yyyy - h:mm a")
                formattedDate = dateFormatter.format(localDateTime)
            } else {
                val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                val formatter = SimpleDateFormat("EEE, d MMM yyyy - h:mm a")
                formattedDate = formatter.format(parser.parse(date))
            }
            return formattedDate
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