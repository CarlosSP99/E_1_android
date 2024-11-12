package com.utad.e_1_android.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

// Implementamos la interfaz Parceable para poder hacer
// la edición de bandera
@Parcelize
data class Bandera(
    val id: Int,
    val nombre: String,
    val imagen: Int
) : Parcelable