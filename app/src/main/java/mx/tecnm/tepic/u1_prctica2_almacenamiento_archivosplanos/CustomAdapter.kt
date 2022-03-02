package mx.tecnm.tepic.u1_prctica2_almacenamiento_archivosplanos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter: RecyclerView.Adapter<CustomAdapter.ViewHolder>(){

    /*val nombre = arrayOf(Archivos.nombres[0])
    val dueno = arrayOf(Archivos.duenos[0])
    val raza = arrayOf(Archivos.razas[0])*/

    val images = intArrayOf(R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground)

    override fun onCreateViewHolder(vieGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(vieGroup.context).inflate(R.layout.card_layout,vieGroup,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemImage.setImageResource(images[i])
        viewHolder.itemNombre.text= Archivos.nombres[i]
        viewHolder.itemDueno.text= Archivos.duenos[i]
        viewHolder.itemRaza.text= Archivos.razas[i]
    }

    override fun getItemCount(): Int {
        var co=0
        for (i in 0..9){
            if (Archivos.nombres[i].equals("")){
                co=i
                break
            }
        }

        return co
    }
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemNombre:TextView
        var itemDueno:TextView
        var itemRaza:TextView
        init {
            itemImage = itemView.findViewById(R.id.item_image)
            itemNombre = itemView.findViewById(R.id.item_nombre)
            itemDueno = itemView.findViewById(R.id.item_dueno)
            itemRaza = itemView.findViewById(R.id.item_raza)
        }
    }
}