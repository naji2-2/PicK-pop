package com.example.pick_pop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class MusicListViewAdapter(var items: MutableList<MusicItem>): BaseAdapter() {

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): MusicItem = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View? {

        var convertView = view
        if (convertView == null) convertView = LayoutInflater.from(parent?.context).inflate(R.layout.activity_music_item, parent, false)

        var albumImage = convertView?.findViewById<ImageView>(R.id.album_image)
        var musicTitle = convertView?.findViewById<TextView>(R.id.music_title)
        var groupName = convertView?.findViewById<TextView>(R.id.group_name)

        var item: MusicItem = items[position]
        albumImage?.setImageDrawable(item.image)
        musicTitle?.text = item.title
        groupName?.text = item.group

        return convertView
    }
}
