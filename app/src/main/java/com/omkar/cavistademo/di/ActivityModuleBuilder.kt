package com.omkar.cavistademo.di

import com.omkar.cavistademo.ui.view.ImageDetailsActivity
import com.omkar.cavistademo.ui.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Suppress("unused")
@Module
abstract class ActivityModuleBuilder {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeImageDetailsActivity(): ImageDetailsActivity

}
