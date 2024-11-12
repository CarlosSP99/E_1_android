package com.utad.e_1_android.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.utad.e_1_android.databinding.ActivityModificarBinding
import com.utad.e_1_android.model.Bandera

class ModificarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityModificarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModificarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bandera = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("bandera", Bandera::class.java)
        } else {
            intent.getParcelableExtra<Bandera>("bandera")
        }

        Picasso.get()
            .load(bandera!!.imagen)
            .resize(80, 80)
            .centerInside()
            .into(binding.ivBandera)

        binding.btAceptarInfo.setOnClickListener {
            val nombreCambiado = binding.etBanderaNombre.text.toString().trim()
            if (nombreCambiado.isEmpty()) {
                binding.etBanderaNombre.error = "El nombre no puede estar vacío"
            }
            Intent().apply {
                putExtra(
                    "bandera",
                    bandera.copy(nombre = nombreCambiado)
                )
            }.let { intent ->
                setResult(RESULT_OK, intent)
            }
            finish()
        }

        binding.btRechazarModificacion.setOnClickListener {
            finish()
        }
    }
}

