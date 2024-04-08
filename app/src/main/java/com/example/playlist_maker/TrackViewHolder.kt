package com.example.playlist_maker

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class TrackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val trackNameView: TextView
    private val artistNameView: TextView
    private val trackTimeView: TextView
    private val trackCoverImage: ImageView

    init {
        trackNameView = itemView.findViewById(R.id.tv_trackName)
        artistNameView = itemView.findViewById(R.id.tv_artistName)
        trackTimeView = itemView.findViewById(R.id.tv_trackTime)
        trackCoverImage = itemView.findViewById(R.id.tv_cover_image)
    }

    fun bind(model: Track) {
        val cornerRadiusInPx = dpToPx(2f, itemView.context)

        trackNameView.text = model.trackName
        artistNameView.text = model.artistName
        trackTimeView.text = model.trackTime

        Glide.with(itemView)
            .load(model.artworkUrl100)
            .placeholder(R.drawable.ic_placeholder_image)
            .centerCrop()
            .transform(RoundedCorners(cornerRadiusInPx))
            .into(trackCoverImage)
    }

    private fun dpToPx(dp: Float, context: Context): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            context.resources.displayMetrics
        ).toInt()
    }
}