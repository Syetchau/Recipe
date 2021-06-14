package com.example.recipeapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.recipeapp.databinding.RecipeLayoutAdapterBinding
import com.example.recipeapp.fragments.HomeFragmentDirections
import com.example.recipeapp.models.Result

class RecipeAdapter: RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(val binding: RecipeLayoutAdapterBinding): RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object: DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var recipe: List<Result>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(RecipeLayoutAdapterBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val currentRecipe = recipe[position]

        holder.binding.apply {
            tvRecipeItem.text = currentRecipe.title
            ivRecipeItem.load(currentRecipe.thumbnail) {
                crossfade(true)
                crossfade(1000)
            }
        }

        holder.itemView.setOnClickListener { mView ->
            val direction = HomeFragmentDirections
                .actionHomeFragmentToDetailFragment(currentRecipe)
            mView.findNavController().navigate(direction)
        }
    }

    override fun getItemCount()= recipe.size
}