package adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tutorlink.R
import model.Tutor

class TutorAdapter(private val list: List<Tutor>) :
    RecyclerView.Adapter<TutorAdapter.TutorViewHolder>() {

    inner class TutorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgTutor: ImageView = view.findViewById(R.id.imgTutor)
        val txtNama: TextView = view.findViewById(R.id.txtNamaTutor)
        val txtPelajaran: TextView = view.findViewById(R.id.txtPelajaran)
        val txtRating: TextView = view.findViewById(R.id.txtRating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TutorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tutor, parent, false)
        return TutorViewHolder(view)
    }

    override fun onBindViewHolder(holder: TutorViewHolder, position: Int) {
        val tutor = list[position]
        holder.imgTutor.setImageResource(tutor.imageResId)
        holder.txtNama.text = tutor.nama
        holder.txtPelajaran.text = tutor.pelajaran
        holder.txtRating.text = "⭐ ${tutor.rating}"
    }

    override fun getItemCount(): Int = list.size
}
