package com.example.homework3.view


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.homework3.R
import com.example.homework3.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), StartMyActivity {
    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

//        navController = binding.navHostFragment.findNavController()
        val navFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navFragment.navController
        val navView = binding.navView
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.calendarFragment,
                R.id.exercisesFragment
            )
        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        setContentView(binding.root)
    }

    override fun startMyIntent(i: Intent) {
        startActivity(i)
    }
//    @RequiresApi(Build.VERSION_CODES.O)
//    public fun replaceFragments(forward: Boolean) {
//
//        val fragment =  if (forward) NewTrainingEventActivity() else CalendarFragment()
//
////        navController.navigate(action)
//        // Insert the fragment by replacing any existing fragment
//        val fragmentManager= supportFragmentManager
//        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment)
//            .commit()
//    }

}

interface StartMyActivity {
    fun startMyIntent(i: Intent)
}