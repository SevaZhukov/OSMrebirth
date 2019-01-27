package com.memebattle.template.features

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.memebattle.template.R
import com.memebattle.template.features.main.MainFlowFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
                .add(R.id.activityContainer, MainFlowFragment())
                .commit()
    }
}
