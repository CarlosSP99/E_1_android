package com.utad.e_1_android.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.squareup.picasso.Picasso
import com.utad.e_1_android.R
import com.utad.e_1_android.databinding.ActivityEliminarOpcionBinding

class EliminarOpcion : AppCompatActivity() {
    private lateinit var binding: ActivityEliminarOpcionBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEliminarOpcionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Picasso.get().load(intent.getIntExtra("imagen_bandera",0)).resize(80,80).centerInside().into(binding.ivBandera)

        binding.btAceptarInfo.setOnClickListener {
            val nombreCambiado = binding.etBanderaNombre.text.toString().trim()
            if (nombreCambiado.isEmpty()) {
                binding.etBanderaNombre.error = "El nombre no puede estar vacío"
            }
            intent= Intent(this,MainActivity::class.java)
            intent.putExtra("nombre_bandera",nombreCambiado)
            startActivity(intent)
        }
    }


}