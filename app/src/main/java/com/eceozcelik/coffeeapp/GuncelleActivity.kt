package com.eceozcelik.coffeeapp

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.eceozcelik.coffeeapp.databinding.ActivityGuncelleBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class GuncelleActivity : AppCompatActivity() {
  private  lateinit var binding: ActivityGuncelleBinding
    private lateinit var auth: FirebaseAuth
    var databaseReference: DatabaseReference?=null
    var database: FirebaseDatabase?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

      binding= ActivityGuncelleBinding.inflate(layoutInflater)

        setContentView(binding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)


        auth= FirebaseAuth.getInstance()
        database= FirebaseDatabase.getInstance()
        databaseReference= database?.reference!!.child("profile")

        val currentUser= auth.currentUser
        binding.guncelleEmail.setText(currentUser?.email)

        //realtime database de bulunan kullanıcının id si ile username alınması



        val userReference= databaseReference?.child(currentUser?.uid!!)
        userReference?.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
              binding.guncelleUsername.setText(snapshot.child("username").value.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        // update buton

        binding.guncelleUpdateBtn.setOnClickListener {

            val guncelle_email= binding.guncelleEmail.text.toString().trim()
            currentUser!!.updateEmail(guncelle_email)
                .addOnCompleteListener{task->
                    if (task.isSuccessful){
                        Toast.makeText(applicationContext,"Email Updated",Toast.LENGTH_LONG).show()
                    } else{
                        Toast.makeText(applicationContext,"Email Failed",Toast.LENGTH_LONG).show()

                    }

                }
             // parolamı güncelle

            val guncelle_parola= binding.guncellePassword.text.toString().trim()
            currentUser!!.updateEmail(guncelle_parola)
                .addOnCompleteListener{task->
                    if (task.isSuccessful){
                        Toast.makeText(applicationContext,"Password Updated",Toast.LENGTH_LONG).show()
                    } else{
                        Toast.makeText(applicationContext,"Password Failed",Toast.LENGTH_LONG).show()

                    }

        }
         // username güncelleme

            val currentUserDb= currentUser?.let { it-> databaseReference?.child(it.uid) }

            currentUserDb?.removeValue()
            currentUserDb?.child("username")?.setValue(binding.guncelleUsername)
            Toast.makeText(applicationContext,"Your Username Updated",
                Toast.LENGTH_LONG).show()
    }
        binding.guncelleLogout.setOnClickListener {
            auth.signOut()
            intent= Intent(applicationContext,GirisActivity::class.java)
            startActivity(intent)
            finish()
        }

        }
        }
