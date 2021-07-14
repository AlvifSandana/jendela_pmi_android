package com.app.jendelapmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.app.jendelapmi.models.HomeModel
import com.app.jendelapmi.retrofit.ApiService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        // define menu bottomNavigation
        val menu: Menu = bottomNavigation.menu
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
        val transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.rootFragment, fragment)
        transaction.commit()
    }
//
//    override fun onStart() {
//        super.onStart()
//        getDataFromApi()
//    }
//
//    // get data from api
//    fun getDataFromApi(){
//        // define api service and implement its methods
//        ApiService.endpoint.getKegiatan()
//            .enqueue(object: Callback<HomeModel> {
//                override fun onResponse(call: Call<HomeModel>, response: Response<HomeModel>) {
//                    showKegiatanData(response.body()!!)
//                }
//
//                override fun onFailure(call: Call<HomeModel>, t: Throwable) {
//                    Log.d("MainActivity", t.toString())
//                }
//
//            })
//    }
//
//    fun showKegiatanData(data: HomeModel){
//        val status = data.status
//        val message = data.message
//        val results = data.data
//
//        Log.d("MainActivity", message)
//    }
}