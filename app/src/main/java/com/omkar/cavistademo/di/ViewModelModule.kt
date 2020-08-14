package com.omkar.cavistademo.di

import androidx.lifecycle.ViewModel
import com.omkar.cavistademo.ui.viewmodel.ImageListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ImageListViewModel::class)
    abstract fun bindArticleListViewModel(viewModel: ImageListViewModel): ViewModel

}
