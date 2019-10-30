package tech.stacka.bizmaster

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.fxn.OnBubbleClickListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
