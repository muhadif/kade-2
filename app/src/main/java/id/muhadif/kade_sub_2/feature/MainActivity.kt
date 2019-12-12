package id.muhadif.kade_sub_2.feature

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import id.muhadif.kade_sub_2.R
import id.muhadif.kade_sub_2.databinding.ActivityMainBinding
import id.muhadif.kade_sub_2.feature.dashboard.DashboardFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.bottomNav.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.dashboard -> {
                    loadMatchesFragment(savedInstanceState)
                    return@setOnNavigationItemReselectedListener
                }
            }
        }

        binding.bottomNav.selectedItemId = R.id.dashboard
    }

    private fun loadMatchesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null){
            doFragmentTransaction(DashboardFragment())
        }
    }

    fun doFragmentTransaction(fragment : Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_main, fragment)
            .commit()
    }


}
