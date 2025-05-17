package adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tutorlink.R
import model.AllTutor

class TutorSpesialisAdapter(
    private val tutorList: List<AllTutor>,
    private val onItemClick: (AllTutor) -> Unit
) : RecyclerView.Adapter<TutorSpesialisAdapter.TutorViewHolder>() {

    inner class TutorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgTutor: ImageView = itemView.findViewById(R.id.imgTutor)
        val txtNamaTutor: TextView = itemView.findViewById(R.id.txtNamaTutor)
        val txtLokasiTutor: TextView = itemView.findViewById(R.id.txtLokasiTutor)
        val txtJarak: TextView = itemView.findViewById(R.id.txtJarak)
        val txtRating: TextView = itemView.findViewById(R.id.txtRating)
        val txtLihatProfil: TextView = itemView.findViewById(R.id.txtLihatProfil)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TutorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tutor_spesialis, parent, false)
        return TutorViewHolder(view)
    }

    override fun onBindViewHolder(holder: TutorViewHolder, position: Int) {
        val tutor = tutorList[position]
        holder.txtNamaTutor.text = tutor.nama
        holder.txtLokasiTutor.text = tutor.lokasi
        holder.txtJarak.text = "${tutor.jarak} km"
        holder.txtRating.text = "⭐ ${tutor.rating}"
        holder.imgTutor.setImageResource(tutor.imageResId)

        // Aksi klik (misalnya tampilkan detail)
        holder.txtLihatProfil.setOnClickListener {
            onItemClick(tutor)
        }
    }

    override fun getItemCount(): Int = tutorList.size
}