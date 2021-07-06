package com.app.jendelapmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // define menu bottomNavigation
        var menu: Menu = bottomNavigation.menu
        // set item for selectedMenu
        selectedMenu(menu.getItem(0))
        // set when bottomNavigation clicked
        bottomNavigation.setOnNavigationItemSelectedListener {
            item: MenuItem -> selectedMenu(item)
            false
        }
    }

    /**
     * function selectedMenu
     *
     * handle when user checked or clicked menu item
     * and show fragment.
     */
    private fun selectedMenu(item: MenuItem){
        item.isChecked = true
        when(item.itemId){
            R.id.homeMenu -> selectedFragment(HomeFragment.getInstance())
            R.id.UDDMenu -> selectedFragment(UDDFragment.getInstance())
            R.id.NotifikasiMenu -> selectedFragment(NotifikasiFragment.getInstance())
            R.id.userMenu -> selectedFragment(ProfileFragment.getInstance())
        }
    }

    /**
     * function selectedFragment
     *
     * handle showing fragment by clicked menu item
     * from bottomNavigation.
     */
    private fun selectedFragment(fragment: Fragment) {
        var transaction : FragmentTransaction? = supportFragmentManager.beginTransaction()
        transaction?.replace(R.id.rootFragment, fragment)
        transaction?.commit()
    }
}