package com.example.sprintm6.view.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide.init
import com.example.sprintm6.model.Phone
import com.example.sprintm6.model.PhoneRoomDatabase
import com.example.sprintm6.model.repository.PhoneRepository
import kotlinx.coroutines.launch

class PhoneViewModel  ( application: Application,private var repository: PhoneRepository) : ViewModel()  {


    init{
        val bd= PhoneRoomDatabase.getDatabase(application)
        val  centroFuturoDao = bd.PhoneDao()
        repository = PhoneRepository(centroFuturoDao)
        // lama el método del respositorio
        viewModelScope.launch {
            repository.fechPhone()
        }
    }

    // devuelve todos los datos de la bd
    val allDatos: LiveData<List<Phone>> = repository.allDatos

    // Launching a new coroutine to insert the data in a non-blocking way
    fun insertAllPhones(phone: List<Phone>) = viewModelScope.launch {
        repository.insert(phone)
    }
    // Ellimina todos los datos de la db
    //vaciar la lista de usuarios registrados
    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }
    //Elimina un usuario, un registro a traves de la id
    fun eliminarUno(id: Int) = viewModelScope.launch {
        repository.deleteUno(id)
    }

}
/*
class PhoneViewModelFactory(private val application: Application, private val repository: PhoneRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhoneViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PhoneViewModel(application, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}*/
