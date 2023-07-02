package com.example.myapplicationtest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.myapplicationtest.R
//Class MyAdapter
class MyAdapter(private val context: Context, private val arrayList: java.util.ArrayList<String>) : BaseAdapter() {
    private lateinit var epsID: TextView
    private lateinit var epsUrl: TextView
    override fun getCount(): Int {
        return arrayList.size
    }
    override fun getItem(position: Int): Any {
        return position
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView = convertView
        convertView = LayoutInflater.from(context).inflate(R.layout.exprience_layout, parent, false)
        epsID = convertView.findViewById(R.id.tvEpsId)
        epsUrl = convertView.findViewById(R.id.tvEpsUrl)

        epsID.text = arrayList[position]
        epsID.text = position.toString()
        return convertView
    }
}