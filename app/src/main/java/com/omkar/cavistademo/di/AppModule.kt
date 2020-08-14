package com.omkar.cavistademo.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.omkar.cavistademo.CavistaDemoApplication
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
class AppModule {

    @Provides
    fun provideContext(app: CavistaDemoApplication): Context {
        return app.applicationContext
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideCoroutineContext(): CoroutineContext {
        return Dispatchers.IO
    }


}
