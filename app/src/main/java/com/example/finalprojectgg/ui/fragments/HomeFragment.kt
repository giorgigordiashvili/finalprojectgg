package com.example.finalprojectgg.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.finalprojectgg.R
import com.example.finalprojectgg.model.Post
import com.example.finalprojectgg.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var progressBar: ProgressBar
    private lateinit var titleTextView: TextView
    private lateinit var bodyTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Initialize views
        progressBar = view.findViewById(R.id.progressBar)
        titleTextView = view.findViewById(R.id.titleTextView)
        bodyTextView = view.findViewById(R.id.bodyTextView)

        // Set default visibility
        titleTextView.visibility = View.GONE
        bodyTextView.visibility = View.GONE

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("HomeFragment", "Fragment View Created - Fetching Data")

        // Fetch data when the fragment view is ready
        fetchPosts()
    }

    private fun fetchPosts() {
        Log.d("HomeFragment", "API Call Started")

        // Show progress bar before fetching data
        progressBar.visibility = View.VISIBLE

        RetrofitInstance.api.getPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                Log.d("HomeFragment", "API Response Received")

                // Hide loading indicator
                progressBar.visibility = View.GONE

                if (response.isSuccessful) {
                    val fetchedPosts = response.body()
                    if (fetchedPosts != null && fetchedPosts.isNotEmpty()) {
                        val post = fetchedPosts[0] // Get the first post

                        Log.d("HomeFragment", "Fetched Post: ${post.title}")

                        // Show title
                        titleTextView.visibility = View.VISIBLE
                        titleTextView.text = post.title

                        // Show body
                        bodyTextView.visibility = View.VISIBLE
                        bodyTextView.text = post.body
                    } else {
                        showError("No posts available")
                    }
                } else {
                    showError("Response Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.e("HomeFragment", "API Call Failed: ${t.message}")
                progressBar.visibility = View.GONE
                showError("Error: ${t.message}")
            }
        })
    }

    private fun showError(message: String) {
        Log.e("HomeFragment", "Error: $message")
        if (isAdded) {
            titleTextView.visibility = View.VISIBLE
            bodyTextView.visibility = View.VISIBLE
            titleTextView.text = message
            bodyTextView.text = "Please try again later."
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }
}
