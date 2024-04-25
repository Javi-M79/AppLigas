package com.example.appligas.model

import android.text.Editable
import java.io.Serializable

class Usuario(

    private var nombre: String,
    private var mail: String,
    private var password: String
) : Serializable {

}
