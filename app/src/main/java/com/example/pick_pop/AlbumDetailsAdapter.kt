package com.example.pick_pop

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.firebase.storage.FirebaseStorage

class AlbumDetailsAdapter(
    private val context: Context,
    private val albumList: List<AlbumDetailsItem>
) : BaseAdapter() {

    override fun getCount(): Int {
        return albumList.size
    }

    override fun getItem(position: Int): Any {
        return albumList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ViewHolder

        if (convertView == null) {
            // 새로운 아이템 뷰를 생성
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.activity_album_details, null)

            // ViewHolder 패턴 사용
            holder = ViewHolder()
            holder.albumImageView = view.findViewById(R.id.album_image)
            holder.albumNameTextView = view.findViewById(R.id.album_name)
            holder.groupNameTextView = view.findViewById(R.id.group_name)
            holder.releaseDateTextView = view.findViewById(R.id.ymd)
            holder.genreTextView = view.findViewById(R.id.genre)

            view.tag = holder
        } else {
            // 재사용 가능한 뷰를 가져옴
            view = convertView
            holder = view.tag as ViewHolder
        }

        // 데이터를 뷰에 설정
        val albumItem = getItem(position) as AlbumDetailsItem
        holder.albumNameTextView.text = albumItem.a_name
        holder.groupNameTextView.text = albumItem.a_group
        holder.releaseDateTextView.text = albumItem.a_ymd
        holder.genreTextView.text = albumItem.a_genre

        // 이미지 설정
        val imagePath = albumItem.a_image
        if (imagePath.isNullOrEmpty()) {
            Glide.with(context)
                .load(R.drawable.album_image) // 기본 이미지
                .into(holder.albumImageView)
        } else {
            val storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(imagePath)
            storageReference.downloadUrl.addOnSuccessListener { uri ->
                Glide.with(context)
                    .load(uri)
                    .error(R.drawable.album_image)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .centerCrop()
                    .into(holder.albumImageView)
            }
        }

        return view
    }

    // 뷰 홀더 클래스
    private class ViewHolder {
        lateinit var albumImageView: ImageView
        lateinit var albumNameTextView: TextView
        lateinit var groupNameTextView: TextView
        lateinit var releaseDateTextView: TextView
        lateinit var genreTextView: TextView
    }
}