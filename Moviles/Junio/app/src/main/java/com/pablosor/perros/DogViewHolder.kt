package com.pablosor.perros

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.pablosor.perros.databinding.ItemPerroBinding
import com.squareup.picasso.Picasso

class DogViewHolder (view: View): RecyclerView.ViewHolder(view){

    // Traigo el binding a la clase
    private val binding = ItemPerroBinding.bind(view)

    // Esta clase recibe la vista que vamos a pintar
    fun render (image: String){
        Picasso.get().load(image).into(binding.ivPerro)
    }
}