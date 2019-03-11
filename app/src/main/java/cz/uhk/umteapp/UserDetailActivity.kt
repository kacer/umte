package cz.uhk.umteapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import cz.uhk.umteapp.model.User
import cz.uhk.umteapp.model.UserDB
import kotlinx.android.synthetic.main.activity_user_detail.*

class UserDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        var user: UserDB = intent.getSerializableExtra("user") as UserDB

        nameEditText.setText(user.name)
        lastnameEditText.setText(user.lastname)
        //ageEditText.setText(user.age)
        //weightEditText.setText(user.weight)

        saveButton.setOnClickListener(View.OnClickListener {
            user.name = nameEditText.text.toString()
            user.lastname = lastnameEditText.text.toString()
            //user.age = ageEditText.text.toString().toInt()
            //user.weight = weightEditText.text.toString().toInt()

            val resultIntent = Intent()
            resultIntent.putExtra("user", user)
            setResult(Activity.RESULT_OK, resultIntent)

            finish()
        })
    }
}