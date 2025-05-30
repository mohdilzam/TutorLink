package adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tutorlink.R
import model.KategoriPelajaran

class KategoriAdapter(
    private val kategoriList: List<KategoriPelajaran>,
    private val onItemClick: (KategoriPelajaran) -> Unit
) : RecyclerView.Adapter<KategoriAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgKategori: ImageView = itemView.findViewById(R.id.imgKategori)
        private val txtNama: TextView = itemView.findViewById(R.id.txtNama)

        fun bind(kategori: KategoriPelajaran) {
            txtNama.text = kategori.nama
            Glide.with(itemView.context)
                .load(kategori.imageUrl)
                .placeholder(R.drawable.placeholder_image) // ← Pastikan drawable ini ada
                .error(R.drawable.placeholder_image) // jika gagal, tampilkan ini juga
                .into(imgKategori)

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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(kategoriList[position])
    }

    override fun getItemCount(): Int = kategoriList.size
}