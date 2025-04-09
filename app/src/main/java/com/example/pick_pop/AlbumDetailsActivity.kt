package com.example.pick_pop

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage

class AlbumDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_details)

        // View 참조
        val albumImageView = findViewById<ImageView>(R.id.album_image)
        val albumNameTextView = findViewById<TextView>(R.id.album_name)
        val groupNameTextView = findViewById<TextView>(R.id.group_name)
        val releaseDateTextView = findViewById<TextView>(R.id.ymd)
        val genreTextView = findViewById<TextView>(R.id.genre)
        val subTextView = findViewById<TextView>(R.id.sub)
        val songListView = findViewById<ListView>(R.id.song)

        // Intent 데이터 수신
        val albumName = intent.getStringExtra("album_name")
        val groupName = intent.getStringExtra("album_group")
        val releaseDate = intent.getStringExtra("album_ymd")
        val genre = intent.getStringExtra("album_genre")
        val imagePath = intent.getStringExtra("album_image")
        val sub = intent.getStringExtra("album_sub")
        val songs = intent.getStringArrayExtra("album_song")

        // 데이터 설정
        albumNameTextView.text = albumName
        groupNameTextView.text = groupName
        releaseDateTextView.text = releaseDate
        genreTextView.text = genre
        subTextView.text = sub

        if (songs != null) {
            // ArrayAdapter 생성 및 ListView에 연결
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, songs)
            songListView.adapter = adapter
        }

        // 이미지 로드
        if (!imagePath.isNullOrEmpty()) {
            val storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(imagePath)
            storageReference.downloadUrl.addOnSuccessListener { uri ->
                Glide.with(this)
                    .load(uri)
                    .error(R.drawable.album_image) // 기본 이미지
                    .centerCrop()
                    .into(albumImageView)
            }
        } else {
            Glide.with(this)
                .load(R.drawable.album_image) // 기본 이미지
                .into(albumImageView)
        }
    }
}