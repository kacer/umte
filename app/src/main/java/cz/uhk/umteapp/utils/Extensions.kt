package cz.uhk.umteapp.utils

import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import java.text.DecimalFormat


val decimalFormat by lazy {
    DecimalFormat("#.###")
}

fun Float.toOurFormat():String { // rozsireni floatu o metodu toOurFormat
    return decimalFormat.format(this)
}

fun <T> Spinner.onItemSelected(listener: (item: T) -> Unit) {
    this.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {

        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            listener.invoke(selectedItem as T)
        }
    }
}