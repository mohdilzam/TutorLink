import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tutorlink.PembayaranActivity
import com.example.tutorlink.R
import model.Booking

class BookingAdapter(private val bookings: List<Booking>) :
    RecyclerView.Adapter<BookingAdapter.BookingViewHolder>() {

    inner class BookingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tutorName: TextView = itemView.findViewById(R.id.tvTutorName)
        val subject: TextView = itemView.findViewById(R.id.tvSubject)
        val dateTime: TextView = itemView.findViewById(R.id.tvDateTime)
        val duration: TextView = itemView.findViewById(R.id.tvDuration)
        val location: TextView = itemView.findViewById(R.id.tvLocation)
        val btnBayar: Button = itemView.findViewById(R.id.btnBayar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_booking, parent, false)
        return BookingViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
        val booking = bookings[position]
        holder.tutorName.text = booking.tutorName ?: "-"
        holder.subject.text = booking.subject ?: "-"
        holder.dateTime.text = "${booking.date ?: "-"} - ${booking.time ?: "-"}"
        holder.duration.text = "Durasi: ${booking.duration ?: "-"}"
        holder.location.text = "Lokasi: ${booking.location ?: "-"}"

        // Tampilkan tombol bayar hanya jika status == "booked"
        if (booking.status == "booked") {
            holder.btnBayar.visibility = View.VISIBLE
            holder.btnBayar.setOnClickListener {
                val context = holder.itemView.context
                val intent = Intent(context, PembayaranActivity::class.java).apply {
                    putExtra("bookingId", booking.bookingId)
                    putExtra("tanggal", booking.date)
                    putExtra("jam", booking.time)
                    putExtra("lokasi", booking.location)
                }
                context.startActivity(intent)
            }
        } else {
            holder.btnBayar.visibility = View.GONE
        }
    }


    override fun getItemCount(): Int = bookings.size
}
