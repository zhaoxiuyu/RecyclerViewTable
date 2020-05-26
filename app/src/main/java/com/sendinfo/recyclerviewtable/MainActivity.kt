package com.sendinfo.recyclerviewtable

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sendinfo.recyclerviewtable.no_nested.NoNestedActivity
import com.sendinfo.recyclerviewtable.two.TwoActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        but1.setOnClickListener {
            startActivity(Intent(this, NoNestedActivity::class.java))
        }

        but2.setOnClickListener {
            startActivity(Intent(this, TwoActivity::class.java))
        }

    }

}
