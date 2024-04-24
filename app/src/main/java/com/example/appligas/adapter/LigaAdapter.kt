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
import com.example.appligas.ui.activities.MainActivity
import com.example.appligas.ui.fragments.LigaFragment


//FRAGMENT QUE HARA DE ADAPTADOR PARA EL RECYCLER DE LAS LIGAS

//CALLBACK.3 CREAR UN OBJETO DE LA INTERFACE PARA PODER LLAMAR AL METODO. AL TRATARSE DE UN FRAGMENT LO METEMOS EN EL CONSTRUCTOR PARA PODER CASTEARLO.
/*En este caso el listener actua como un objeto de */
class LigaAdapter(
    private val listaLigas: ArrayList<Liga>,
    private val listener: onLigaListener
) : RecyclerView.Adapter<LigaAdapter.MyHolder>() {

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
        val liga: Liga = listaLigas[position]
        holder.nombre.text = liga.nombre
        holder.nombre.setOnClickListener {
            //TODO CALLBACK.5 LLAMO AL METODO DE LA INTERFACE
            listener.onLigaSelected(liga)
        }
    }

    // CALLBACK.1 CREAR UNA INTERFACE EN EL ORIGEN DE LOS DATOS
    interface onLigaListener {
        // CALLBACK.2 CREAR UN METODO CON EL DATO A COMUNICAR COMO PARAMETRO
        fun onLigaSelected(liga: Liga)
    }

}
