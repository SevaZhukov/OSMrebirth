package com.memebattle.template.features.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.memebattle.template.App
import com.memebattle.template.R
import com.memebattle.template.features.main.notes.NotesFragment

class MainFlowFragment : Fragment() {

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
        fragmentManager!!.beginTransaction()
                .add(R.id.mainContainer, NotesFragment())
                .commit()
        return v
    }
}
