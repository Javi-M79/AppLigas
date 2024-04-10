package com.example.appligas.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appligas.R
import com.example.appligas.adapter.LigaAdapter
import com.example.appligas.databinding.FragmentMainBinding
import com.example.appligas.model.Liga

class MainFragment : Fragment(), OnClickListener {


    private lateinit var binding: FragmentMainBinding
    private lateinit var ligaAdapter: LigaAdapter
    private var listaLigas: ArrayList<Liga> = ArrayList()

    //Metodo que ACOPLA los elementos de la pantalla con el Fragment.
    override fun onAttach(context: Context) {
        super.onAttach(context)


    }


    //METODO OBLIGATORIO. Retorna la vista que mostrara el fragment. Es decir su Layout. (fragment_login.xml)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root


    }

    //En este metodo incluimos la logica de la UI (escucha de botones, etc)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listaLigas.add(Liga("Liga Santander"))
        listaLigas.add(Liga("Liga BBVA"))
        listaLigas.add(Liga("Liga BBVA"))
        listaLigas.add(Liga("Liga BBVA"))
        listaLigas.add(Liga("Liga BBVA"))
        listaLigas.add(Liga("Liga BBVA"))
        listaLigas.add(Liga("Liga BBVA"))
        listaLigas.add(Liga("Liga BBVA"))
        listaLigas.add(Liga("Liga BBVA"))
        //En vez d ethis se usa requiredContext
        ligaAdapter = LigaAdapter(listaLigas, requireContext())
        binding.recyclerLigas.adapter = ligaAdapter
        binding.recyclerLigas.layoutManager = LinearLayoutManager(requireContext())
        binding.btnback.setOnClickListener(this)
    }


    //Metodo que DESACOPLA los elementos de la pantalla con el Fragment.
    override fun onDetach() {
        super.onDetach()    }

    override fun onClick(v: View?) {
        when (v?.id) {


            }
        }
    }


