package cz.uhk.umteapp.services

import android.app.IntentService
import android.content.Intent
import cz.uhk.umteapp.ws.StagService
import cz.uhk.umteapp.ws.stagService
import java.text.SimpleDateFormat
import java.util.*

class RoomService : IntentService {

    // pokud ma trida vice konstruktoru, tak musi byt zapsano takto, nelze zkombinovat
    // se zapisem konstruktoru v deklarci tridy
    constructor() : super("RoomService")
    constructor(name: String?) : super(name)

    override fun onHandleIntent(intent: Intent?) {
        val rooms = mutableListOf("J8", "J9", "J10", "J23")
        val building = "J"
        val date = SimpleDateFormat("d.M.yyyy").format(Date())

        for(room in rooms) {
            val response = stagService.getTimetable(building, room, date, date, StagService.JSON).execute()

            response.body()?.items?.forEach { // foreach vynecha prvky null z listu
                println(it.toString())
            }
        }
    }
}