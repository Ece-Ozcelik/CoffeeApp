package com.eceozcelik.coffeeapp

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.eceozcelik.coffeeapp.databinding.ActivityGirisBinding
import com.google.firebase.auth.FirebaseAuth

class GirisActivity : AppCompatActivity() {
 private   lateinit var auth: FirebaseAuth
    lateinit var binding: ActivityGirisBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding= ActivityGirisBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        auth= FirebaseAuth.getInstance()

       // kullanıcının oturum kontrolü

        val currentUser= auth.currentUser
        if(currentUser!=null){
            val intent= Intent( this@GirisActivity,MenuActivity::class.java)
            startActivity(intent)
            finish()

        }

        // giriş yap butonun tıklandığında

        binding.girisLoginbutton.setOnClickListener {


            var giris_username= binding.girisUsernameTxt.text.toString()
            var giris_password= binding.girisPaswordTxt.text.toString()

            if (TextUtils.isEmpty(giris_username)){
                binding.girisUsernameTxt.error= "Please Enter Your Username"
                return@setOnClickListener
            } else if( TextUtils.isEmpty(giris_password)){
                binding.girisPaswordTxt.error= "Please Enter Your Password"
                return@setOnClickListener

            }
            // giriş bilgilerimizi doğrulayıp giriş yapmak

            auth.signInWithEmailAndPassword(giris_username,giris_password)
                .addOnCompleteListener(this){

                    if (it.isSuccessful){
                        intent= Intent(applicationContext,MenuActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {

                        Toast.makeText(applicationContext,"Incorrect login, please try again.",Toast.LENGTH_LONG).show()

                    }
                }


        }
// yeni üyelik sayfasına gitmek için.

        binding.girisNewUser.setOnClickListener{
            intent= Intent(applicationContext,UyeActivity::class.java)
            startActivity(intent)
            finish()

        }

// parolamı unuttum
        binding.girisForgotPassword.setOnClickListener{
            intent= Intent(applicationContext,ParolaSifirlaActivity::class.java)
            startActivity(intent)
            finish()

        }



    }
}