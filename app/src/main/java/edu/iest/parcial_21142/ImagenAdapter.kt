package edu.iest.parcial_21142

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ImagenAdapter(
    private val context: Context,
    private val listaImagen: ArrayList<Int>,
    private val listaTitulo: ArrayList<String>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<ImagenAdapter.ImagenViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagenViewHolder {
        val vista = LayoutInflater.from(context).inflate(R.layout.item_imagen, parent, false)
        return ImagenViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ImagenViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            listener.onItemClick(position)
        }

        holder.imagen.setImageResource(listaImagen[position])
        holder.titulo.text = listaTitulo[position]
    }

    override fun getItemCount(): Int {
        return listaImagen.size
    }

    class ImagenViewHolder(vista: View) : RecyclerView.ViewHolder(vista) {
        val imagen: ImageView = vista.findViewById(R.id.ivImagen)
        val titulo: TextView = vista.findViewById(R.id.tvTitulo)
    }
}

