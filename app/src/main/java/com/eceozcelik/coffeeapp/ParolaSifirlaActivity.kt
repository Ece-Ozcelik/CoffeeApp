package com.eceozcelik.coffeeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowManager
import com.eceozcelik.coffeeapp.databinding.ActivityParolaSifirlaBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_parola_sifirla.*

class ParolaSifirlaActivity : AppCompatActivity() {
    lateinit var binding: ActivityParolaSifirlaBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityParolaSifirlaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        auth= FirebaseAuth.getInstance()


        binding.sifirlaResetPasswordBtn.setOnClickListener {
            var parola_sifirla_email= binding.sifirlaEmail.text.toString().trim()
            if (TextUtils.isEmpty(parola_sifirla_email)){
                binding.sifirlaEmail.error="Please Enter Your Email"
            } else{
                auth.sendPasswordResetEmail(parola_sifirla_email)
                    .addOnCompleteListener(this){sifirlama->
                        if (sifirlama.isSuccessful){
                            binding.sifirlaMessageText.text="A reset link has been sent to your email address, please your check"
                        } else{
                            binding.sifirlaMessageText.text="Reset Failed"
                        }

                    }
            }

        }

        binding.sifirlaLoginBtn.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this@ParolaSifirlaActivity,GirisActivity::class.java))
            finish()


            }


    }
}