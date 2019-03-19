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
import merchant.com.our.nextlounge.helper.AppUtils.Companion.PREF_EMAIL
import merchant.com.our.nextlounge.helper.AppUtils.Companion.PREF_IS_LOGGEDIN
import merchant.com.our.nextlounge.helper.AppUtils.Companion.PREF_TOKEN
import merchant.com.our.nextlounge.helper.AppUtils.Companion.PREF_USERID
import merchant.com.our.nextlounge.helper.AppUtils.Companion.PREF_USERNAME
import merchant.com.our.nextlounge.helper.Const
import merchant.com.our.nextlounge.helper.HttpUtility
import merchant.com.our.nextlounge.helper.SharedPreferenceUtil
import merchant.com.our.nextlounge.model.LoginModel

class LoginActivity : AppCompatActivity() {
    private var editPassword : EditText ?= null
    private var editEmail : EditText ?= null
    private var forgot : TextView ?= null
    private var radioSave : RadioButton ?= null
    private var appUtils: AppUtils ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editEmail = findViewById(R.id.editEmail)
        editPassword = findViewById(R.id.editPassword)
        forgot = findViewById(R.id.textForgot)
        radioSave = findViewById(R.id.radioButtonSave)

        appUtils = AppUtils(this)
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
          //  startActivity(Intent(this, MenusActivity::class.java))
            LoginAsync(passwordText, emailText).execute()
    }

    @SuppressLint("StaticFieldLeak")
    inner class LoginAsync(private var passwordText:String , private var emailText:String):AsyncTask<Void,Int, String>(){

        override fun doInBackground(vararg p0: Void?): String? {
            val map = HashMap<String, Any?>()
            map["email"] = emailText
            map["password"] = passwordText
            val url = Const.NEXT_URL+"login"
            return HttpUtility.sendPostRequest(url, map)
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            if (result != null){
                val gson = Gson()
                val type= object : TypeToken<LoginModel>(){}.type
                val login = gson.fromJson<LoginModel>(result, type)
                if (login != null){
                    if (login.success != null) {
                        val details = login.details!!
                        val sharedPreferences = SharedPreferenceUtil
                        sharedPreferences.save(this@LoginActivity, details.name!!, PREF_USERNAME)
                        sharedPreferences.save(this@LoginActivity, details.email!!, PREF_EMAIL)
                        sharedPreferences.save(this@LoginActivity, details.id!!.toString(), PREF_USERID)
                        sharedPreferences.save(this@LoginActivity, login.success!!.token!!, PREF_TOKEN)
                        sharedPreferences.save(this@LoginActivity, "1", PREF_IS_LOGGEDIN)

                        Toast.makeText(this@LoginActivity, login.details!!.name!! + " welcome to The Next Lounge", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this@LoginActivity,MenusActivity::class.java ))
                    }
                    else{
                        appUtils!!.showAlert(login.error!!)
                    }
                }

            }
        }

    }
}
