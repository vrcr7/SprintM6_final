package com.example.sprintm6
import com.example.sprintm6.model.Phone
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PhoneTest {

    private lateinit var phone: Phone

    @Before
    fun setUp() {
        // Configuración inicial antes de cada test
        phone = Phone(
            id = 1,
            phoneName = "Example Phone",
            phonePrice = 999,
            phoneImage = "example_image.jpg",
            phoneDescription = "This is an example phone",
            phoneLastPrice = 899,
            phoneCredit = true
        )
    }

    @After
    fun tearDown() {
        // Acciones posteriores a cada test (limpieza, liberación de recursos, etc.)
    }

    @Test
    fun testPhoneConstructor() {
        // Verificar que los valores asignados al constructor sean correctos
        assert(phone.id == 1)
        assert(phone.phoneName == "Example Phone")
        assert(phone.phonePrice == 999)
        assert(phone.phoneImage == "example_image.jpg")
        assert(phone.phoneDescription == "This is an example phone")
        assert(phone.phoneLastPrice == 899)
        assert(phone.phoneCredit)
    }

    // Otros tests unitarios para métodos adicionales de la clase Phone

}