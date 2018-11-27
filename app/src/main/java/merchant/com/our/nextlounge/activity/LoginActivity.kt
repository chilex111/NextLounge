package merchant.com.our.nextlounge.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import merchant.com.our.nextlounge.R

class LoginActivity : AppCompatActivity() {
    private var editPassword : EditText ?= null
    private var editEmail : EditText ?= null
    private var forgot : TextView ?= null
    private var radioSave : RadioButton ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editEmail = findViewById(R.id.editEmail)
        editPassword = findViewById(R.id.editPassword)
        forgot = findViewById(R.id.textForgot)
        radioSave = findViewById(R.id.radioButtonSave)


        findViewById<Button>(R.id.buttonSignIn).setOnClickListener {
            submit()
        }
    }

    private fun submit() {
        val passwordText = editPassword!!.text.toString()
        val emailText = editEmail!!.text.toString()

        if (passwordText.isEmpty())
            editPassword!!.error = "This field is required"
        if (emailText.isEmpty())
            editEmail!!.error = "This field is required"
        else
            startActivity(Intent(this, MenusActivity::class.java))
         //   LoginAsync(passwordText, emailText).execute()
    }

    @SuppressLint("StaticFieldLeak")
    inner class LoginAsync(private var passwordText:String , private var emailText:String):AsyncTask<Void,Int, String>(){

        override fun doInBackground(vararg p0: Void?): String? {
            val map = HashMap<String, Any?>()
            map[""] = emailText
            map[""] = passwordText
            val url = ""
            return null
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            if (result != null){

            }
        }

    }
}
