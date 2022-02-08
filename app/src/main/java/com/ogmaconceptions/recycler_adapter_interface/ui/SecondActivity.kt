package com.ogmaconceptions.recycler_adapter_interface.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ogmaconceptions.recycler_adapter_interface.adapter.ListingAdapter
import com.ogmaconceptions.recycler_adapter_interface.databinding.ActivitySecondBinding
import com.ogmaconceptions.recycler_adapter_interface.interfaces.CountHandle

class SecondActivity : AppCompatActivity(), CountHandle {
    private lateinit var secondBinding: ActivitySecondBinding
    private lateinit var listObj: ListingAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        secondBinding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(secondBinding.root)

        listObj = ListingAdapter(this)
        secondBinding.recyclerList.apply {
            layoutManager = LinearLayoutManager(this@SecondActivity)
            adapter = this@SecondActivity.listObj
        }

        secondBinding.flBtn.setOnClickListener {
            listObj.addItem(getRandomString(20))
        }

        secondBinding.topAppBar.setNavigationOnClickListener {
            finish()
        }

    }

    private fun getRandomString(length: Int): String {
        val charset = ('A'..'Z') + ('a'..'z')

        return List(length) { charset.random() }
            .joinToString("")
    }

    override fun displayTotalCount(value: Int) {
        Log.e("PRINT", "$value")
        secondBinding.tvCountNumber.text = "$value"
    }


}