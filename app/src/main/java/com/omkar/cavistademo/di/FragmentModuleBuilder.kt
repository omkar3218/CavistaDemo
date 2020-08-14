package com.omkar.cavistademo.di

import com.omkar.cavistademo.ui.view.SearchImageListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentModuleBuilder {

    @ContributesAndroidInjector
    abstract fun contributeImageListFragment(): SearchImageListFragment

}
