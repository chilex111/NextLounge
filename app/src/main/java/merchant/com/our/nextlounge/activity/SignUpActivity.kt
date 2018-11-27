package merchant.com.our.nextlounge.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import merchant.com.our.nextlounge.R

class SignUpActivity : AppCompatActivity() {

    private var editPassword : EditText?= null
    private var editEmail : EditText?= null
    private var editUsername : EditText?= null
    private var radioTerms : RadioButton?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        editEmail = findViewById(R.id.editEmail)
        editPassword = findViewById(R.id.editPassword)
        editUsername = findViewById(R.id.editUsername)
        radioTerms = findViewById(R.id.radioButtonTerms)


        findViewById<TextView>(R.id.buttonSignIn).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        findViewById<Button>(R.id.buttonSignUp).setOnClickListener {
            submit()
        }
    }

    private fun submit() {
        val passwordText = editPassword!!.text.toString()
        val emailText = editEmail!!.text.toString()
        val userNameText = editUsername!!.text.toString()
        if (passwordText.isEmpty())
            editPassword!!.error = "This field is required"
        if (emailText.isEmpty())
            editEmail!!.error = "This field is required"
        if (userNameText.isEmpty())
            editEmail!!.error = "This field is required"
        if (!radioTerms!!.isChecked)
        //Please add a pop up
        else
            startActivity(Intent(this, LoginActivity::class.java))
        //   LoginAsync(passwordText, emailText).execute()

    }
}
