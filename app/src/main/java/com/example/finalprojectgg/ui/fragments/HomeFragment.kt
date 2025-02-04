package com.example.finalprojectgg.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.finalprojectgg.R
import com.example.finalprojectgg.model.Post
import com.example.finalprojectgg.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var progressBar: ProgressBar
    private lateinit var redTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout containing the ProgressBar and TextView
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Initialize views
        progressBar = view.findViewById(R.id.progressBar)
        redTextView = view.findViewById(R.id.redTextView)

        // Start fetching data (the ProgressBar will show until the data is fetched)
        fetchPosts()

        return view
    }

    private fun fetchPosts() {
        RetrofitInstance.api.getPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                // Hide the loading indicator
                progressBar.visibility = View.GONE

                if (response.isSuccessful) {
                    response.body()?.let { fetchedPosts ->
                        if (fetchedPosts.isNotEmpty()) {
                            // Show the fetched data in red text (e.g., the title of the first post)
                            redTextView.visibility = View.VISIBLE
                            redTextView.text = fetchedPosts[0].title
                        } else {
                            redTextView.visibility = View.VISIBLE
                            redTextView.text = "No posts available"
                        }
                    }
                } else {
                    redTextView.visibility = View.VISIBLE
                    redTextView.text = "Response Error"
                    Toast.makeText(context, "Response Error", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                progressBar.visibility = View.GONE
                redTextView.visibility = View.VISIBLE
                redTextView.text = "Error: ${t.message}"
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
