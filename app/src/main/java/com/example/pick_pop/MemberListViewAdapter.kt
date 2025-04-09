package com.example.pick_pop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.firebase.storage.FirebaseStorage

class MemberListViewAdapter(var items: MutableList<MemberDetailsItem>): BaseAdapter() {

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): MemberDetailsItem = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View? {

        var convertView = view
        if(convertView == null) convertView = LayoutInflater.from(parent?.context).inflate(R.layout.activity_member_derails_item, parent, false)

        var memberName = convertView?.findViewById<TextView>(R.id.member_name)
        var memberImage = convertView?.findViewById<ImageView>(R.id.member_image)
        var memberGname = convertView?.findViewById<TextView>(R.id.member_Gname)
        var memberBirth = convertView?.findViewById<TextView>(R.id.member_birth)
        var memberPoint = convertView?.findViewById<TextView>(R.id.member_point)

        var item: MemberDetailsItem = items[position]
        memberName?.text = item.name
        memberGname?.text = item.gname
        memberBirth?.text = item.birth
        memberPoint?.text = item.point

        // Firebase Storage 경로를 사용
        val imagePath = item.image.toString()

        // Glide로 이미지 로드
        if (imagePath.isNotEmpty()) {
            val storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(imagePath)
            storageReference.downloadUrl.addOnSuccessListener { uri ->
                Glide.with(parent?.context!!)
                    .load(uri)
                    .error(R.drawable.member_image)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .centerCrop()
                    .into(memberImage!!)
            }.addOnFailureListener { exception ->
                Glide.with(parent?.context!!)
                    .load(R.drawable.member_image)
                    .into(memberImage!!)
                exception.printStackTrace()
            }
        } else {
            Glide.with(parent?.context!!)
                .load(R.drawable.member_image)
                .into(memberImage!!)
        }

        return convertView
    }

}