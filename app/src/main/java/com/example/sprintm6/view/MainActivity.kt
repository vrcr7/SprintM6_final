package com.example.sprintm6.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.sprintm6.R
import com.example.sprintm6.databinding.ActivityMainBinding
import com.example.sprintm6.model.Phone
import com.example.sprintm6.model.PhoneRoomDatabase
import com.example.sprintm6.model.repository.PhoneRepository
import com.example.sprintm6.view.viewmodel.PhoneViewModel


class MainActivity : AppCompatActivity() {
    // Declaración de variables globales
    private lateinit var binding: ActivityMainBinding
    private lateinit var phoneViewModel: PhoneViewModel
    internal var data: MutableLiveData<List<Phone>> = MutableLiveData()
    private lateinit var headerContainer: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Instanciar la base de datos y el DAO
        val database = PhoneRoomDatabase.getDatabase(applicationContext)
        val phoneDao = database.PhoneDao()
        // Crear una instancia del repositorio pasando el DAO
        val phoneRepository = PhoneRepository(phoneDao)
        // Crear una instancia de PhoneViewModel directamente
        phoneViewModel = PhoneViewModel(application, phoneRepository)
        //cargamos el header
        headerContainer = findViewById(R.id.headerContainer)
        val headerFragment = HeaderFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.headerContainer, headerFragment)
            .commit()

        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        if (fragment == null) {
            phoneViewModel.allDatos.observe(this, Observer { newList ->
                // Actualizar la variable de datos
                data.value = newList
                Log.i("", data.value?.size.toString())

                // Verificar si la data está cargada
                if (data.value != null && data.value!!.isNotEmpty()) {
                    // Agregar el fragmento si no ha sido agregado antes
                    val newFragment = ListaFragment()
                    supportFragmentManager.beginTransaction()
                        .setReorderingAllowed(true)
                        .add(R.id.fragmentContainer, newFragment)
                        .commit()
                }
            })
        }
    }


    fun mostrar(id: String)
    {
        val phoneId: Int =  id.toInt()// Obtén el ID del teléfono deseado

// Obtén el objeto Phone correspondiente al ID
        val phone: Phone? = data.value?.find { it.id == phoneId }

// Verifica si se encontró el teléfono
        if (phone != null) {
            val intent = Intent(this, DetailActivity::class.java)
            // Agrega los datos del objeto Phone al intent utilizando putExtra
            intent.putExtra("id", phone.id)
            intent.putExtra("phoneName", phone.phoneName)
            intent.putExtra("phonePrice", phone.phonePrice)
            intent.putExtra("phoneImage", phone.phoneImage)
            intent.putExtra("phoneDescription", phone.phoneDescription)
            intent.putExtra("phoneLastPrice", phone.phoneLastPrice)
            intent.putExtra("phoneCredit", phone.phoneCredit)
            // Inicia la actividad con el intent
            startActivity(intent)
        } else {
            // Maneja el caso en el que no se encuentre el teléfono
            Toast.makeText(this, "Teléfono no encontrado", Toast.LENGTH_SHORT).show()
        }
    }
}