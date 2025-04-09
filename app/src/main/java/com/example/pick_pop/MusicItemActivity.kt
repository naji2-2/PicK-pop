package com.example.pick_pop

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MusicItemActivity: AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        auth = Firebase.auth
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_item)

        var likeBtn = findViewById<Button>(R.id.likeBtn)
        var likeBtnClick = false
        var like = 0

        likeBtn.setOnClickListener {
            if(likeBtnClick){
                likeBtn.setText("♡")
                like--
                likeBtnClick = false
            }
            else{
                likeBtn.setText("♥")
                like++
                likeBtnClick = true
            }
        }

    }
}