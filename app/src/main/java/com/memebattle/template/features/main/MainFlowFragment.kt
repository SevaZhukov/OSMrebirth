package com.memebattle.template.features.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.memebattle.template.R
import kotlinx.android.synthetic.main.flow_fragment_main.*

class MainFlowFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.flow_fragment_main, container, false)
        val navController = Navigation.findNavController(activity!!, R.id.nav_host_main)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
        return v
    }
}
