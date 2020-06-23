package com.ahmadtakkoush.appledeveloper

import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import fragments.NewsFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val liveFragment = LiveFragment()
        val newsFragment = NewsFragment()

        makeCurrentFragment(homeFragment)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ic_forum -> makeCurrentFragment(homeFragment)
                R.id.ic_live_tv -> makeCurrentFragment(liveFragment)
                R.id.ic_library -> makeCurrentFragment(newsFragment)

            }
            true
        }
    }

    override fun onBackPressed() {
        if (WebView.canGoBack()) {
            WebView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && WebView.canGoBack()) {
            WebView.goBack()
            //If there is history, then the canGoBack method will return ‘true’//
            return true
        }

        //If the button that’s been pressed wasn’t the ‘Back’ button, or there’s currently no
        //WebView history, then the system should resort to its default behavior and return
        //the user to the previous Activity//

        return super.onKeyDown(keyCode, event)
    }

    private fun makeCurrentFragment(fragment: Fragment) =
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fl_wrapper, fragment)
                commit()
            }

}