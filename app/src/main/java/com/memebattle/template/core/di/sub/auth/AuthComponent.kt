package com.memebattle.template.core.di.sub.auth

import com.memebattle.template.core.di.core.scope.FlowFragmentScope
import com.memebattle.template.core.di.sub.auth.module.AuthApiModule
import com.memebattle.template.core.di.sub.auth.module.AuthSettingsModule
import com.memebattle.template.features.auth.fragment.sign_in.SignInViewModel
import com.memebattle.template.features.auth.fragment.sign_up.SignUpViewModel
import dagger.Subcomponent

@Subcomponent(modules = [AuthApiModule::class, AuthSettingsModule::class])
@FlowFragmentScope
interface AuthComponent {
    fun inject(signInViewModel: SignInViewModel)
    fun inject(signUpViewModel: SignUpViewModel)

    @Subcomponent.Builder
    interface Builder {
        fun apiModule(authApiModule: AuthApiModule): Builder
        fun settingsModule(authSettingsModule: AuthSettingsModule): Builder
        fun buid(): AuthComponent
    }
}