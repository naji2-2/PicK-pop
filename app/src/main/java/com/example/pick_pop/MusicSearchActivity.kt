package com.example.pick_pop

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MusicSearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_search)

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
}