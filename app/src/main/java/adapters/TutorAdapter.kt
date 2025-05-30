package adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tutorlink.R
import model.Tutor

class TutorAdapter(private var originalList: List<Tutor>) :
    RecyclerView.Adapter<TutorAdapter.TutorViewHolder>() {

    private var filteredList: List<Tutor> = originalList.toList()

    inner class TutorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgTutor: ImageView = view.findViewById(R.id.imgTutor)
        val txtNama: TextView = view.findViewById(R.id.txtNamaTutor)
        val txtMapel: TextView = view.findViewById(R.id.txtPelajaran)
        val txtRating: TextView = view.findViewById(R.id.txtRating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TutorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tutor, parent, false)
        return TutorViewHolder(view)
    }

    override fun onBindViewHolder(holder: TutorViewHolder, position: Int) {
        val tutor = filteredList[position]

        Glide.with(holder.itemView.context)
            .asBitmap()
            .load(tutor.imageUrl)
            .placeholder(R.drawable.tutor)
            .error(R.drawable.tutor)
            .into(holder.imgTutor)

        holder.txtNama.text = tutor.nama
        holder.txtMapel.text = tutor.pelajaran
        holder.txtRating.text = "⭐ ${tutor.rating}"
    }

    override fun getItemCount(): Int = filteredList.size

    fun filter(query: String) {
        filteredList = if (query.isEmpty()) {
            originalList
        } else {
            originalList.filter {
                it.nama.contains(query, ignoreCase = true)
            }
        }
        notifyDataSetChanged()
    }
}
