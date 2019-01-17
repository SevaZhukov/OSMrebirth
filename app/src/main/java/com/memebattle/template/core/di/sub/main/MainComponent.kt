package com.memebattle.template.core.di.sub.main

import com.memebattle.template.core.di.core.scope.FlowFragmentScope
import com.memebattle.template.core.di.sub.main.module.SomeApiModule
import dagger.Subcomponent

@Subcomponent(modules = [SomeApiModule::class])
@FlowFragmentScope
interface MainComponent {


    @Subcomponent.Builder
    interface Builder {
        fun buid(): MainComponent
    }
}