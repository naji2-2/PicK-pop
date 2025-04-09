package com.example.pick_pop

import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class MemberDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_details)
        FirebaseApp.initializeApp(this)

        val db = FirebaseFirestore.getInstance()

        var memberlistView = findViewById<ListView>(R.id.member_list)
        var memberlist = mutableListOf<MemberDetailsItem>()

        db.collection("member")
            .whereGreaterThan("birth", "") // 생일 필드가 비어 있지 않은 항목만 필터링
            .orderBy("birth", Query.Direction.ASCENDING) // 생일 기준 오름차순 정렬
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot) {
                    val name = document.getString("name") ?: "이름"
                    val gname = document.getString("gname") ?: "본명"
                    val birth = document.getString("birth") ?: "출생"
                    val point = document.getString("point") ?: "특징"
                    val imagePath = document.getString("imagePath") ?: ""

                    val member = MemberDetailsItem(imagePath, name, gname, birth, point)
                    memberlist.add(member)
                }

                // 어댑터 설정
                val adapter = MemberListViewAdapter(memberlist)
                memberlistView.adapter = adapter
            }
            val adapter = MemberListViewAdapter(memberlist)
            memberlistView.adapter = adapter
    }
}