package com.pablosor.postsapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.pablosor.postsapp.databinding.PostsBinding

class PostViewHolder (view: View): RecyclerView.ViewHolder(view) {
    // Binding
    private val binding = PostsBinding.bind(view)

    fun render(post: PostResponse){
        binding.tvTitulo.text = post.titulo
        binding.tvTexto.text = post.contenido
    }
}