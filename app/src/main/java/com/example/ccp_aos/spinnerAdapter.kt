package com.example.ccp_aos

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.ccp_aos.Network.categoryList
import com.example.ccp_aos.databinding.ActivityMainBinding
import com.example.ccp_aos.databinding.SpinnerLayoutBinding

class spinnerAdapter (private val context: Context, private val cateList : ArrayList<categoryList>) :
    BaseAdapter() {

    private  lateinit var binding: SpinnerLayoutBinding

    override fun getCount(): Int {
        return cateList.size
    }

    override fun getItem(p0: Int): Any {
        return cateList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    @SuppressLint("ViewHolder")
    override fun getView(positon: Int, p1: View?, p2: ViewGroup?): View {

        val rootView: View = LayoutInflater.from(context)
            .inflate(R.layout.spinner_layout, p2, false)

        val category_Name: TextView = rootView.findViewById(R.id.category_Name)
        category_Name.text = cateList.toString()

        return rootView

    }


}
