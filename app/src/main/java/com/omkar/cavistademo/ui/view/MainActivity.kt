package com.omkar.cavistademo.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.omkar.cavistademo.R
import dagger.android.AndroidInjection

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        if(savedInstanceState==null)
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, SearchImageListFragment.newInstance()).commit()
    }
}
