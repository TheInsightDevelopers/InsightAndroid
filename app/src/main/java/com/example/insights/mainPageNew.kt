package com.example.insights

import BookFragment
import HomeFragment
import ProfileFragment
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class mainPageNew : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page_new)

        val homeFragment=HomeFragment()
        val bookFragment=BookFragment()
        val profileFragment=ProfileFragment()

        setCurrentFragment(homeFragment)


        val bottomNavigationView:BottomNavigationView=findViewById(R.id.bottomNavigationView)


        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home->setCurrentFragment(homeFragment)
                R.id.book->setCurrentFragment(bookFragment)
                R.id.profile->setCurrentFragment(profileFragment)

            }
            true
        }

    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }

}


