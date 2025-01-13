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
import com.example.ecproject.databinding.FragmentMainPageBinding
import com.example.ecproject.model.Book
import com.example.ecproject.model.BooksResponse
import com.example.ecproject.ui.adapter.BooksAdapter
import com.example.ecproject.ui.adapter.ItemClickListener
import com.example.ecproject.ui.viewmodel.MainPageViewModel
import com.example.ecproject.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainPage : Fragment(), ItemClickListener {

    private val mainPageViewModel : MainPageViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentMainPageBinding? = null
    private val binding get() = _binding!!

    private lateinit var booksAdapter: BooksAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRv()
        mainPageViewModel.getBooks()
        observeViewModel()
        setOnClicks()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClicked(book: Book) {
        val action = MainPageDirections.actionMainPageToBookDetailFragment(book)
        findNavController().navigate(action)
    }

    private fun initRv() {
        booksAdapter = BooksAdapter(this)
        binding.recyclerView.adapter = booksAdapter
    }

    private fun setOnClicks() {
        binding.layoutSeeFavorite.setOnClickListener {
            val action = MainPageDirections.actionMainPageToFavoriteBooksFragment()
            findNavController().navigate(action)
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            mainPageViewModel.booksData.collectLatest { bookResponse ->
                handleBookResponse(bookResponse)
            }
        }
    }

    private fun handleBookResponse(booksResponse: Resource<BooksResponse>) {
        when(booksResponse) {
            is Resource.Success -> {
                booksResponse.data?.results?.books?.let {
                    booksAdapter.submitList(it)
                }
            }
            is Resource.Error -> {

            }
            is Resource.Loading -> {

            }
            is Resource.NoConnection -> {

            }
        }
    }
}