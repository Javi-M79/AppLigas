package com.example.appligas.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.appligas.R
import com.example.appligas.model.Equipo
import com.example.appligas.ui.fragments.FavoritosFragment

class FavoritosAdapter(
    val listaFavoritos: ArrayList<Equipo>,
    val listener: onFavoritosListener
) : RecyclerView.Adapter<FavoritosAdapter.MyHolderFavoritos>() {

    class MyHolderFavoritos(private var item: View) : ViewHolder(item) {
        var nombreEquipo: TextView = item.findViewById(R.id.nombreEquipo)
        var escudo: ImageView = item.findViewById(R.id.imagenEquipo)
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
        var equipo = listaFavoritos[position]
        holder.nombreEquipo.text = equipo.nombre
        Glide.with(holder.itemView.context)
            .load(equipo.escudo)
            .placeholder(R.drawable.soccerball)
            .into(holder.escudo)

    }

    interface onFavoritosListener{

        class onFavoritoSelected()

    }


}