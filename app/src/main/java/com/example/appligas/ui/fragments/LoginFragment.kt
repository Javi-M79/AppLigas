package com.example.appligas.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.appligas.R
import com.example.appligas.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar

class LoginFragment : Fragment(), OnClickListener {


    private lateinit var binding: FragmentLoginBinding


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
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    /*Aqui ya tenemos la vista creada y pegada al fragment.
    dentro de este metodo ponemos a escuchar a los botones.
    */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnEntrar.setOnClickListener(this)
        binding.btnRegistro.setOnClickListener(this)
    }


    //Metodo que DESACOPLA los elementos de la pantalla con el Fragment.
    override fun onDetach() {
        super.onDetach()
    }

    override fun onClick(v: View?) {
        when (v?.id) {

            binding.btnEntrar.id -> {
                //Comprobamos que hay datos.
                if (binding.editUser.text.isNotEmpty()
                    && binding.editPassword.text.isNotEmpty()
                ) {
                    //Navegamos a otro fragment con la lista de ligas.
//                    Snackbar.make(
//                        binding.root,
//                        resources.getString(R.string.datosOk),
//                        Snackbar.LENGTH_SHORT
//                    ).show()
                    findNavController().navigate(R.id.action_loginFragment_to_mainFragment)

                } else {
                    Snackbar.make(
                        binding.root,
                        resources.getString(R.string.rellenarDatos),
                        Snackbar.LENGTH_SHORT
                    ).show()

                }
            }

            binding.btnRegistro.id->{

                //Prueba de funcionamineto de pulsacion.
                Snackbar.make(binding.root,"Boton registro pulsado", Snackbar.LENGTH_SHORT).show()
            }

        }
    }


}