package com.eceozcelik.coffeeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_hot_drinks.*

class HotDrinksActivity : AppCompatActivity() {
    private lateinit var coffeesArrayList: ArrayList<Coffees>
    private lateinit var adapter: CoffeesAdapter
    private lateinit var auth: FirebaseAuth
    var databaseReference: DatabaseReference?=null
    var database: FirebaseDatabase?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hot_drinks)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)


        auth= FirebaseAuth.getInstance()
        database= FirebaseDatabase.getInstance()
        databaseReference= database?.reference!!.child("coffees")


        rv.setHasFixedSize(true)
        rv.layoutManager= StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)

        val c1= Coffees(1,"latte","Latte",34.99 )
        val c2= Coffees(2,"americano","Americano",30.99 )
        val c3= Coffees(3,"espresso","Espresso",24.99 )
        val c4= Coffees(4,"macchiato","Macchiato",14.99 )
        val c5= Coffees(5,"mocha","Mocha",20.99 )
        val c6= Coffees(6,"Filter","Filter",15.99 )


        coffeesArrayList= ArrayList<Coffees>()

        coffeesArrayList.add(c1)
        coffeesArrayList.add(c2)
        coffeesArrayList.add(c3)
        coffeesArrayList.add(c4)
        coffeesArrayList.add(c5)
        coffeesArrayList.add(c6)

        adapter= CoffeesAdapter(this@HotDrinksActivity,coffeesArrayList)

        rv.adapter= adapter
    }
}