package cz.uhk.umteapp.ws

import com.google.gson.annotations.SerializedName
import java.lang.StringBuilder

class ScheduleDTO {

    @SerializedName("harmonogramItem")
    var scheduleItem: List<Schedule>? = null

    override fun toString(): String {
        val builder = StringBuilder()

        scheduleItem?.forEach {
            builder.append("\n ${it.toString()}")   //pod it je aktualni prvek
        }

        return builder.toString()
    }
}

class Schedule {

    @SerializedName("datumOd")
    var dateFrom: ValueDTO? = null

    @SerializedName("datumDo")
    var dateTo: ValueDTO? = null

    @SerializedName("popis")
    var description: String? = null

    override fun toString(): String {
        return "$description (${dateFrom?.value}))"
    }
}

class ValueDTO {

    var value: String? = null

}