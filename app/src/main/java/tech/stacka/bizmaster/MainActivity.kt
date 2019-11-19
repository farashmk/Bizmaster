package tech.stacka.bizmaster

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.fxn.OnBubbleClickListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var prefs :SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

         prefs = getSharedPreferences("com.stackatech.bizmaster", MODE_PRIVATE);
        setContentView(R.layout.activity_main)

        if (prefs.getBoolean("firstrun", true)) {
            val clickintent=Intent(this,RegisterActivity::class.java)
            startActivity(clickintent)
            // Do first run stuff here then set 'firstrun' as false
            // using the following line to edit/commit prefs
            prefs.edit().putBoolean("firstrun", false).commit();
        }
        val fragment = HomeFragment()
        addfragement(fragment)

        bubbleTabBar.addBubbleListener(object : OnBubbleClickListener {
            override fun onBubbleClick(id: Int) {
                when (id) {
                    R.id.menu_home -> {
                        val fragment = HomeFragment()
                        addfragement(fragment)
                    }
                    R.id.menu_stats -> {
                        val fragment= StatiticsFragment()
                        addfragement(fragment)
                    }
                    R.id.menu_profile ->{
                        val fragment= ProfileFragment()
                        addfragement(fragment)
                    }
                }
            }

        })

    }

    private fun addfragement(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragement_container, fragment)
            .commit()

    }
}
