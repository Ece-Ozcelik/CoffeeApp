package com.eceozcelik.coffeeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {
  private  lateinit var auth: FirebaseAuth

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater= menuInflater
        menuInflater.inflate(R.menu.ana_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.cikis_yap){
            auth.signOut()
            val intent= Intent(this@MenuActivity,GirisActivity::class.java)
            startActivity(intent)
            finish()

        }
        return super.onOptionsItemSelected(item)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        auth= FirebaseAuth.getInstance()

        hot_drinks.setOnClickListener {
            intent= Intent(this@MenuActivity,HotDrinksActivity::class.java)
            startActivity(intent)
            finish()

        }


    }
}