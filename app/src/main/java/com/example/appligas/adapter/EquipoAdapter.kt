package com.example.appligas.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.appligas.R
import com.example.appligas.model.Equipo
import com.example.appligas.model.EquipoFavorito
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


//FRAGMENT QUE HARA DE ADAPTADOR PARA EL RECYCLER DE LOS EQUIPOS DE LA LIGA

class EquipoAdapter(
    val listaEquipos: ArrayList<Equipo>,
    val listener: onEquipoListener
) :
    RecyclerView.Adapter<EquipoAdapter.MyHolderEquipo>() {

    var listaFavoritos: ArrayList<EquipoFavorito> = ArrayList()
    var database = FirebaseDatabase.getInstance()
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val usuarioReferencia: DatabaseReference

    init {
        usuarioReferencia = FirebaseDatabase.getInstance().getReference("usuarios")
    }

    class MyHolderEquipo(private var item: View) : ViewHolder(item) {

        var nombreEquipo: TextView = item.findViewById(R.id.nombreEquipo)
        var escudo: ImageView = item.findViewById(R.id.imagenEquipo)
        var botonFavorito: ImageButton = item.findViewById(R.id.botonAddFavorito)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EquipoAdapter.MyHolderEquipo {
        val vista: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_equipo, parent, false)
        return MyHolderEquipo(vista)
    }

    override fun onBindViewHolder(holder: EquipoAdapter.MyHolderEquipo, position: Int) {
        var equipo = listaEquipos[position]
        holder.nombreEquipo.text = equipo.nombre
        Glide.with(holder.itemView.context)
            .load(equipo.escudo)
            .placeholder(R.drawable.soccerball)
            .into(holder.escudo)
        /* holder.nombreEquipo.setOnClickListener {
             listener.onEquipoSelected(equipo)
         }*/

        holder.botonFavorito.setOnClickListener {

            val equipoFavorito = EquipoFavorito(equipo.nombre, equipo.escudo)
            Snackbar.make(holder.itemView, "Equipo añadido a favoritos", Snackbar.LENGTH_SHORT)
                .show()
            usuarioReferencia.child(auth.currentUser!!.uid).child("favoritos").push()
                .setValue(equipoFavorito)
            listaFavoritos.add(equipoFavorito)
        }


    }

    override fun getItemCount(): Int {
        return listaEquipos.size
    }


    interface onEquipoListener {
        fun onEquipoSelected(equipo: Equipo)


    }


}













