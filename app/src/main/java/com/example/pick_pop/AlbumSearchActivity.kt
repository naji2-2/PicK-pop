package com.example.pick_pop

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.Gallery
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.FirebaseFirestore

class AlbumSearchActivity : AppCompatActivity() {

    private lateinit var gridView: GridView
    private val albumList = mutableListOf<AlbumDetailsItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_search)

        gridView = findViewById(R.id.album_gridview)

        // Firebase Firestore에서 데이터 가져오기
        val db = FirebaseFirestore.getInstance()
        db.collection("albums")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val album = AlbumDetailsItem(
                        a_image = document.getString("imagePath") ?: "",
                        a_name = document.getString("name") ?: "",
                        a_group = document.getString("group") ?: "",
                        a_ymd = document.getString("ymd") ?: "",
                        a_sub = document.getString("sub") ?: "",
                        a_genre = document.getString("genre") ?: "",
                        a_song = (document.get("song") as? ArrayList<String>)?.toTypedArray() // 배열 데이터 처리
                    )
                    albumList.add(album)

                    val adapter = AlbumDetailsAdapter(this, albumList)
                    gridView.adapter = adapter
                }
                // Adapter 연결
                val adapter = AlbumDetailsAdapter(this, albumList)
                gridView.adapter = adapter
            }

        // GridView 클릭 이벤트
        gridView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val selectedAlbum = albumList[position]
            val intent = Intent(this, AlbumDetailsActivity::class.java)
            intent.putExtra("album_image", selectedAlbum.a_image)
            intent.putExtra("album_name", selectedAlbum.a_name)
            intent.putExtra("album_group", selectedAlbum.a_group)
            intent.putExtra("album_ymd", selectedAlbum.a_ymd)
            intent.putExtra("album_genre", selectedAlbum.a_genre)
            intent.putExtra("album_sub", selectedAlbum.a_sub)
            intent.putExtra("album_song", selectedAlbum.a_song)

            startActivity(intent)
        }
    }
}