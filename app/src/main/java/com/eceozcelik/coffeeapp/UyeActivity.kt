package com.eceozcelik.coffeeapp

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.eceozcelik.coffeeapp.databinding.ActivityUyeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class UyeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUyeBinding
    private lateinit var auth: FirebaseAuth
    var databaseReference: DatabaseReference?=null
    var database: FirebaseDatabase?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUyeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)



        auth= FirebaseAuth.getInstance()
        database= FirebaseDatabase.getInstance()
        databaseReference= database?.reference!!.child("profile")

        // kaydet butonu

        binding.uyeSignupBtn.setOnClickListener {



            val uye_username= binding.uyeUsername.text.toString()
            val uye_email= binding.uyeEmail.text.toString()
            val uye_password= binding.uyePassword.text.toString()

            if (TextUtils.isEmpty(uye_username)){
                binding.uyeUsername.error="Please Enter Your Firstname"
                return@setOnClickListener

            } else if (TextUtils.isEmpty(uye_email)){
                binding.uyeEmail.error="Please Enter Your Email"
                return@setOnClickListener


            } else if (TextUtils.isEmpty(uye_password)){
                binding.uyePassword.error="Please Enter Your Password"
                return@setOnClickListener

                }
            // email,password, firstname,lastname veri tabanına ekleme

            auth.createUserWithEmailAndPassword(binding.uyeEmail.text.toString(),
                binding.uyePassword.text.toString())
                .addOnCompleteListener(this){ task->
                    if(task.isSuccessful)   {
                        // kullanıcı bilgilerini alalım

                        val currentUser= auth.currentUser
                        // kullanıcı id alıp id altında ad soyad kaydı

                        val currentUserDb= currentUser?.let { it-> databaseReference?.child(it.uid) }

                        currentUserDb?.child("username")?.setValue(binding.uyeUsername.text.toString())

                        Toast.makeText(this@UyeActivity,"Registration Successful",Toast.LENGTH_LONG).show()

                    } else {

                        Toast.makeText(this@UyeActivity,"Registration Is Incorrect",Toast.LENGTH_LONG).show()
                    }


                }

        }
        // Giriş sayfasına gitmek için
         binding.uyeLoginBtn.setOnClickListener {
             intent= Intent(applicationContext,GirisActivity::class.java)
             startActivity(intent)
             finish()
         }



    }
   




}