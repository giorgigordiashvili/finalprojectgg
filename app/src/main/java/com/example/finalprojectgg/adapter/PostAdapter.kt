package com.example.finalprojectgg.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalprojectgg.R
import com.example.finalprojectgg.model.Post

class PostAdapter(private val posts: List<Post>) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    // ViewHolder class holds the references to the UI components for each item
    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val bodyTextView: TextView = itemView.findViewById(R.id.bodyTextView)
    }

    // Called when RecyclerView needs a new ViewHolder of the given type
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        // Inflate the layout for individual list items (defined in item_post.xml)
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    // Bind data to the ViewHolder for each list item
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.titleTextView.text = post.title
        holder.bodyTextView.text = post.body
    }

    // Returns the total number of items in the list
    override fun getItemCount(): Int = posts.size
}
