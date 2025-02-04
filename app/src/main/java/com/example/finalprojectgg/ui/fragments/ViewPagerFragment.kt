package com.example.finalprojectgg.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.finalprojectgg.adapter.ViewPagerAdapter
import com.example.finalprojectgg.databinding.FragmentViewpagerBinding
import com.google.android.material.tabs.TabLayoutMediator

class ViewPagerFragment : Fragment() {

    private var _binding: FragmentViewpagerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewpagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Create a list of fragments. Use ProductPageFragment as the first page.
        val fragments = listOf(
            ProductPageFragment(),
            // Add other pages as needed
            PageFragment()
        )
        val adapter = ViewPagerAdapter(this, fragments)
        binding.viewPager.adapter = adapter

        // Attach TabLayout with ViewPager2 (if using tabs)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = "Tab ${position + 1}"
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
