package com.example.appligas.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.appligas.R
import com.example.appligas.model.Equipo


//FRAGMENT QUE HARA DE ADAPTADOR PARA EL RECYCLER DE LOS EQUIPOS DE LA LIGA

class EquipoAdapter(val listaEquipos: ArrayList<Equipo>, val contex: Context) :
    RecyclerView.Adapter<EquipoAdapter.MyHolderEquipo>() {


    class MyHolderEquipo(private var item: View) : ViewHolder(item) {

        var nombreEquipo: TextView = item.findViewById(R.id.nombreEquipo)
        var imagen: ImageView = item.findViewById(R.id.imagenEquipo)

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EquipoAdapter.MyHolderEquipo {

        val vista: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_liga, parent, false)

        return MyHolderEquipo(vista)
    }


    override fun onBindViewHolder(holder: EquipoAdapter.MyHolderEquipo, position: Int) {
        var equipo = listaEquipos[position]
        holder.nombreEquipo
    }

    override fun getItemCount(): Int {
        return listaEquipos.size
    }
}








