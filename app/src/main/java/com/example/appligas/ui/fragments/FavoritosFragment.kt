package com.example.appligas.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appligas.adapter.FavoritosAdapter
import com.example.appligas.databinding.FragmentFavoritosBinding
import com.example.appligas.model.Equipo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class FavoritosFragment : Fragment(), FavoritosAdapter.onFavoritosListener {

    private lateinit var binding: FragmentFavoritosBinding
    private lateinit var listaFavoritos: ArrayList<Equipo>

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var equipoRecuperado: Equipo
    private lateinit var favoritosAdpater: FavoritosAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Inicio del adaptador
        favoritosAdpater = FavoritosAdapter(listaFavoritos, this)
        binding.recyclerFavoritos.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerFavoritos.adapter = favoritosAdpater
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance("https://appligaspmdm-default-rtdb.firebaseio.com/")

    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }


    private fun obtenerFavorito(equipo: Equipo) {
        //Recogemos el objeto equipo enviado por EquipoFragment.
        equipoRecuperado = arguments?.getSerializable("Equipo") as Equipo
        //Lo añadimos a la lista de favoritos.
        listaFavoritos.add(equipo)
        //Notificamos cambios en la lista
        favoritosAdpater.notifyItemInserted(listaFavoritos.size - 1)
    }


}