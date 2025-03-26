package com.example.giris_sayfasi
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.giris_sayfasi.ViewPagerItem

class VPAdapter(
    private val viewPagerItems: List<ViewPagerItem>,
    private val clickListener: (ViewPagerItem) -> Unit
) : RecyclerView.Adapter<VPAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val headingText: TextView = itemView.findViewById(R.id.Heading)
        val descriptionText: TextView = itemView.findViewById(R.id.Desc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewpager_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = viewPagerItems[position]
        holder.imageView.setImageResource(item.imageID)
        holder.headingText.text = item.heading
        holder.descriptionText.text = item.description

        holder.itemView.setOnClickListener {
            try {
                clickListener(item)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    override fun getItemCount(): Int = viewPagerItems.size
}

