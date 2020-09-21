package com.example.perfumeapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myperfumeapp.R
import com.example.perfumeapp.database.Perfume

class PerfumeAdapter(val onClickListener: OnClickListener): RecyclerView.Adapter<PerfumeAdapter.ViewHolder>() {

    var data = listOf<Perfume>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = data[position]
//        holder.textView.text = item.perfumeName
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_perfume, parent, false)
        return ViewHolder(view)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val res = itemView.context.resources


        val perfumeImage: ImageView = itemView.findViewById(R.id.perfume_image)
        val perfumeBrand: TextView = itemView.findViewById(R.id.perfume_brand)
        val perfumeName: TextView = itemView.findViewById(R.id.perfume_name)

        fun bind(item: Perfume) {
            perfumeName.text = item.perfumeName
            perfumeBrand.text = item.perfumeBrand
            perfumeImage.setImageResource(R.drawable.cute_perfume)
        }
    }

    class OnClickListener(val clickListener: (perfume: Perfume) -> Unit) {
        fun onClick(perfume: Perfume) = clickListener(perfume)
    }
}


