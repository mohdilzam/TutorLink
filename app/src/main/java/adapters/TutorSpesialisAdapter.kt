package adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tutorlink.databinding.ItemTutorSpesialisBinding
import model.AllTutor

class TutorSpesialisAdapter(
    private val tutorList: List<AllTutor>,
    private val onItemClick: (AllTutor) -> Unit
) : RecyclerView.Adapter<TutorSpesialisAdapter.TutorViewHolder>() {

    inner class TutorViewHolder(val binding: ItemTutorSpesialisBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tutor: AllTutor) {
            binding.txtNamaTutor.text = tutor.nama
            binding.txtPelajaranTutor.text = tutor.pelajaran
            binding.txtLokasiTutor.text = tutor.lokasi
            binding.txtRatingTutor.text = tutor.rating
            binding.txtBiaya.text = tutor.biaya
            binding.txtJarak.text = "${tutor.jarak} km"

            // Load gambar dari URL menggunakan Glide
            Glide.with(binding.root.context)
                .load(tutor.imageUrl)
                .into(binding.imgTutor)

            // Event klik
            binding.btnLihatProfil.setOnClickListener {
                onItemClick(tutor)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TutorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTutorSpesialisBinding.inflate(inflater, parent, false)
        return TutorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TutorViewHolder, position: Int) {
        holder.bind(tutorList[position])
    }

    override fun getItemCount(): Int = tutorList.size
}
