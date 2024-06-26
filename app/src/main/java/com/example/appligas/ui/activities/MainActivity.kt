package com.example.appligas.ui.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.appligas.R
import com.example.appligas.databinding.ActivityMainBinding
import com.example.appligas.model.Equipo
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var listaFavoritos: ArrayList<Equipo>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listaFavoritos = ArrayList()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        auth = FirebaseAuth.getInstance()
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //LOGICA DE LAS ACCIONES DEL MENU
        return when (item.itemId) {
            R.id.logout -> {
                FirebaseAuth.getInstance().signOut()
                Snackbar.make(binding.root, "Sesion cerrada con exito", Snackbar.LENGTH_SHORT)
                    .show()
                val navController = findNavController(R.id.nav_host_fragment_content_main)
                navController.navigate(R.id.loginFragment)
                true
            }

            R.id.favoritos -> {
                val navController = findNavController(R.id.nav_host_fragment_content_main)
                navController.navigate(R.id.favoritosFragment)
                true

            }


            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()

    }




}
