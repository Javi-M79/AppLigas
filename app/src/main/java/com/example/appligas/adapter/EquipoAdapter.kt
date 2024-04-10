package com.example.appligas.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.appligas.model.Equipo


//FRAGMENT QUE HARA DE ADAPTADOR PARA EL RECYCLER DE LOS EQUIPOS DE LA LIGA
class EquipoAdapter (var contex: Context, listaLigas: ArrayList<Equipo>): RecyclerView.Adapter<EquipoAdapter.MyHolder>() {


    class MyHolder (item: View) :ViewHolder(item){



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipoAdapter.MyHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: EquipoAdapter.MyHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


}