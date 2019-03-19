package merchant.com.our.nextlounge.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import merchant.com.our.nextlounge.R
import merchant.com.our.nextlounge.fragment.NewOrderFragment
import merchant.com.our.nextlounge.fragment.OrderTypeFragment
import merchant.com.our.nextlounge.fragment.TableCountFragment
import merchant.com.our.nextlounge.listener.StringListener

class MenusActivity : AppCompatActivity(), StringListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menus)

        TableCountFragment.stringListener = this

        findViewById<TextView>(R.id.textNew).setOnClickListener {
            supportFragmentManager.beginTransaction()
                    .add(R.id.container, TableCountFragment())
                    .addToBackStack(null)
                    .commit()
        }
        findViewById<TextView>(R.id.textLogOut).setOnClickListener {
           //Logout
        }
        findViewById<TextView>(R.id.textPending).setOnClickListener {
            supportFragmentManager.beginTransaction()
                    .add(R.id.container, OrderTypeFragment.newInstance("Pending", null))
                    .addToBackStack(null)
                    .commit()
        }
        findViewById<TextView>(R.id.textMenu).setOnClickListener {
            supportFragmentManager.beginTransaction()
                    .add(R.id.container, NewOrderFragment())
                    .addToBackStack(null)
                    .commit()
        }
        findViewById<TextView>(R.id.textSpecials)
        findViewById<TextView>(R.id.textActive).setOnClickListener {
            supportFragmentManager.beginTransaction()
                    .add(R.id.container, OrderTypeFragment.newInstance("Active", null))
                    .addToBackStack(null)
                    .commit()
        }

    }
    override fun stringListener(value: String) {
        supportFragmentManager.beginTransaction()
                .add(R.id.container, NewOrderFragment.newInstance(value,null))
                .addToBackStack(null)
                .commit()
    }

}
