package com.example.appligas.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.appligas.R
import com.example.appligas.databinding.FragmentRegistroBinding
import com.google.android.material.snackbar.Snackbar

class RegistroFragment() : Fragment(), OnClickListener {


    private lateinit var binding: FragmentRegistroBinding


    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

                Snackbar.make(
                    binding.root,
                    resources.getString(R.string.botonregistro),
                    Snackbar.LENGTH_SHORT
                ).show()
            }

        }
    }
}