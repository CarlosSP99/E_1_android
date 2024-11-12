package com.utad.e_1_android.adapter

import android.view.ContextMenu
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.utad.e_1_android.R
import com.utad.e_1_android.databinding.ItemBanderasBinding
import com.utad.e_1_android.model.Bandera

class BanderaViewHolder(view: View) : RecyclerView.ViewHolder(view),
    View.OnCreateContextMenuListener {

    val binding = ItemBanderasBinding.bind(view)
    lateinit var bandera: Bandera

    fun render(item: Bandera, onClickListener: (Bandera) -> Unit) {
        bandera = item
        binding.tvNombre.text = bandera.nombre
        Picasso.get().load(bandera.imagen).resize(80, 80).centerInside().into(binding.ivBandera)
        itemView.setOnClickListener {
            onClickListener(bandera)
        }
        itemView.setOnCreateContextMenuListener(this)
    }

    // Context menu for onLongClickListener
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menu!!.setHeaderTitle(bandera.nombre)
        menu.add(this.adapterPosition, 0, 0, "Eliminar")
        menu.add(this.adapterPosition, 1, 1, "Modificar")
    }
}