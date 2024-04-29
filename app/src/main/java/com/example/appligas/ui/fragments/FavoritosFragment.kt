package com.example.appligas.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appligas.adapter.FavoritosAdapter
import com.example.appligas.databinding.FragmentFavoritosBinding
import com.example.appligas.model.EquipoFavorito
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FavoritosFragment : Fragment(), FavoritosAdapter.onFavoritosListener {

    private lateinit var binding: FragmentFavoritosBinding
    private lateinit var listaFavoritos: ArrayList<EquipoFavorito>
    private lateinit var favoritosAdapter: FavoritosAdapter
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Inicio de la lista
        listaFavoritos = ArrayList()
        auth = FirebaseAuth.getInstance()
        //Acceso a la base de datos.
        database = FirebaseDatabase.getInstance("https://appligaspmdm-default-rtdb.firebaseio.com/")

//Comprobamos que recycler funciona añadiendo a mano los datos.
        /* listaFavoritos.add(
             EquipoFavorito(
                 "Real Madrid",
                 " https://www.thesportsdb.com/images/media/team/badge/vwvwrw1473502969.png"
             )
         )
         listaFavoritos.add(
             EquipoFavorito(
                 "Real Madrid1",
                 " https://www.thesportsdb.com/images/media/team/badge/vwvwrw1473502969.png"
             )
         )
         listaFavoritos.add(
             EquipoFavorito(
                 "Real Madrid2",
                 " https://www.thesportsdb.com/images/media/team/badge/vwvwrw1473502969.png"
             )
         )
         listaFavoritos.add(
             EquipoFavorito(
                 "Real Madrid3",
                 " https://www.thesportsdb.com/images/media/team/badge/vwvwrw1473502969.png"
             )
         )
         listaFavoritos.add(
             EquipoFavorito(
                 "Real Madrid4",
                 " https://www.thesportsdb.com/images/media/team/badge/vwvwrw1473502969.png"
             )
         )*/
        //Inicio del adaptador
        favoritosAdapter = FavoritosAdapter(listaFavoritos, this)
        cargarFavoritosUsuario()
        binding.recyclerFavoritos.adapter = favoritosAdapter
        binding.recyclerFavoritos.layoutManager = LinearLayoutManager(requireContext())


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onFavoritoSelected(equipoFavorito: EquipoFavorito) {

        fun onFavoritoSelected() {
            Toast.makeText(
                requireContext(),
                "prueba de funcionamiento recycler",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    //TODO NO  CARGA LA LISTA DE FAVORITOS
    fun cargarFavoritosUsuario() {
        //Obtener ID del usuario logeado.
        val referencia =
            database.getReference("usuarios").child(auth.currentUser!!.uid).child("favoritos")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    //Metodo que se ejecuta cuando un dato a cambiado

                    override fun onDataChange(snapshot: DataSnapshot) {
                        listaFavoritos.clear()
                        for (favoritoSnapshot in snapshot.children) {

                            val nombre =
                                favoritoSnapshot.child("nombre").getValue(String::class.java)
                            val escudo =
                                favoritoSnapshot.child("escudo").getValue(String::class.java)
                            Log.d("FavoritosFragment", "Nombre: $nombre, Escudo: $escudo")

                            if (nombre != null && escudo != null) {
                                val equipoFavorito = EquipoFavorito(nombre, escudo)
                                listaFavoritos.add(equipoFavorito)
                                //En este caso utilizar este metodo
                                favoritosAdapter.notifyDataSetChanged()


                            }

                        }

                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.e("datos", "Error al cargar favoritos: ${error.message}")
                    }


                })
    }
}




