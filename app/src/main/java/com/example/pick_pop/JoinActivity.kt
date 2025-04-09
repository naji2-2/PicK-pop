package com.example.pick_pop

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class JoinActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        auth = Firebase.auth
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        var email = findViewById<EditText>(R.id.emailArea)
        var password = findViewById<EditText>(R.id.passwordArea)
        var passwordck = findViewById<EditText>(R.id.passworchekdArea)

        var joinBtn = findViewById<Button>(R.id.joinBtn)

        joinBtn.setOnClickListener {
            auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful && (password.text.toString() == passwordck.text.toString())) {
                        Toast.makeText(this, "회원가입 성공했습니다", Toast.LENGTH_SHORT).show()
                        var intent = Intent(this, IntroActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "회원가입 실패했습니다", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

}