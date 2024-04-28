package com.example.appligas.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.appligas.R
import com.example.appligas.databinding.FragmentRegistroBinding
import com.example.appligas.model.Equipo
import com.example.appligas.model.Usuario
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegistroFragment() : Fragment(), OnClickListener {


    private lateinit var binding: FragmentRegistroBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    lateinit var listaFavoritos: ArrayList<Equipo>

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
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
                    //REGISTRO DE USUARIO EN FIREBASE (autenticacion)
                    val nombreUsuario = binding.editnombreRegistro.text.toString()
                    val mailUsuario = binding.editCorreoRegistro.text.toString()
                    val passwordUsuario = binding.editPassRegistro.text.toString()

                    auth.createUserWithEmailAndPassword(mailUsuario, passwordUsuario)
                        //COMPROBACION DE LA INSERCION
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Snackbar.make(
                                    binding.root,
                                    "Registro realizado con exito",
                                    Snackbar.LENGTH_SHORT
                                ).show()
                                //Creamos una referencia del usuario para llevarlo a DB
                                database = FirebaseDatabase.getInstance()
                                //Crear el usuario despues de crear la instancia de la DB.
                                val usuario: Usuario =
                                    Usuario(nombreUsuario, mailUsuario, passwordUsuario)
                                val usuarioReferencia =
                                    database.getReference("usuarios").child(auth.currentUser!!.uid)
//                                //Guardamos todos los datos del usuario creado
                                usuarioReferencia.setValue(usuario)
                                //Volvemos a la pantalla de inicio
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