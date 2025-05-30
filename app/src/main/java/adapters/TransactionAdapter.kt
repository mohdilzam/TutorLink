package adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tutorlink.R
import model.Transaction
import java.text.SimpleDateFormat
import java.util.*

class TransactionAdapter(private val transactions: List<Transaction>) :
    RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    inner class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTanggal: TextView = itemView.findViewById(R.id.tvTanggal)
        val tvMetode: TextView = itemView.findViewById(R.id.tvMetode)
        val tvTotal: TextView = itemView.findViewById(R.id.tvTotal)
        val tvStatus: TextView = itemView.findViewById(R.id.tvStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_transaction, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = transactions[position]

        val formatter = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault())
        val dateFormatted = formatter.format(Date(transaction.timestamp))

        holder.tvTanggal.text = "Tanggal: $dateFormatted"
        holder.tvMetode.text = "Metode: ${transaction.method}"
        holder.tvTotal.text = "Total: Rp${transaction.amount}"
        holder.tvStatus.text = "Status: ${transaction.status}"
    }

    override fun getItemCount(): Int = transactions.size
}
