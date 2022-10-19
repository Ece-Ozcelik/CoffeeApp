package com.eceozcelik.coffeeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import com.eceozcelik.coffeeapp.databinding.ActivityProfilBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ProfilActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfilBinding
    private lateinit var auth: FirebaseAuth
    var databaseReference: DatabaseReference?=null
    var database: FirebaseDatabase?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)


        auth= FirebaseAuth.getInstance()
        database= FirebaseDatabase.getInstance()
        databaseReference= database?.reference!!.child("profile")

        var currentUser= auth.currentUser
        binding.profilEmail.text= "Email:" +currentUser?.email


         // realtime - database id içerisindeki veriyi sayfaya aktarmak

      var userReference= databaseReference?.child(currentUser?.uid!!)

        userReference?.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

               binding.profilUsername.text= "Username:" +snapshot.child("username").value.toString()
            }

            override fun onCancelled(error: DatabaseError) {

            }


        })

        // çıkış yap butonu
        binding.profilLogout.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this@ProfilActivity,MenuActivity::class.java))
            finish()


        }
        // hesabımı sil butonu

        binding.profilDelete.setOnClickListener {

            currentUser?.delete()?.addOnCompleteListener{

                if (it.isSuccessful){
                    Toast.makeText(applicationContext,"Your account has been deleted",
                    Toast.LENGTH_LONG).show()
                    auth.signOut()
                    startActivity(Intent(this@ProfilActivity,GirisActivity::class.java))
                    finish()

                    val currentUserDb= currentUser?.let { it-> databaseReference?.child(it.uid) }

                    currentUserDb?.removeValue()
                    Toast.makeText(applicationContext,"Your username deleted",
                        Toast.LENGTH_LONG).show()
                }
            }
        }


        binding.profilUpdate.setOnClickListener {
            startActivity(Intent(this@ProfilActivity,GuncelleActivity::class.java))
            finish()



    }
}

    }
