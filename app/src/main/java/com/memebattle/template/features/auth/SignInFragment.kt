package com.memebattle.template.features.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.memebattle.template.R
import kotlinx.android.synthetic.main.fragment_sign_in.*

class SignInFragment : Fragment() {

    lateinit var viewModel: SignInViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_sign_in, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SignInViewModel::class.java)
        viewModel.user.observe(this, Observer {

        })
        viewModel.error.observe(this, Observer {

        })
        gotoSignUpButton.setOnClickListener {

        }
        signInButton.setOnClickListener {
            //viewModel.signIn("")
            val navController = Navigation.findNavController(activity!!, R.id.nav_host_global)
            navController.navigate(R.id.action_signInFragment_to_mainFlowFragment)
        }
    }
}
