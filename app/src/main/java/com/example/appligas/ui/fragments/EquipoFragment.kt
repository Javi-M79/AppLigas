package com.example.appligas.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.volley.RequestQueue
import com.example.appligas.adapter.EquipoAdapter
import com.example.appligas.adapter.LigaAdapter
import com.example.appligas.databinding.FragmentEquipoBinding
import com.example.appligas.model.Equipo

class EquipoFragment : Fragment() {


    private lateinit var binding: FragmentEquipoBinding
    private lateinit var equipoAdaptaer: EquipoAdapter
    private var listaEquipos: List<Equipo> = ArrayList()
    private lateinit var requestQueue: RequestQueue

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
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


}


