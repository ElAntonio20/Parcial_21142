package edu.iest.parcial_21142

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

interface OnItemClickListener {
    fun onItemClick(position: Int)
}

class MainActivity : AppCompatActivity(), OnItemClickListener {

    private lateinit var rvListado: RecyclerView
    private lateinit var listaImagen: ArrayList<Int>
    private lateinit var listaTitulo: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        poblarLista()
        rvListado = findViewById(R.id.rvListado)
        rvListado.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        rvListado.adapter = ImagenAdapter(this, listaImagen, listaTitulo, this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_keyboard_return_24)
        supportActionBar?.title = "Menú principal"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_wifi, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuCerrar -> {
                finishAffinity()
                true
            }
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.menuWifi -> {
                val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val networkInfo = connectivityManager.activeNetworkInfo
                if (networkInfo != null && networkInfo.isConnected && networkInfo.type == ConnectivityManager.TYPE_WIFI) {
                    Toast.makeText(this, "El dispositivo está conectado a Wi-Fi", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "El dispositivo no está conectado a Wi-Fi", Toast.LENGTH_SHORT).show()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun poblarLista() {
        listaTitulo = arrayListOf("Gatos", "Perfil", "Configuración", "Cerrar sesión", "Salir")
        listaImagen = arrayListOf(
            R.drawable.cat,
            R.drawable.profile,
            R.drawable.config,
            R.drawable.close
        )
    }

    override fun onItemClick(position: Int) {
        when (position) {
            1 -> {
                val intent = Intent(this, PreferenciasActivity::class.java)
                startActivity(intent)
            }
            3 -> {
                finishAffinity()
            }
            else -> {
                Toast.makeText(this, "Clic en imagen de ${listaTitulo[position]}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
