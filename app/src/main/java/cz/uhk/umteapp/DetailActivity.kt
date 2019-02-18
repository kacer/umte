package cz.uhk.umteapp

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        detailTextView.text = "Hello from Main Activity"

        val value = intent.getStringExtra("key")
        Toast.makeText(this, value, Toast.LENGTH_LONG).show()

        confirmButton.setOnClickListener {
            var builder = AlertDialog.Builder(this@DetailActivity) // v jave DetailActivity.this

            builder.setTitle("Dialog")
            builder.setMessage("Do you really want to close this activity?")

            builder.setPositiveButton("Yes") { dialog, _ ->

                val resultIntent = Intent()
                resultIntent.putExtra("result", "Done")
                setResult(Activity.RESULT_OK, resultIntent)

                dialog.dismiss()
                finish()
            }

            builder.setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }

            builder.create().show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}
