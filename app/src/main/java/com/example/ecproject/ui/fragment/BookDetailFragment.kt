package com.example.ecproject.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.ecproject.MainViewModel
import com.example.ecproject.databinding.FragmentBookDetailBinding
import com.example.ecproject.ui.viewmodel.BookDetailFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookDetailFragment : Fragment() {

    private val bookDetailViewModel : BookDetailFragmentViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentBookDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.model = bookDetailViewModel
        val args = BookDetailFragmentArgs.fromBundle(requireArguments())
        val book = args.book // Assuming 'book' is the argument name
        bookDetailViewModel.setBook(book)
        initOnClick()
    }

    private fun initOnClick() {
        binding.layoutBackButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

}