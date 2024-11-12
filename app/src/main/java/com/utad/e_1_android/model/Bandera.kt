package com.utad.e_1_android.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Bandera(
    val id: Int,
    val nombre: String,
    val imagen: Int
) : Parcelable