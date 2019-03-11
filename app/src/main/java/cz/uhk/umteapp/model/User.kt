package cz.uhk.umteapp.model

import java.io.Serializable

data class User(var name: String, var lastname: String, var age: Int, var weight: Int) : Serializable {

}