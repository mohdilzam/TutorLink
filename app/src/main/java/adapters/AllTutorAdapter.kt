package adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tutorlink.ProfilTutorActivity
import com.example.tutorlink.R
import model.AllTutor

class AllTutorAdapter(
    private val list: List<AllTutor>,
    private val onChatClick: (AllTutor) -> Unit
) : RecyclerView.Adapter<AllTutorAdapter.TutorViewHolder>() {

    inner class TutorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imgTutor: ImageView = view.findViewById(R.id.imgTutor)
        private val txtNama: TextView = view.findViewById(R.id.txtNamaTutor)
        private val txtMapel: TextView = view.findViewById(R.id.txtMapelTutor)
        private val txtLokasi: TextView = view.findViewById(R.id.txtLokasiTutor)
        private val txtJarak: TextView = view.findViewById(R.id.txtJarak)
        private val txtRating: TextView = view.findViewById(R.id.txtRating)
        private val btnBook: Button = view.findViewById(R.id.btnBook)
        private val iconChat: ImageView = view.findViewById(R.id.iconChat)

        fun bind(tutor: AllTutor) {
            Glide.with(itemView.context)
                .load(tutor.imageUrl)
                .placeholder(R.drawable.tutor)
                .into(imgTutor)

            txtNama.text = tutor.nama
            txtMapel.text = tutor.pelajaran
            txtLokasi.text = tutor.lokasi
            txtJarak.text = "${tutor.jarak} km"
            txtRating.text = "⭐ ${tutor.rating}"

            // Klik seluruh item → buka ProfilTutorActivity
            itemView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, ProfilTutorActivity::class.java).apply {
                    putExtra("namaTutor", tutor.nama)
                    putExtra("spesialisTutor", tutor.pelajaran)
                    putExtra("biayaTutor", tutor.biaya)
                    putExtra("deskripsiTutor", tutor.deskripsi)
                    putExtra("imageUrlTutor", tutor.imageUrl) // kirim URL
                }
                context.startActivity(intent)
            }

            btnBook.setOnClickListener {
                Toast.makeText(itemView.context, "Book ${tutor.nama}", Toast.LENGTH_SHORT).show()
            }

            iconChat.setOnClickListener {
                onChatClick(tutor)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TutorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_all_tutor, parent, false)
        return TutorViewHolder(view)
    }

    override fun onBindViewHolder(holder: TutorViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}

