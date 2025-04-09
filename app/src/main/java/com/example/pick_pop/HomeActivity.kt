package com.example.pick_pop

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Gallery
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class HomeActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    private val imageList = mutableListOf<String>() // 이미지 URL을 저장할 리스트
    private lateinit var galleryAdapter: MemberGalleryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        FirebaseApp.initializeApp(this)

        val memberGallery = findViewById<Gallery>(R.id.gallery1)
        galleryAdapter = MemberGalleryAdapter(this, imageList)
        memberGallery.adapter = galleryAdapter

        // Firestore에서 이미지 데이터 가져오기
        db.collection("member")
            .whereGreaterThan("birth", "") // 생일 필드가 비어있지 않은 항목만 필터링
            .orderBy("birth", Query.Direction.ASCENDING) // 생일 기준 오름차순
            .get()
            .addOnSuccessListener { querySnapshot ->
                imageList.clear() // 기존 데이터 초기화
                for (document in querySnapshot) {
                    val imagePath = document.getString("imagePath") ?: ""
                    if (imagePath.isNotEmpty()) {
                        imageList.add(imagePath) // 이미지 URL 추가
                    }
                }
                galleryAdapter.notifyDataSetChanged() // 어댑터 갱신
            }
            .addOnFailureListener { e ->
                e.printStackTrace()
            }

        var memberBtn = findViewById<TextView>(R.id.member)
        var albumBtn = findViewById<TextView>(R.id.album)
        var musicBtn = findViewById<TextView>(R.id.music)

        memberBtn.setOnClickListener {
            var intent = Intent(this, MemberDetailsActivity::class.java)
            startActivity(intent)
        }

        albumBtn.setOnClickListener {
            var intent = Intent(this, AlbumSearchActivity::class.java)
            startActivity(intent)
        }

        musicBtn.setOnClickListener {
            var intent = Intent(this, MusicSearchActivity::class.java)
            startActivity(intent)
        }

        var albumgallery = findViewById<Gallery>(R.id.gallery2)
        var gal2adapter = AlbumGalleryAdapter(this)
        albumgallery.adapter = gal2adapter

        var musiclistView = findViewById<ListView>(R.id.music_list)
        var musiclist = mutableListOf<MusicItem>()

        musiclist.add(MusicItem(ContextCompat.getDrawable(this, R.drawable.album_iloveyou)!!, "오늘만 I LOVE YOU", "BOYNEXTDOOR"))
        musiclist.add(MusicItem(ContextCompat.getDrawable(this, R.drawable.album_1999)!!, "Nice Guy", "BOYNEXTDOOR"))
        musiclist.add(MusicItem(ContextCompat.getDrawable(this, R.drawable.album_woh)!!, "Earth, Wind & Fire", "BOYNEXTDOOR"))
        musiclist.add(MusicItem(ContextCompat.getDrawable(this, R.drawable.album_1999)!!, "부모님 관람불가", "BOYNEXTDOOR"))
        musiclist.add(MusicItem(ContextCompat.getDrawable(this, R.drawable.album_why)!!, "뭣같아", "BOYNEXTDOOR"))
        musiclist.add(MusicItem(ContextCompat.getDrawable(this, R.drawable.album_1999)!!, "돌멩이", "BOYNEXTDOOR"))
        musiclist.add(MusicItem(ContextCompat.getDrawable(this, R.drawable.album_1999)!!, "스물", "BOYNEXTDOOR"))
        musiclist.add(MusicItem(ContextCompat.getDrawable(this, R.drawable.album_who)!!, "One and Only", "BOYNEXTDOOR"))
        musiclist.add(MusicItem(ContextCompat.getDrawable(this, R.drawable.album_who)!!, "Serenade", "BOYNEXTDOOR"))
        musiclist.add(MusicItem(ContextCompat.getDrawable(this, R.drawable.album_woh)!!, "So let's go see the starts", "BOYNEXTDOOR"))

        var listviewadapter = MusicListViewAdapter(musiclist)
        musiclistView.adapter = listviewadapter

    }
    // Gallery 어댑터 클래스
    inner class MemberGalleryAdapter(
        private val context: Context,
        private val images: List<String>
    ) : BaseAdapter() {
        override fun getCount(): Int = images.size

        override fun getItem(position: Int): Any = images[position]

        override fun getItemId(position: Int): Long = position.toLong()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val imageView = ImageView(context)
            imageView.layoutParams = Gallery.LayoutParams(500, 500)
            imageView.scaleType = ImageView.ScaleType.FIT_START
            imageView.setPadding(5, 10, 5, 10)

            print(images[position])

            // Glide를 사용해 이미지 로드
            Glide.with(context)
                .load(images[position])
                .error(R.drawable.member_image) // 기본 이미지 설정
                .into(imageView)

            return imageView
        }
    }
//    inner class MemberGalleryAdapter(var context: Context) : BaseAdapter() {
//
//        var db = FirebaseFirestore.getInstance()
//
//        var memberImage = arrayOf(
//            R.drawable.member_parksungho, R.drawable.member_leesanghyuk, R.drawable.member_myeongjaehyun,
//            R.drawable.member_handongmin, R.drawable.member_kimdonghyun, R.drawable.member_kimwoonhak
//        )
//
//        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//            var imageView = ImageView(context)
//            imageView.layoutParams = Gallery.LayoutParams(500, 500)
//            imageView.scaleType = ImageView.ScaleType.FIT_START
//            imageView.setPadding(5, 5, 5, 5)
//            imageView.setImageResource(memberImage[position])
//
//            return imageView
//        }
//
//        override fun getCount(): Int {
//            return memberImage.size
//        }
//
//        override fun getItem(position: Int): Any {
//            return 0
//        }
//
//        override fun getItemId(position: Int): Long {
//            return 0
//        }
//    }
    inner class AlbumGalleryAdapter(var context: Context) : BaseAdapter() {

        var albumImage = arrayOf(R.drawable.album_iloveyou, R.drawable.album_1999, R.drawable.album_and, R.drawable.album_ost,
                                R.drawable.album_woh, R.drawable.album_fadeaway, R.drawable.album_why, R.drawable.album_who)

        var albumName = arrayOf("오늘만 I LOVE YOU", "19.99","AND,", "낮과 밤이 다른 그녀 OST Part.3",
                                "HOW?", "Fadeaway (가비지타임 X BOYNEXTDOOR)", "WHY..", "WHO!")

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var imageView = ImageView(context)
            imageView.layoutParams = Gallery.LayoutParams(500, 500)
            imageView.scaleType = ImageView.ScaleType.FIT_START
            imageView.setPadding(5, 10, 5, 10)
            imageView.setImageResource(albumImage[position])

            return imageView
        }

        override fun getCount(): Int {
            return albumImage.size
        }

        override fun getItem(position: Int): Any {
            return 0
        }

        override fun getItemId(position: Int): Long {
            return 0
        }
    }

}