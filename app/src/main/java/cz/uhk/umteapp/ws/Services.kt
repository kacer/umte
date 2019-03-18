package cz.uhk.umteapp.ws

val stagService by lazy {   // dokud nezavolame v programu stagService, tak nebude vytvořena instance
    StagService.create()
}