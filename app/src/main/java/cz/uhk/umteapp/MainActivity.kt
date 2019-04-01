package cz.uhk.umteapp

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import cz.uhk.umteapp.prefs.Prefs
import cz.uhk.umteapp.sensors.SensorActivity
import cz.uhk.umteapp.services.RoomService
import cz.uhk.umteapp.ws.ScheduleDTO
import cz.uhk.umteapp.ws.StagService
import cz.uhk.umteapp.ws.stagService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openButton.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("key", "Hi")
            startActivityForResult(intent, 777)
        }

        openListButton.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }

        wsButton.setOnClickListener {
            val call = stagService.getHarmonogram(StagService.JSON)
            call.enqueue(object: Callback<ScheduleDTO> {

                override fun onResponse(call: Call<ScheduleDTO>, response: Response<ScheduleDTO>) {
                    println("onResponse")
                    Toast.makeText(this@MainActivity, response.body()?.toString(), Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<ScheduleDTO>, t: Throwable) {
                    println("onFailure")
                }
            })

            // service se spusti jen jednou i pokud se to zavola nekolikrat za sebou (optimalizace androidu)
            startService(Intent(this@MainActivity, RoomService::class.java))
        }

        sensorButton.setOnClickListener {
            val intent = Intent(this, SensorActivity::class.java)
            startActivity(intent)
        }

        val appStartMillis = Prefs.getAppStart()
        Toast.makeText(this, "$appStartMillis", Toast.LENGTH_LONG).show()
        Prefs.setAppStart(System.currentTimeMillis())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 777 && resultCode == Activity.RESULT_OK) {
            //val - final nemenna, var - variable promenna
            var value = data?.getStringExtra("result")
            Toast.makeText(this, value, Toast.LENGTH_LONG).show()
        }
    }
}
