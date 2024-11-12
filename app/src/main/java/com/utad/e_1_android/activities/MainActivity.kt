package com.utad.e_1_android.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
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
    private lateinit var LayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
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
        // Función que muestra todos lo datoss
        initRecyclerView()
    }

    private fun initRecyclerView(){
        listaBanderas = BanderaProvider.cargarBanderas()
        binding.rvBanderas.layoutManager=LinearLayoutManager(this)
        // Indicamos al adaptador una función lambda para que al clickar en un item
        // podamos interactuar con el mismo
        binding.rvBanderas.adapter =
            BanderaAdapter(
                listaBanderas,
            ) { bandera -> onItemSelected(bandera) }
            // { bandera -> onLongItemSelected(bandera)}

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


    // función que muestra con un toast la bandera seleccionada
    fun onItemSelected(bandera: Bandera) {
        Toast.makeText(this, "Mi comunidad autónoma es ${bandera.nombre}", Toast.LENGTH_SHORT)
            .show()
    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        val banderaSeleccionada = listaBanderas[item.groupId]

        when (item.itemId) {
            0 -> {
                val alert = AlertDialog.Builder(this).setTitle("Eliminar ${banderaSeleccionada.nombre}")
                    .setMessage("¿Está seguro de querer eliminar ${banderaSeleccionada.nombre}?")
                    .setNeutralButton("Cerrar", null)
                    .setPositiveButton( "Aceptar") { _,_ ->
                        mostrarMsg("Se ha eliminado ${banderaSeleccionada.nombre}")
                        listaBanderas.removeAt(item.groupId)
                        binding.rvBanderas.adapter?.notifyItemRemoved(item.groupId)
                    }.create()
                alert.show()
            }
            1 -> {
                intent = Intent(this, EliminarOpcion::class.java)
                intent.putExtra("imagen_bandera", banderaSeleccionada.imagen)

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

