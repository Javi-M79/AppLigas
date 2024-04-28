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
import com.example.appligas.databinding.FragmentLoginBinding
import com.example.appligas.model.Usuario
import com.example.appligas.ui.activities.MainActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment(), OnClickListener {


    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth

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
        //Ocultar la barra de herramientas
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        super.onViewCreated(view, savedInstanceState)
        //Inicialiazar la isntancia de FIREBASE
        auth = FirebaseAuth.getInstance()
        binding.btnEntrar.setOnClickListener(this)
        binding.btnRegistro.setOnClickListener(this)
    }


    override fun onDetach() {
        super.onDetach()
    }

    override fun onClick(v: View?) {
        when (v?.id) {

            binding.btnEntrar.id -> {
                //Comprobamos que hay datos.
                if (binding.editCorreoLogin.text.isNotEmpty()
                    && binding.editPasswordLogin.text.isNotEmpty()
                ) {
                    //LOGIN EN FIREBASE
                    val correo = binding.editCorreoLogin.text.toString()
                    val password = binding.editPasswordLogin.text.toString()
                    val usuario: Usuario = Usuario(correo, password)
                    auth.signInWithEmailAndPassword(usuario.correo, usuario.password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                findNavController().navigate(R.id.action_loginFragment_to_LigaFragment)
                            } else {
                                Snackbar.make(
                                    binding.root,
                                    "Error en inicio de sesion ${task.exception?.message}",
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            }
                        }
                } else {
                    Snackbar.make(
                        binding.root,
                        resources.getString(R.string.rellenarDatos),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }

            binding.btnRegistro.id -> {
                findNavController().navigate(R.id.action_loginFragment_to_registroFragment)
            }

        }
    }
}