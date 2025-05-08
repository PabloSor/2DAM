package com.pablosor.perros

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class DogAdapter(val images: List<String>):RecyclerView.Adapter<DogViewHolder>(){
    // Esta clase recive una lista de imágenes que tendremos que pasar al viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        // Aqui pasamos la imagen a el item_perro.xml

        val layoutInflater = LayoutInflater.from(parent.context)
        return DogViewHolder(layoutInflater.inflate(R.layout.item_perro, parent, false))

    }

    override fun getItemCount(): Int {
        // Tamaño de la lista de imágenes que recibe la clase
        return images.size
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val item = images[position]
        holder.render(item)
    }
}