package adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import model.KategoriPelajaran
import com.example.tutorlink.R

//class KategoriAdapter(private val list: List<KategoriPelajaran>) :
//    RecyclerView.Adapter<KategoriAdapter.ViewHolder>() {
//
//    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val imgKategori: ImageView = view.findViewById(R.id.imgKategori)
//        val txtNama: TextView = view.findViewById(R.id.txtNama)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.item_kategori, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val kategori = list[position]
//        holder.imgKategori.setImageResource(kategori.imageResId)
//        holder.txtNama.text = kategori.nama
//    }
//
//    override fun getItemCount(): Int = list.size
//}

//class KategoriAdapter(
//    private val list: List<KategoriPelajaran>,
//    private val onItemClick: (KategoriPelajaran) -> Unit = {} // opsional
//) : RecyclerView.Adapter<KategoriAdapter.ViewHolder>() {
//
//    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val imgKategori: ImageView = view.findViewById(R.id.imgKategori)
//        val txtNama: TextView = view.findViewById(R.id.txtNama)
//
//        init {
//            view.setOnClickListener {
//                val position = adapterPosition
//                if (position != RecyclerView.NO_POSITION) {
//                    onItemClick(list[position])
//                }
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.item_kategori, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val kategori = list[position]
//        holder.imgKategori.setImageResource(kategori.imageResId)
//        holder.txtNama.text = kategori.nama
//    }
//
//    override fun getItemCount(): Int = list.size
//}

class KategoriAdapter(
    private val kategoriList: List<KategoriPelajaran>,
    private val onItemClick: (KategoriPelajaran) -> Unit
) : RecyclerView.Adapter<KategoriAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgKategori: ImageView = itemView.findViewById(R.id.imgKategori)
        private val txtNama: TextView = itemView.findViewById(R.id.txtNama)

        fun bind(kategori: KategoriPelajaran) {
            txtNama.text = kategori.nama
            imgKategori.setImageResource(kategori.imageResId)

            itemView.setOnClickListener {
                onItemClick(kategori)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_kategori, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = kategoriList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(kategoriList[position])
    }
}