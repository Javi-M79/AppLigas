package com.example.appligas.ui.fragments

import android.content.Context
import android.icu.lang.UCharacter.VerticalOrientation
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.appligas.adapter.LigaAdapter
import com.example.appligas.databinding.FragmentLigaBinding
import com.example.appligas.model.Liga
import org.json.JSONArray
import org.json.JSONObject

class LigaFragment : Fragment(), LigaAdapter.onLigaListener {

    private lateinit var binding: FragmentLigaBinding
    private lateinit var ligaAdapter: LigaAdapter
    private var listaLigas: ArrayList<Liga> = ArrayList()
    private lateinit var requestQueue: RequestQueue

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
        //Metodo que trae el nombre de la liga.

        //En vez de this se usa requiredContext
        ligaAdapter = LigaAdapter(listaLigas, requireContext())
        binding.recyclerLigas.adapter = ligaAdapter
        binding.recyclerLigas.layoutManager = LinearLayoutManager(requireContext())
        mostrarLigas()

    }

    //Metodo que DESACOPLA los elementos de la pantalla con el Fragment.
    override fun onDetach() {
        super.onDetach()
    }

     fun mostrarLigas() {
        val url = "https://www.thesportsdb.com/api/v1/json/3/all_leagues.php"

        val request = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                val jsonArray = response.getJSONArray("leagues")

                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    val nombreLiga = jsonObject.getString("strLeague")
                    listaLigas.add(Liga(nombreLiga))
                    //Notificamos Cambios.
                    ligaAdapter.notifyItemInserted(listaLigas.size-1)
                }
            },
            { error ->
                println("Error al importar ligas.")
            })
        requestQueue.add(request)
    }

    override fun onLigaSelected(nombreLiga: String) {
        TODO("Not yet implemented")
    }
}







