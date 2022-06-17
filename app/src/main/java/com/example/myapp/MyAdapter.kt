package com.example.myapp

import android.content.Context
import android.graphics.Bitmap
import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapp.databinding.ItemMainBinding
import com.example.myapp.databinding.ItemRecyclerviewBinding
import java.text.SimpleDateFormat

class MyViewHolder(val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root)
class MyAdapter(val context : Context, val datas : MutableList<Row>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int {
        return datas?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyViewHolder).binding
        val model = datas!![position]
        binding.name.text = model.SH_NAME
        binding.tel.text = model.SH_PHONE
        binding.addr.text = model.SH_ADDR
        // val SH_NAME: String, val SH_PHONE: String, val SH_ADDR: String
    }

}
class MyViewHolder2(val binding: ItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root)
class MyAdapter2(val datas: MutableList<String>?, val prices: MutableList<String>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int {
        //TODO("Not yet implemented")
        return datas?.size ?:0
    }
    fun getItemCount1(): Int {
        return prices?.size?:0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = MyViewHolder2(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //TODO("Not yet implemented")
        val binding = (holder as MyViewHolder2).binding

        binding.titleTextView.text = datas!![position]
        binding.priceTextView.text = prices!![position]
        //binding.productImg.setImageBitmap(images!![position])


    }
}

/*
data class ArticleModel (
    val sellerId : String,
    val title : String,
    val createdAt : Long,
    val price : String,
    val imageUrl : String )

class ArticleAdapter(val onItemClicked : (ArticleModel) -> Unit) : ListAdapter<ArticleModel, ArticleAdapter.ViewHolder>(diffUtil) {
    inner class ViewHolder(val binding : ItemRecyclerviewBinding) :
            RecyclerView.ViewHolder(binding.root) {
                fun bind(articleModel : ArticleModel){
                    val format = SimpleDateFormat("MM월 dd일")
                    val date = Date(articleModel.createdAt)

                    binding.titleTextView.text = articleModel.title
                    binding.dateTextView.text = format.format(date).toString()
                    binding.priceTextView.text = articleModel.price

                    if(articleModel.imageUrl.isNotEmpty()) {
                        Glide.with(binding.productImg)
                            .load(articleModel.imageUrl)
                            .into(binding.productImg)
                    }
                    binding.root.setOnClickListener {
                        onItemClicked(articleModel)
                    }
                }
            }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        return ViewHolder(
            ItemRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.bind(currentList[position])
    }
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ArticleModel>() {
            override fun areItemsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean {
                return oldItem.createdAt == newItem.createdAt
            }

            override fun areContentsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean {
                return oldItem == newItem
            }
        }
    }

}
 */