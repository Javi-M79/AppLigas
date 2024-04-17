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
import com.example.appligas.ui.fragments.LigaFragment


//FRAGMENT QUE HARA DE ADAPTADOR PARA EL RECYCLER DE LAS LIGAS
class LigaAdapter(val listaLigas: ArrayList<Liga>, val context: Context) :
    RecyclerView.Adapter<LigaAdapter.MyHolder>() {


    //TODO CALLBACK.3 CREAR UN OBJETO DE LA INTERFACE PARA PODER LLAMAR AL METODO
    private lateinit var listener: onLigaListener

    //TODO CALLBACK.4 INICIAR LA INTERFACE EN EL BLOQUE INIT DE LA CLASE MYHOLDER
    init {

        listener = context as LigaFragment
    }


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
            //TODO CALLBACK.4 LLAMO AL METODO DE LA INTERFACE
            listener.onLigaSelected(liga.nombre)
        }
    }

    //TODO CALLBACK.1 CREAR UNA INTERFACE EN EL ORIGEN DE LOS DATOS
    interface onLigaListener {
        //TODO CALLBACK.2 CREAR UN METODO CON EL DATO A COMUNICAR COMO PARAMETRO
        fun onLigaSelected(nombreLiga: String)
    }

}
