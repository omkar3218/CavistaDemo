package com.omkar.cavistademo.di

import com.omkar.cavistademo.ui.view.ImageListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentModuleBuilder {

    @ContributesAndroidInjector
    abstract fun contributeImageListFragment(): ImageListFragment

}
