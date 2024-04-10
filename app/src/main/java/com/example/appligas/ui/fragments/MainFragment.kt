package com.example.appligas.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.appligas.R
import com.example.appligas.databinding.FragmentMainBinding

class MainFragment : Fragment(), OnClickListener {


    private lateinit var binding: FragmentMainBinding

    //Metodo que ACOPLA los elementos de la pantalla con el Fragment.
    override fun onAttach(context: Context) {
        super.onAttach(context)

        //Si pongo a escuchar este biton, la aplicacion no pasa de fragment y se cierra.???
//        binding.btnback.setOnClickListener(this)

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    //Metodo que DESACOPLA los elementos de la pantalla con el Fragment.
    override fun onDetach() {
        super.onDetach()
    }

    override fun onClick(v: View?) {
        when (v?.id) {

            //No funciona este metodo.??
            binding.btnback.id -> {
                findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
            }
        }
    }


}