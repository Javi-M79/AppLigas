package com.example.appligas.controller

import com.example.appligas.model.Equipo
import com.example.appligas.model.Usuario

class UserController {

    fun addFavorito(usuario: Usuario, equipo: Equipo) {
        usuario.favoritos.add(equipo)
    }

    fun mostrarFavoritos(usuario: Usuario, listaFavoritos: ArrayList<Equipo>): ArrayList<Equipo> {
        usuario.favoritos = listaFavoritos
        return listaFavoritos
    }
}