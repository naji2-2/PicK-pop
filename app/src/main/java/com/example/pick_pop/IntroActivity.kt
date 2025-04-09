package com.example.pick_pop

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.ktx.Firebase

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        var joinBtn = findViewById<Button>(R.id.joinBtn)
        var loginBtn = findViewById<Button>(R.id.loginBtn)
        var nosignBtn = findViewById<TextView>(R.id.nosignBtn)

        // 회원 가입 버튼을 누른 경우
        joinBtn.setOnClickListener {
            var intent = Intent(this, JoinActivity::class.java)
            startActivity(intent)
        }

        // 로그인 버튼을 누른 경우
        loginBtn.setOnClickListener {
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        nosignBtn.setOnClickListener {
            var intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

    }
}