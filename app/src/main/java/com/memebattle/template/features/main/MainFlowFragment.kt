package com.memebattle.template.features.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.memebattle.msnl.IOnBackPressed
import com.memebattle.msnl.MSNavigation
import com.memebattle.msnl.msFragmentManager
import com.memebattle.template.App
import com.memebattle.template.R
import kotlinx.android.synthetic.main.flow_fragment_main.view.*

class MainFlowFragment : Fragment(), IOnBackPressed {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.daggerComponentHelper.plusMainComponent()
    }

    override fun onDestroy() {
        super.onDestroy()
        App.instance.daggerComponentHelper.removeMainComponent()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.flow_fragment_main, container, false)
        msFragmentManager.localContainerId = R.id.main_container_id
        val fragments = arrayListOf<Fragment>()
        MSNavigation.setupNavigation(msFragmentManager, v.bottomNavigationView, fragments)
        return v
    }

    override fun onBackPressed(): Boolean {
        MSNavigation.onBackPressed()
        return true
    }
}
