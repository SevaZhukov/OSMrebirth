package com.bignerdranch.android.osm.features.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.memebattle.goldextensions.snack
import com.memebattle.template.R
import kotlinx.android.synthetic.main.fragment_auth.*

class AuthFragment : Fragment() {

    lateinit var viewModel: AuthViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_auth, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(activity!!, R.id.nav_host_global)
        viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        if (viewModel.isUserAuth())
            navController.navigate(R.id.action_signInFragment_to_cloudFragment)
        viewModel.user.observe(this, Observer {
            //navController.navigate(R.id.action_signInFragment_to_cloudFragment)
            viewModel.upload()
        })
        viewModel.error.observe(this, Observer {
            snack(it)
        })
        signUpButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            if (email != "" && password != "")
                viewModel.signUp(email, password)
            else
                snack("Заполните все поля")
        }
        signInButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            if (email != "" && password != "")
                viewModel.signIn(email, password)
            else
                snack("Заполните все поля")
        }
    }
}
