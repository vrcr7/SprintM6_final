package com.example.sprintm6.view
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sprintm6.R

import androidx.lifecycle.MutableLiveData

import com.example.sprintm6.model.Phone


class ListaFragment : Fragment() {

    private lateinit var adapter: PhoneListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflar el layout del fragmento
        val view = inflater.inflate(R.layout.fragment_lista, container, false)
        // Obtener referencia al RecyclerView desde el layout
        val recyclerView: RecyclerView = view.findViewById(R.id.listaRecyclerView)
        // Crear instancia del adaptador DatosListAdapter y asignarlo al RecyclerView
        // Pasamos la lista de datos obtenida desde la actividad principal (MainActivity)
        recyclerView.layoutManager = GridLayoutManager(this.requireContext(),3)

        adapter = PhoneListAdapter((activity as MainActivity).data)
        recyclerView.adapter = adapter
        Log.i("","desde el fragmento")
        Log.i("",adapter.itemCount.toString())

        adapter.setMostrarButtonClickListener { id ->
            // Llama a la función "mostrar" en tu MainActivity y pasa el ID
            (activity as MainActivity).mostrar(id)
        }


        return view
    }
}
