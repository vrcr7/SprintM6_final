package com.example.sprintm6.view
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.example.sprintm6.R
import com.example.sprintm6.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var headerContainer: FrameLayout
    private lateinit var bodyContainer: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)



        // Obtén los datos pasados desde la actividad anterior
        val id = intent.getIntExtra("id", 0)
        val phoneName = intent.getStringExtra("phoneName")
        val phonePrice = intent.getIntExtra("phonePrice", 0)
        val phoneImage = intent.getStringExtra("phoneImage")
        val phoneDescription = intent.getStringExtra("phoneDescription")
        val phoneLastPrice = intent.getIntExtra("phoneLastPrice", 0)
        val phoneCredit = intent.getBooleanExtra("phoneCredit", false)

        headerContainer = findViewById(R.id.headerContainer)
        bodyContainer = findViewById(R.id.bodyContainer)





        // Cargar el fragmento de la cabecera
        val headerFragment = HeaderFragment()
        val headerBundle = Bundle().apply {
            putString("phoneName", phoneName)
            putInt("phonePrice", phonePrice)
            putString("phoneImage", phoneImage)
        }
        headerFragment.arguments = headerBundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.headerContainer, headerFragment)
            .commit()

        // Cargar el fragmento del cuerpo
        val bodyFragment = BodyFragment()
        val bodyBundle = Bundle().apply {
            putString("phoneName", phoneName)
            putInt("phonePrice", phonePrice)
            putString("phoneImage", phoneImage)
            putString("phoneDescription", phoneDescription)
            putInt("phoneLastPrice", phoneLastPrice)
            putBoolean("phoneCredit", phoneCredit)

        }
        bodyFragment.arguments = bodyBundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.bodyContainer, bodyFragment)
            .commit()
    }
}
