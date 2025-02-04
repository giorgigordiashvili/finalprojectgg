package com.example.finalprojectgg.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.finalprojectgg.R
import com.example.finalprojectgg.model.Product

class ProductAdapter(private val products: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImageView: ImageView = itemView.findViewById(R.id.productImageView)
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.titleTextView.text = product.title
        holder.priceTextView.text = "$${product.price}"
        // Load the image using Glide
        Glide.with(holder.itemView.context)
            .load(product.image)
            .into(holder.productImageView)
    }

    override fun getItemCount(): Int = products.size
}
