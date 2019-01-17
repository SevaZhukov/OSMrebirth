package com.memebattle.template.features.auth.fragment.sign_up


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.memebattle.template.R
import kotlinx.android.synthetic.main.fragment_sign_up.view.*

class SignUpFragment : Fragment() {

    lateinit var viewModel: SignUpViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_sign_up, container, false)
        viewModel = ViewModelProviders.of(this).get(SignUpViewModel::class.java)

        v.backButton.setOnClickListener {

        }
        v.mainButton.setOnClickListener {

        }
        return v
    }
}
