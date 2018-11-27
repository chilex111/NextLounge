package merchant.com.our.nextlounge.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AppCompatDelegate
import android.widget.Button
import merchant.com.our.nextlounge.R

class DashAuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        setContentView(R.layout.activity_dash_auth)

        findViewById<Button>(R.id.buttonSignIn).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        findViewById<Button>(R.id.buttonSignUp).setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}
