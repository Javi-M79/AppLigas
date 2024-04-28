package com.example.appligas.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.appligas.R
import com.example.appligas.model.EquipoFavorito

class FavoritosAdapter(
    val listaFavoritos: MutableList<EquipoFavorito>,
    val listener: FavoritosAdapter.onFavoritosListener
) : RecyclerView.Adapter<FavoritosAdapter.MyHolderFavoritos>() {


    class MyHolderFavoritos(private var item: View) : ViewHolder(item) {

        var nombreFavorito: TextView = item.findViewById(R.id.nombreFavorito)
        var imagenFavorito: ImageView = item.findViewById(R.id.imagenFavorito)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolderFavoritos {
        val vista: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_favorito, parent, false)
        return MyHolderFavoritos(vista)
    }

    override fun getItemCount(): Int {
        return listaFavoritos.size
    }

    override fun onBindViewHolder(holder: MyHolderFavoritos, position: Int) {
        var favorito = listaFavoritos[position]
        holder.nombreFavorito.text = favorito.nombre
        Glide.with(holder.itemView.context)
            .load(favorito.escudo)
            .placeholder(R.drawable.soccerball)
            .into(holder.imagenFavorito)
        // Imprimir los datos para verificar si se están pasando correctamente
        println("Nombre del favorito en posición $position: ${favorito.nombre}")
        println("URL del escudo en posición $position: ${favorito.escudo}")

    }

    interface onFavoritosListener {
        fun onFavoritoSelected(favorito: EquipoFavorito)

    }


}