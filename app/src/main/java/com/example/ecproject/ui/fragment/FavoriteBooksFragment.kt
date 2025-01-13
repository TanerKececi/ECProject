package com.example.ecproject.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.ecproject.MainViewModel
import com.example.ecproject.databinding.FragmentFavoriteBooksBinding
import com.example.ecproject.model.Book
import com.example.ecproject.ui.adapter.FavoriteBooksAdapter
import com.example.ecproject.ui.adapter.ItemClickListener
import com.example.ecproject.ui.viewmodel.FavoriteBooksViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteBooksFragment : Fragment(), ItemClickListener {

    private val favoriteBooksViewModel : FavoriteBooksViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentFavoriteBooksBinding? = null
    private val binding get() = _binding!!

    private lateinit var favoriteBooksAdapter: FavoriteBooksAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBooksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoriteBooksViewModel.getBooks()
        initRv()
        observeViewModel()
        initOnClick()
    }

    override fun onItemClicked(book: Book) {
        val action = FavoriteBooksFragmentDirections.actionFavoriteBooksFragmentToBookDetailFragment(book)
        findNavController().navigate(action)
    }

    private fun initOnClick() {
        binding.layoutBackButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            favoriteBooksViewModel.booksData.collectLatest { books ->
                favoriteBooksAdapter.submitList(books)
            }
        }
    }

    private fun initRv() {
        favoriteBooksAdapter = FavoriteBooksAdapter(this)
        binding.recyclerView.adapter = favoriteBooksAdapter
    }
}