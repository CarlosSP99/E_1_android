package com.utad.e_1_android.provider

import com.utad.e_1_android.R
import com.utad.e_1_android.model.Bandera

class BanderaProvider {
    companion object{
        fun cargarBanderas(): MutableList<Bandera> {
            return mutableListOf<Bandera>(
                Bandera(
                    "Andalucía",
                    R.drawable.andalucia
                ),
                Bandera(
                    "Aragón",
                    R.drawable.aragon
                ),
                Bandera("Asturias", R.drawable.asturias),
                Bandera("Islas Baleares", R.drawable.baleares),
                Bandera("Canarias", R.drawable.canarias),
                Bandera("Cantabria", R.drawable.cantabria),
                Bandera("Castilla-La Mancha", R.drawable.castillamancha),
                Bandera("Castilla y León", R.drawable.castillaleon),
                Bandera("Cataluña", R.drawable.catalunya),
                Bandera("Extremadura", R.drawable.extremadura),
                Bandera("Galicia", R.drawable.galicia),
                Bandera("La Rioja", R.drawable.larioja),
                Bandera("Madrid", R.drawable.madrid),
                Bandera("Murcia", R.drawable.murcia),
                Bandera("Navarra", R.drawable.navarra),
                Bandera("País Vasco", R.drawable.paisvasco),
                Bandera("Comunidad Valenciana", R.drawable.valencia),
                Bandera("Ceuta", R.drawable.ceuta),
                Bandera("Melilla", R.drawable.melilla)
            )
        }
    }
}