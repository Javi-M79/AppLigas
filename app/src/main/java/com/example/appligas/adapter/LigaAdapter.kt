package com.example.appligas.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.appligas.R
import com.example.appligas.model.Liga


//FRAGMENT QUE HARA DE ADAPTADOR PARA EL RECYCLER DE LAS LIGAS
class LigaAdapter(val listaLigas: ArrayList<Liga>, val context: Context) :
    RecyclerView.Adapter<LigaAdapter.MyHolder>() {


    class MyHolder(val item: View) : ViewHolder(item) {

        var nombre: TextView = item.findViewById(R.id.nombreliga)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val vista: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_liga, parent, false)
        return MyHolder(vista)
    }

    override fun getItemCount(): Int = listaLigas.size


    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        //Quiero que el nombre de la liga lo traiga de una API
        val liga: Liga = listaLigas[position]
        holder.nombre.text = liga.nombre

    }
}