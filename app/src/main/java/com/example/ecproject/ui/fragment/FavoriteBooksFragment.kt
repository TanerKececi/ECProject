package com.example.ecproject.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ecproject.MainViewModel
import com.example.ecproject.databinding.FragmentFavoriteBooksBinding
import com.example.ecproject.ui.viewmodel.FavoriteBooksViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteBooksFragment : Fragment() {

    private val favoriteBooksViewModel : FavoriteBooksViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()


    private var _binding: FragmentFavoriteBooksBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBooksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClicks()
    }

    private fun setOnClicks() {
        binding.nextPageButton.setOnClickListener {
            /*
            val action = FavoriteBooksFragmentDirections.actionFavoriteBooksFragmentToBookDetailFragment()
            findNavController().navigate(action)

             */
        }
    }
}