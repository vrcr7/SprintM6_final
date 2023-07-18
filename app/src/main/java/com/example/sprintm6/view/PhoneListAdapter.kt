package com.example.sprintm6.view
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sprintm6.R
import com.example.sprintm6.model.Phone


class PhoneListAdapter(private val datos: MutableLiveData<List<Phone>>) : RecyclerView.Adapter<PhoneListAdapter.DatosViewHolder>() {


    fun setMostrarButtonClickListener(listener: (String) -> Unit) {
        mostrarButtonClickListener = listener
    }

    internal var mostrarButtonClickListener: ((String) -> Unit)? = null

    fun setDeleteButtonClickListener(listener: (String) -> Unit) {
        mostrarButtonClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DatosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.article_item, parent, false)
        return DatosViewHolder(view)
    }

    override fun onBindViewHolder(holder: DatosViewHolder, position: Int) {
        val currentDatos = datos.value?.get(position)
        holder.bind(currentDatos)
    }
    override fun getItemCount(): Int = datos.value?.size ?: 0
    inner class DatosViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var idMostrar: String = ""
        private val nombreTextView: TextView = view.findViewById(R.id.name)
        private val precioTextView: TextView = view.findViewById(R.id.price)
        private val imagenPhone: ImageView = view.findViewById(R.id.listImage)
        private val mostrarButton: Button = view.findViewById(R.id.mostrarButton)

        fun bind(datos: Phone?) {
            datos?.let {
                nombreTextView.text = it.phoneName
                precioTextView.text = it.phonePrice.toString()
                idMostrar = it.id.toString()

                Log.i("","el id para mostrar en el adapter es : ")
                Log.i("","$idMostrar")

                Glide.with(itemView)
                    .load(it.phoneImage)
                    .into(imagenPhone)
                mostrarButton.setOnClickListener {
                    mostrarButtonClickListener?.invoke(idMostrar)
                }
            }
        }
    }
}
