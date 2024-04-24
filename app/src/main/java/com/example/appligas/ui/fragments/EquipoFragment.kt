package com.example.appligas.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.appligas.adapter.EquipoAdapter
import com.example.appligas.databinding.FragmentEquipoBinding
import com.example.appligas.model.Equipo
import com.example.appligas.model.Liga
import com.google.android.material.snackbar.Snackbar

class EquipoFragment : Fragment(), EquipoAdapter.onEquipoListener {


    private lateinit var binding: FragmentEquipoBinding
    private lateinit var equipoAdapter: EquipoAdapter
    private val listaEquipos: ArrayList<Equipo> = ArrayList()
    private lateinit var requestQueue: RequestQueue
    private lateinit var ligaRecuperada: Liga

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        equipoAdapter = EquipoAdapter(listaEquipos, this)
        binding.recyclerEquipos.adapter = equipoAdapter
        binding.recyclerEquipos.layoutManager = LinearLayoutManager(requireContext())
//        Recuperacion de datos del fragment liga
        ligaRecuperada = arguments?.getSerializable("liga") as Liga
        obtenerEquipos(ligaRecuperada.nombre)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        requestQueue = Volley.newRequestQueue(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEquipoBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onDetach() {
        super.onDetach()
    }


    fun obtenerEquipos(nombreLiga: String) {
//TODO REVISAR URL
        val url = "https://www.thesportsdb.com/api/v1/json/3/search_all_teams.php?l=${nombreLiga}"
        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->

                val jsonArray = response.getJSONArray("teams")

                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    val nombreEquipo = jsonObject.getString("strTeam")
                    val escudoEquipo = jsonObject.getString("strTeamBadge")
                    if (jsonObject.getString("strSport").equals("Soccer")) {
                        listaEquipos.add(Equipo(nombreEquipo, escudoEquipo))
                        //Notificamos Cambios.
                        equipoAdapter.notifyItemInserted(listaEquipos.size - 1)
                    }

                }
            },
            { error ->
                Snackbar.make(binding.root, "Error al importar los Equipos.", Snackbar.LENGTH_SHORT)
                    .show()
                println("Error al importar los Equipos.")
            })
        requestQueue.add(request)

    }

    override fun onEquipoSelected(equipo: Equipo) {
        TODO("Not yet implemented")
    }

}


