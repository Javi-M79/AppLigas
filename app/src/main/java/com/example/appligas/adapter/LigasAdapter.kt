package com.example.appligas.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.appligas.Liga


//FRAGMENT QUE HARA DE ADAPTADOR PARA EL RECYCLER DE LAS LIGAS
class LigasAdapter (var contex: Context, val listLigas:ArrayList<Liga>): RecyclerView.Adapter<LigasAdapter.MyHolder>() {



    class MyHolder(val item: View) : ViewHolder(item) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        TODO("Not yet implemented")
    }
}