package cz.uhk.umteapp

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import cz.uhk.umteapp.prefs.Prefs
import kotlinx.android.synthetic.main.activity_main.*

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
