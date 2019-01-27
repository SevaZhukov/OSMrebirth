package com.memebattle.template.core.di.sub.main

import com.memebattle.template.core.di.core.scope.FlowFragmentScope
import com.memebattle.template.core.di.sub.main.module.SomeApiModule
import com.memebattle.template.features.main.notes.NotesFragment
import com.memebattle.template.features.main.notes.paging.NotePositionalDataSource
import dagger.Subcomponent

@Subcomponent(modules = [SomeApiModule::class])
@FlowFragmentScope
interface MainComponent {
    fun inject(notePositionalDataSource: NotePositionalDataSource)
    fun inject(notePositionalDataSource: NotesFragment)

    @Subcomponent.Builder
    interface Builder {
        fun buid(): MainComponent
    }
}