package com.example.appligas.model

import java.io.Serializable

class Usuario(
    val nombre: String,
    val correo: String,
    val password: String,
    var favoritos: ArrayList<Equipo>

) : Serializable {
    constructor(correo: String, password: String) : this("", correo, password, ArrayList())

    constructor() : this("", "", "", ArrayList())
    constructor(nombre: String, correo: String, password: String) : this(
        nombre,
        correo,
        password,
        ArrayList()
    )


}
