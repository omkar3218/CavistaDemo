package com.omkar.cavistademo.di

import com.omkar.cavistademo.CavistaDemoApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 *  Components
 */
@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityModuleBuilder::class,
        FragmentModuleBuilder::class,
        ViewModelModule::class]
)

/**
 *  Interface
 */
interface AppComponent : AndroidInjector<CavistaDemoApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: CavistaDemoApplication): Builder
        fun build(): AppComponent
    }
}