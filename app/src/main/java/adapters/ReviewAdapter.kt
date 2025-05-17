package adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tutorlink.R
import model.Review

class ReviewAdapter(private val reviewList: List<Review>) :
    RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgUser: ImageView = itemView.findViewById(R.id.imgUser)
        val txtNamaUser: TextView = itemView.findViewById(R.id.txtNamaUser)
        val txtIsiReview: TextView = itemView.findViewById(R.id.txtIsiReview)
        val txtWaktuReview: TextView = itemView.findViewById(R.id.txtWaktuReview)
        val ratingBar: RatingBar = itemView.findViewById(R.id.ratingBar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_review, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = reviewList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val review = reviewList[position]
        holder.imgUser.setImageResource(review.fotoResId)
        holder.txtNamaUser.text = review.namaUser
        holder.txtIsiReview.text = review.isiReview
        holder.txtWaktuReview.text = review.waktuReview
        holder.ratingBar.rating = review.rating
    }
}