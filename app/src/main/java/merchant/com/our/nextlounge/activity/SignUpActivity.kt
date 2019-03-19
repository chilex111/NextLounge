package merchant.com.our.nextlounge.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import merchant.com.our.nextlounge.R
import merchant.com.our.nextlounge.helper.AppUtils
import merchant.com.our.nextlounge.helper.Const
import merchant.com.our.nextlounge.helper.HttpUtility
import merchant.com.our.nextlounge.model.SignUpModel

class SignUpActivity : AppCompatActivity() {

    private var editPassword : EditText?= null
    private var editEmail : EditText?= null
    private var editUsername : EditText?= null
    private var radioTerms : RadioButton?= null
    private var appUtils: AppUtils ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        editEmail = findViewById(R.id.editEmail)
        editPassword = findViewById(R.id.editPassword)
        editUsername = findViewById(R.id.editUsername)
        radioTerms = findViewById(R.id.radioButtonTerms)

        appUtils = AppUtils(this)

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
        SignUpAsync(passwordText, emailText, userNameText).execute()

    }

    @SuppressLint("StaticFieldLeak")
    inner class SignUpAsync(private var passwordText: String, private var emailText: String,
                            private var userNameText: String) :AsyncTask<Void,Int,String>() {
        override fun doInBackground(vararg p0: Void?): String {
            val map = HashMap<String, Any?>()
            map["password"] = passwordText
            map["email"] = emailText
            map["name"] = userNameText
            map["c_password"] = passwordText

            val url = Const.NEXT_URL + "register"
            return HttpUtility.sendPostRequest(url, map)
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            if (result != null){
                val gson = Gson()
                val type= object : TypeToken<SignUpModel>(){}.type
                val signUpModel = gson.fromJson<SignUpModel>(result, type)
                if (signUpModel != null) {
                    if (signUpModel.success != null) {
                        Toast.makeText(this@SignUpActivity, signUpModel.success!!.name + " Congratulations for Registering with The Next", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
                    }
                }

            }
        }
    }
}
