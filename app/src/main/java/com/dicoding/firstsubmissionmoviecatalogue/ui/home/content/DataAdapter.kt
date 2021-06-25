package com.dicoding.firstsubmissionmoviecatalogue.ui.home.content

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.firstsubmissionmoviecatalogue.R
import com.dicoding.firstsubmissionmoviecatalogue.databinding.ItemRowDataBinding
import com.dicoding.firstsubmissionmoviecatalogue.model.DataModel
import com.dicoding.firstsubmissionmoviecatalogue.utils.Helper.API_IMAGE_ENDPOINT
import com.dicoding.firstsubmissionmoviecatalogue.utils.Helper.ENDPOINT_POSTER_SIZE_W185
import com.dicoding.firstsubmissionmoviecatalogue.utils.Helper.setImageWithGlide
import java.util.ArrayList
import kotlin.coroutines.coroutineContext

class DataAdapter (private val callback: DataCallback) :
    RecyclerView.Adapter<DataAdapter.ListViewHolder>() {
    private val listData = ArrayList<DataModel>()
    lateinit var binding: ItemRowDataBinding

    fun setData(data: List<DataModel>?) {
        if (data == null) return
        listData.clear()
        listData.addAll(data)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: DataModel) {
            binding = ItemRowDataBinding.bind(itemView)
            with(itemView) {
                setImageWithGlide(context, API_IMAGE_ENDPOINT+ ENDPOINT_POSTER_SIZE_W185+ data.poster, binding.imgData)
                binding.tvDataTitle.text = data.name
                binding.tvDataDesc.text = data.desc

                binding.cardItem.setOnClickListener {
                    callback.onItemClicked(data)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_data, parent,false)
        )
    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: DataAdapter.ListViewHolder, position: Int) {
        holder.bind(listData[position])
    }
}