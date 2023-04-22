package edu.iest.parcial_21142

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class NombresGuardadosActivity : AppCompatActivity() {
    private lateinit var spinnerNombres: Spinner
    private lateinit var nombres: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nombres_guardados)

        // Obtener la referencia al spinner en el archivo XML
        spinnerNombres = findViewById(R.id.spinnerNombres)

        // Obtener los nombres desde la actividad anterior
        nombres = intent.getStringArrayListExtra("nombres") ?: ArrayList()

        // Crear un adaptador para el spinner con los nombres
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, nombres)

        // Asignar el adaptador al spinner
        spinnerNombres.adapter = adapter
    }
}
