package com.utad.e_1_android.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.utad.e_1_android.R
import com.utad.e_1_android.adapter.BanderaAdapter
import com.utad.e_1_android.databinding.ActivityMainBinding
import com.utad.e_1_android.model.Bandera
import com.utad.e_1_android.provider.BanderaProvider

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var listaBanderas: MutableList<Bandera>
    private lateinit var adapter: BanderaAdapter


    // función que recogerá la lógica de EliminarActivity
    private val modifciarOpcionLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val bandera = result.data?.getParcelableExtra<Bandera>("bandera")
                adapter.updateBandera(bandera!!)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        registerForContextMenu(binding.rvBanderas)

        // -----Explicación para mi-----
        // Configuramos el onclick de la toolbar

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Función que muestra todos lo datos
        initRecyclerView()
    }

    // Declaro el layaout en el xml
    private fun initRecyclerView() {
        listaBanderas = BanderaProvider.cargarBanderas()
        adapter = BanderaAdapter(
            listaBanderas = listaBanderas,
            onClickListener = {
                bandera -> onItemSelected(bandera)
            }
        )
        binding.rvBanderas.adapter = adapter
    }


    // ---------------para el menu de opciones del toolbar----------------
    // Inflating the menu items from the menu_items.xml file
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_opciones, menu)
        return super.onCreateOptionsMenu(menu)
    }


    // Handling the click events of the menu items
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Switching on the item id of the menu item
        when (item.itemId) {
            R.id.menu_opciones_recargar -> {
                mostrarDatos()
                return true
            }

            R.id.menu_opciones_limpiar -> {
                limpiarListado()
                return true
            }

            else -> false
        }
        return super.onOptionsItemSelected(item)
    }
    // ---------------para el menu de opciones del toolbar----------------


    // Función que muestra con un toast la bandera seleccionada
    fun onItemSelected(bandera: Bandera) {
        Toast.makeText(this, "Mi comunidad autónoma es ${bandera.nombre}", Toast.LENGTH_SHORT)
            .show()
    }


    // ---------------Para el menu de opciones del onClickListener----------------
    override fun onContextItemSelected(item: MenuItem): Boolean {
        val banderaSeleccionada = listaBanderas[item.groupId]

        when (item.itemId) {
            // Eliminar
            0 -> {
                val alert =
                    AlertDialog.Builder(this).setTitle("Eliminar ${banderaSeleccionada.nombre}")
                        .setMessage("¿Está seguro de querer eliminar ${banderaSeleccionada.nombre}?")
                        .setNeutralButton("Cerrar", null)
                        .setPositiveButton("Aceptar") { _, _ ->
                            mostrarMsg("Se ha eliminado ${banderaSeleccionada.nombre}")
                            adapter.deleteBandera(banderaSeleccionada)
                        }.create()
                alert.show()
            }
            // Modificar
            1 -> {
                // se recoge la bandera que queremos modificar y la enviamos parseada a ModificarActivity
                intent = Intent(this, ModificarActivity::class.java)
                intent.putExtra("bandera", banderaSeleccionada)
                modifciarOpcionLauncher.launch(intent)
            }
        }

        return true
    }

    fun mostrarMsg(mensaje: String) {
        Snackbar.make(binding.root, mensaje, Snackbar.LENGTH_SHORT).show()
    }


    private fun mostrarDatos() {
        initRecyclerView()
    }

    // Función que elimina toda la info y sustituye todos los parámetros anteriores
    // para mostrar lista en blanco
    private fun limpiarListado() {
        listaBanderas.clear()
        // Indicamos al adaptador una función lambda para que al clickar en un item
        // podamos interactuar con el mismo
        binding.rvBanderas.adapter = BanderaAdapter(
            listaBanderas
        ) { bandera -> onItemSelected(bandera) }
    }
}

