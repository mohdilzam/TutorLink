package adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.tutorlink.R
import model.AllTutor

//class AllTutorAdapter(private val list: List<AllTutor>) :
//    RecyclerView.Adapter<AllTutorAdapter.TutorViewHolder>() {
//
//    inner class TutorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val imgTutor: ImageView = view.findViewById(R.id.imgTutor)
//        val txtNama: TextView = view.findViewById(R.id.txtNamaTutor)
//        val txtMapel: TextView = view.findViewById(R.id.txtMapelTutor)
//        val txtLokasi: TextView = view.findViewById(R.id.txtLokasiTutor)
//        val txtJarak: TextView = view.findViewById(R.id.txtJarak)
//        val txtRating: TextView = view.findViewById(R.id.txtRating)
//        val btnBook: Button = view.findViewById(R.id.btnBook)
//        val iconChat: ImageView = view.findViewById(R.id.iconChat)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TutorViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.item_all_tutor, parent, false)
//        return TutorViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: TutorViewHolder, position: Int) {
//        val tutor = list[position]
//        holder.imgTutor.setImageResource(tutor.imageResId)
//        holder.txtNama.text = tutor.nama
//        holder.txtMapel.text = tutor.pelajaran
//        holder.txtLokasi.text = tutor.lokasi
//        holder.txtJarak.text = "${tutor.jarak} km"
//        holder.txtRating.text = "⭐ ${tutor.rating}"
//
//        // Listener dummy
//        holder.btnBook.setOnClickListener {
//            Toast.makeText(holder.itemView.context, "Book ${tutor.nama}", Toast.LENGTH_SHORT).show()
//        }
//
//        holder.iconChat.setOnClickListener {
//            Toast.makeText(holder.itemView.context, "Chat dengan ${tutor.nama}", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    override fun getItemCount(): Int = list.size
//}

class AllTutorAdapter(
    private val list: List<AllTutor>,
    private val onItemClick: (AllTutor) -> Unit,
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
            imgTutor.setImageResource(tutor.imageResId)
            txtNama.text = tutor.nama
            txtMapel.text = tutor.pelajaran
            txtLokasi.text = tutor.lokasi
            txtJarak.text = "${tutor.jarak} km"
            txtRating.text = "⭐ ${tutor.rating}"

            itemView.setOnClickListener {
                onItemClick(tutor)
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
