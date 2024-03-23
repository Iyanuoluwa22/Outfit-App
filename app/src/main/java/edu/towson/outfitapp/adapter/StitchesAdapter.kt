package edu.towson.outfitapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.towson.outfitapp.R
import edu.towson.outfitapp.model.Stitch

class StitchesAdapter(private val stitches: List<Stitch>) : RecyclerView.Adapter<StitchesAdapter.StitchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StitchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_stitch, parent, false)
        return StitchViewHolder(view)
    }

    override fun onBindViewHolder(holder: StitchViewHolder, position: Int) {
        val stitch = stitches[position]
        holder.bind(stitch)
    }

    override fun getItemCount(): Int {
        return stitches.size
    }

    class StitchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val stitchPhotoImageView: ImageView = itemView.findViewById(R.id.stitchPhotoImageView)
        private val likesCountTextView: TextView = itemView.findViewById(R.id.likesCountTextView)
        private val commentsCountTextView: TextView = itemView.findViewById(R.id.commentsCountTextView)

        fun bind(stitch: Stitch) {
            // Load stitch photo using Glide or Picasso
            // Example: Glide.with(itemView.context).load(stitch.photoUrl).into(stitchPhotoImageView)

            likesCountTextView.text = "Likes: ${stitch.likesCount}"
            commentsCountTextView.text = "Comments: ${stitch.commentsCount}"
        }
    }
}
