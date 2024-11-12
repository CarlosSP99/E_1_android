package com.utad.e_1_android.provider

import com.utad.e_1_android.R
import com.utad.e_1_android.model.Bandera

class BanderaProvider {
    companion object {
        fun cargarBanderas(): MutableList<Bandera> {
            return mutableListOf(
                Bandera(1, "Andalucía", R.drawable.andalucia),
                Bandera(2, "Aragón", R.drawable.aragon),
                Bandera(3, "Asturias", R.drawable.asturias),
                Bandera(4, "Islas Baleares", R.drawable.baleares),
                Bandera(5, "Canarias", R.drawable.canarias),
                Bandera(6, "Cantabria", R.drawable.cantabria),
                Bandera(7, "Castilla-La Mancha", R.drawable.castillamancha),
                Bandera(8, "Castilla y León", R.drawable.castillaleon),
                Bandera(9, "Cataluña", R.drawable.catalunya),
                Bandera(10, "Extremadura", R.drawable.extremadura),
                Bandera(11, "Galicia", R.drawable.galicia),
                Bandera(12, "La Rioja", R.drawable.larioja),
                Bandera(13, "Madrid", R.drawable.madrid),
                Bandera(14, "Murcia", R.drawable.murcia),
                Bandera(15, "Navarra", R.drawable.navarra),
                Bandera(16, "País Vasco", R.drawable.paisvasco),
                Bandera(17, "Comunidad Valenciana", R.drawable.valencia),
                Bandera(18, "Ceuta", R.drawable.ceuta),
                Bandera(19, "Melilla", R.drawable.melilla)
            )
        }
    }
}