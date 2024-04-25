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
import com.example.appligas.databinding.FragmentRegistroBinding
import com.example.appligas.model.Usuario
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class RegistroFragment() : Fragment(), OnClickListener {


    private lateinit var binding: FragmentRegistroBinding
    private lateinit var auth: FirebaseAuth

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*Inicio de la instancia de Firebase*/
        auth = FirebaseAuth.getInstance()
        binding.BtnRegistrar.setOnClickListener(this)

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRegistroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            binding.BtnRegistrar.id -> {
                if (binding.editnombreRegistro.text.isNotEmpty()
                    && binding.editCorreoRegistro.text.isNotEmpty()
                    && binding.editPassRegistro.text.isNotEmpty()
                ) {
                    //REGISTRO DE USUARIO EN FIREBASE
                    val nombreUsuario = binding.editnombreRegistro.text.toString()
                    val mailUsuario = binding.editCorreoRegistro.text.toString()
                    val passwordUsuario = binding.editPassRegistro.text.toString()
                    val usuario: Usuario = Usuario(nombreUsuario, mailUsuario, passwordUsuario)
                    auth.createUserWithEmailAndPassword(mailUsuario, passwordUsuario)
                        //COMPROBACION DE LA INSERCION
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Snackbar.make(
                                    binding.root,
                                    "Registro realizado con exito",
                                    Snackbar.LENGTH_SHORT
                                ).show()
                                //Volvemos a la pantsalla anterior.
                                findNavController().navigate(R.id.action_registroFragment_to_loginFragment)
                            } else {
                                Snackbar.make(
                                    binding.root,
                                    "No se ha podido realizar el registro. ${task.exception?.message}",
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            }
                        }

                } else {
                    Snackbar.make(
                        binding.root,
                        "Introduzca sus datos por favor",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }

            }

        }
    }
}