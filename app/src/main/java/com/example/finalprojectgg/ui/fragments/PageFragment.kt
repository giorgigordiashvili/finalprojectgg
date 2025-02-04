package com.example.finalprojectgg.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.finalprojectgg.R

class PageFragment : Fragment() {

    companion object {
        private const val ARG_TEXT = "arg_text"
        fun newInstance(text: String): PageFragment {
            val fragment = PageFragment()
            val bundle = Bundle().apply {
                putString(ARG_TEXT, text)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_page, container, false)
        val textView = view.findViewById<TextView>(R.id.textView)
        // Retrieve and set the text from arguments
        textView.text = arguments?.getString(ARG_TEXT)
        return view
    }
}
