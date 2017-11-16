package com.rkmax.myapplication

import android.app.Application
import io.realm.Realm

/**
 * Created by rkmax on 15/11/17.
 */
class ColorsApp: Application() {
    override fun onCreate() {
        super.onCreate()
        // Realm setup
        Realm.init(this)
    }
}