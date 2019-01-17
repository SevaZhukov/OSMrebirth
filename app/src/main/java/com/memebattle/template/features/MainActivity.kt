package com.memebattle.template.features

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.memebattle.msnl.IOnBackPressed
import com.memebattle.msnl.MSFragmentManager
import com.memebattle.template.R
import com.memebattle.template.features.auth.AuthFlowFragment

class MainActivity : AppCompatActivity() {

    private lateinit var msFragmentManager: MSFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        msFragmentManager = MSFragmentManager(supportFragmentManager)
        msFragmentManager.globalContainerId = R.id.activity_container_id
        msFragmentManager.addGlobal(AuthFlowFragment())
    }

    override fun onBackPressed() {
        val fragment = this.supportFragmentManager.findFragmentById(R.id.activity_container_id)
        (fragment as? IOnBackPressed)?.onBackPressed()
    }
}
