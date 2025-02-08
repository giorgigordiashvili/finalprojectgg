package com.example.finalprojectgg.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalprojectgg.R
import com.example.finalprojectgg.adapter.ProductAdapter
import com.example.finalprojectgg.model.Product
import com.example.finalprojectgg.network.FakeStoreRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductPageFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var progressBar: ProgressBar
    private val products = mutableListOf<Product>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_product_page, container, false)

        // Initialize views
        recyclerView = view.findViewById(R.id.recyclerView)
        progressBar = view.findViewById(R.id.progressBar)

        recyclerView.layoutManager = LinearLayoutManager(context)
        productAdapter = ProductAdapter(products)
        recyclerView.adapter = productAdapter

        // Show loading indicator until data is fetched
        progressBar.visibility = View.VISIBLE

        // Fetch product data from FakeStoreAPI
        fetchProduct()

        return view
    }

    private fun fetchProduct() {
        FakeStoreRetrofit.api.getProducts().enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                // Hide the loading indicator
                progressBar.visibility = View.GONE

                if (response.isSuccessful) {
                    response.body()?.let { fetchedProducts ->
                        if (fetchedProducts.isNotEmpty()) {
                            // Clear the list to avoid duplicate items on refresh
                            products.clear()
                            // Add all fetched products
                            products.addAll(fetchedProducts)
                            // Notify adapter of data change
                            productAdapter.notifyDataSetChanged()
                        } else {
                            Toast.makeText(context, "No products available", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(context, "Response Error", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                progressBar.visibility = View.GONE
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}
