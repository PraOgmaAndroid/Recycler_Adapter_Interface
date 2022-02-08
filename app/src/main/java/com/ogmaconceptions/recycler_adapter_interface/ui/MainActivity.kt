package com.ogmaconceptions.recycler_adapter_interface.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.ogmaconceptions.recycler_adapter_interface.adapter.ListingAdapter
import com.ogmaconceptions.recycler_adapter_interface.databinding.ActivityMainBinding
import com.ogmaconceptions.recycler_adapter_interface.interfaces.CountHandle

class MainActivity : AppCompatActivity(),CountHandle {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var listObj: ListingAdapter
    override fun onCreate(savedInstanceState: Bundle?){
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(mainBinding.root)

        listObj = ListingAdapter(this)
        mainBinding.recyclerList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.listObj
        }

        mainBinding.flBtn.setOnClickListener {
            listObj.addItem(getRandomString(10))
        }

    }

    private fun getRandomString(length: Int) : String {
        val charset = ('A'..'Z')

        return List(length) { charset.random() }
            .joinToString("")
    }

    override fun displayTotalCount(value: Int) {
        Log.e("PRINT","$value")
        mainBinding.tvCountNumber.text = "$value"
    }
}