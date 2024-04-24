package com.example.appligas.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.appligas.R
import com.example.appligas.adapter.LigaAdapter
import com.example.appligas.databinding.FragmentLigaBinding
import com.example.appligas.model.Liga
import com.google.android.material.snackbar.Snackbar

//IMPLEMENTAMOS LA INTERFACE EN EL FRAGMENT.

class LigaFragment : Fragment(), LigaAdapter.onLigaListener {

    private lateinit var binding: FragmentLigaBinding
    private lateinit var ligaAdapter: LigaAdapter
    private var listaLigas: ArrayList<Liga> = ArrayList()
    private lateinit var requestQueue: RequestQueue
    private lateinit var equipo: EquipoFragment


    //Metodo que ACOPLA los elementos de la pantalla con el Fragment.
    override fun onAttach(context: Context) {
        super.onAttach(context)
        requestQueue = Volley.newRequestQueue(context)

    }

    //METODO OBLIGATORIO. Retorna la vista que mostrara el fragment. Es decir su Layout. (fragment_login.xml)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLigaBinding.inflate(inflater, container, false)
        return binding.root

    }

    //En este metodo incluimos la logica de la UI (escucha de botones, etc)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //CALLBACK. INICIAMOS EL OBJETO. POR CONTESTO LE PASAMOS THIS QUE CORRESPONDE AL OBJETO LISTENER DE LA INTERFACE
        ligaAdapter = LigaAdapter(listaLigas, this)
        binding.recyclerLigas.adapter = ligaAdapter
        //En este caso en vez de this se usa requiredContext ya que la View ya esta inflada a traves del Binding.root.
        binding.recyclerLigas.layoutManager = LinearLayoutManager(requireContext())
        mostrarLigas()

    }

    //Metodo que DESACOPLA los elementos de la pantalla con el Fragment.
    override fun onDetach() {
        super.onDetach()
    }

    //Metodo que trae el nombre de la liga.
    fun mostrarLigas() {
        val url = "https://www.thesportsdb.com/api/v1/json/3/all_leagues.php"

        val request = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->

                val jsonArray = response.getJSONArray("leagues")

                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    val nombreLiga = jsonObject.getString("strLeague")
                    if (jsonObject.getString("strSport").equals("Soccer")) {
                        listaLigas.add(Liga(nombreLiga))
                        //Notificamos Cambios.
                        ligaAdapter.notifyItemInserted(listaLigas.size - 1)
                    }

                }
            },
            { error ->
                Snackbar.make(binding.root, "Error al importar las ligas.", Snackbar.LENGTH_SHORT).show()
                println("Error al importar ligas.")
            })
        requestQueue.add(request)
    }

    //CALLBACK. IMPLEMENTADA LA INTERFACE EN EL FRAGMENT PARA PODER HACER EL CASTEO

    override fun onLigaSelected(liga: Liga) {
        //Prueba del funcionamiento de la pulsacion
//        Snackbar.make(
//            binding.root,
//            "Liga pulsada con nombre ${liga.nombre}",
//            Snackbar.LENGTH_SHORT
//        ).show()

        //Navegar al fragment Equipos -> pasar el nombre de la liga para concatenarlo a la url

        //PASAR DATOS ENTRE FRAGMENTS
        val bundle = Bundle()
        bundle.putSerializable("liga", liga)
        findNavController().navigate(R.id.action_ligaFragment_to_equipoFragment, bundle)


    }
}







