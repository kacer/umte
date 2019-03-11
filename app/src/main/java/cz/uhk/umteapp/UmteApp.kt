package cz.uhk.umteapp

import android.app.Application
import com.raizlabs.android.dbflow.config.FlowManager
import cz.uhk.umteapp.prefs.Prefs

class UmteApp : Application() {

    override fun onCreate() {
        super.onCreate()

        // inicializace DB, RESTu, ...
        Prefs.init(applicationContext)

        FlowManager.init(this)
    }

}