package com.example.sprintm6.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.example.sprintm6.R
import com.example.sprintm6.databinding.FragmentBodyBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class BodyFragment : Fragment() {
    private lateinit var binding: FragmentBodyBinding
    private var id: Int = 0
    private var phoneName: String? = null
    private var phonePrice: Int = 0
    private var phoneImage: String? = null
    private var phoneDescription: String? = null
    private var phoneLastPrice: Int = 0
    private var phoneCredit: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBodyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //enviar correo
        val sendLayout = binding.send
        sendLayout.setOnClickListener {
            val email = "mailto:info@novaera.cl"
            val intentEmail = Intent(Intent.ACTION_SEND, Uri.parse(email))
            intentEmail.type = "plain/text"
            intentEmail.putExtra(Intent.EXTRA_SUBJECT, "Consulta ${phoneName} id ${id}")
            intentEmail.putExtra(Intent.EXTRA_TEXT, "Hola\n\nVi la propiedad ${phoneName} de código ${id} y me " +
                    "gustaría que me contactaran a este correo o al siguiente número\n\nQuedo atento.")
            intentEmail.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
            startActivity(Intent.createChooser(intentEmail, "gmail"))
        }

        // Obtén los datos pasados desde los argumentos del fragmento
        arguments?.let {
            id = it.getInt("id", 0)
            phoneName = it.getString("phoneName")
            phonePrice = it.getInt("phonePrice", 0)
            phoneImage = it.getString("phoneImage")
            phoneDescription = it.getString("phoneDescription")
            phoneLastPrice = it.getInt("phoneLastPrice", 0)
            phoneCredit = it.getBoolean("phoneCredit", false)
        }

        // Asigna los datos a las vistas correspondientes
        //binding.idTextView.text = id.toString()
        binding.nameTextView.text = phoneName
        binding.priceTextView.text = "Antes $"+phonePrice.toString()
        binding.descriptionTextView.text = phoneDescription
        binding.lastPriceTextView.text = "Ahora $"+phoneLastPrice.toString()
        binding.creditTextView.text = if (phoneCredit) "Acepta crédito" else "Solo efectivo"

        // Carga la imagen utilizando Glide
        Glide.with(this)
            .load(phoneImage)
            .into(binding.imageView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
