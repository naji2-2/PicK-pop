package com.example.pick_pop

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Gallery
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.firebase.storage.FirebaseStorage

class MemberGalleryAdapter(private val items: List<MemberDetailsItem>) : RecyclerView.Adapter<MemberGalleryAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val memberImage: ImageView = view.findViewById(R.id.member_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_home, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        val imagePath = item.image.toString()

        if (imagePath.startsWith("gs://")) {
            val storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(imagePath)
            storageReference.downloadUrl
                .addOnSuccessListener { uri ->
                    // URL 생성 성공
                    Glide.with(holder.memberImage.context)
                        .load(uri)
                        .error(R.drawable.member_image)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .centerCrop()
                        .into(holder.memberImage)
                }
                .addOnFailureListener { exception ->
                    // URL 생성 실패
                    exception.printStackTrace()
                    Log.e("FirebaseError", "Failed to get download URL: ${exception.message}")
                    Glide.with(holder.memberImage.context)
                        .load(R.drawable.member_image)
                        .into(holder.memberImage)
                }
        } else {
            Glide.with(holder.memberImage.context)
                .load(imagePath)
                .error(R.drawable.member_image)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .centerCrop()
                .into(holder.memberImage)
        }
    }


    override fun getItemCount(): Int = items.size
}
