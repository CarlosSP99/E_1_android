package com.utad.e_1_android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utad.e_1_android.R
import com.utad.e_1_android.model.Bandera


// Añadimos un oneclicklistener con el objetivo que el usuario al pinchar
// en el rv pueda interactuar con los objetos del mismo
class BanderaAdapter(
    private val listaBanderas: MutableList<Bandera>,
    private val onClickListener: (Bandera) -> Unit
) :
    RecyclerView.Adapter<BanderaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BanderaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BanderaViewHolder(layoutInflater.inflate(R.layout.item_banderas, parent, false))
    }

    override fun onBindViewHolder(holder: BanderaViewHolder, position: Int) {
        val bandera = listaBanderas[position]
        holder.render(bandera, onClickListener)
    }

    override fun getItemCount(): Int {
        return listaBanderas.size
    }

    fun updateBandera(bandera: Bandera) {
        val index = listaBanderas.indexOfFirst { it.id == bandera.id }
        if (index != -1) {
            listaBanderas[index] = bandera
            notifyItemChanged(index)
        }
    }

    fun deleteBandera(bandera:Bandera){
        val index = listaBanderas.indexOfFirst { it.id == bandera.id }
        if (index != -1) {
            listaBanderas.removeAt(index)
            notifyItemRemoved(index)
        }
    }
}