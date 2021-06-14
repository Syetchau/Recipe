package com.example.recipeapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.recipeapp.R
import com.example.recipeapp.databinding.FragmentDetailBinding
import com.example.recipeapp.models.Result

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var _binding: FragmentDetailBinding?= null
    private val binding get() = _binding!!
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var recipe: Result

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recipe = args.recipe

        initView()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initView() {
        binding.apply {
            tvRecipeIngredientsDetail.text = recipe.ingredients
            tvRecipeItemDetail.text = recipe.title
            ivRecipeItemDetail.load(recipe.thumbnail) {
                crossfade(true)
                crossfade(1000)
            }

            btnRecipeReference.setOnClickListener { mView ->
                val direction = DetailFragmentDirections
                    .actionDetailFragmentToWebViewFragment(recipe)
                mView.findNavController().navigate(direction)
            }
        }
    }
}