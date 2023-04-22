package edu.iest.parcial_21142

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView

class PreferenciasActivity : AppCompatActivity() {
    private lateinit var etNombre: EditText
    private lateinit var etNombre2: EditText
    private lateinit var etNombre3: EditText
    private lateinit var btGuardar: Button
    private lateinit var spinnerNombres: Spinner
    private lateinit var btnRegresar: Button
    private lateinit var tvDatosPrevios: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferencias)

        etNombre = findViewById(R.id.etNombre)
        etNombre2 = findViewById(R.id.etNombre2)
        etNombre3 = findViewById(R.id.etNombre3)
        btGuardar = findViewById(R.id.btGuardar)
        spinnerNombres = findViewById(R.id.spinnerNombres)
        btnRegresar = findViewById(R.id.back_button)
        tvDatosPrevios = findViewById(R.id.tvDatosPrevios)

        btnRegresar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        val nombre = sharedPref.getString("nombre", "")
        etNombre.setText(nombre)

        val nombreMascota = sharedPref.getString("nombreMascota", "")
        etNombre2.setText(nombreMascota)

        val edadGato = sharedPref.getString("edadGato", "")
        etNombre3.setText(edadGato)

        btGuardar.setOnClickListener {
            val nombre = etNombre.text.toString()
            val sharedPref = getPreferences(Context.MODE_PRIVATE)
            val nombresGuardados = sharedPref.getStringSet("nombres", setOf())?.toMutableSet()
            nombresGuardados?.add(nombre)
            with (sharedPref.edit()) {
                putStringSet("nombres", nombresGuardados)
                putString("nombre", nombre)

                val nombreMascota = etNombre2.text.toString()
                putString("nombreMascota", nombreMascota)

                val edadGato = etNombre3.text.toString()
                putString("edadGato", edadGato)

                apply()
            }
            actualizarSpinner()
        }

        actualizarSpinner()
    }

    private fun actualizarSpinner() {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        val nombresGuardados = sharedPref.getStringSet("nombres", setOf())?.toList()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, nombresGuardados ?: emptyList())
        spinnerNombres.adapter = adapter
        nombresGuardados?.let {
            if (it.isNotEmpty()) {
                tvDatosPrevios.text = "Con datos previos"
                val intent = intent
                intent.putExtra("nombres", ArrayList(it))
                setResult(RESULT_OK, intent)
            } else {
                tvDatosPrevios.text = "Sin datos previos"
            }
        }
    }
}

