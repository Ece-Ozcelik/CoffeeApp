package com.eceozcelik.coffeeapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var topAnimation: Animation
    private lateinit var bottomAnimation: Animation

    private lateinit var imageView: ImageView
    private lateinit var title_txt: TextView
    private lateinit var description_txt: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)



        supportActionBar?.hide()

        topAnimation= AnimationUtils.loadAnimation(this,R.anim.top_animation)
        bottomAnimation= AnimationUtils.loadAnimation(this,R.anim.bottom_animation)

       imageView= findViewById(R.id.splash_image)
       title_txt= findViewById(R.id.splash_title_txt)
       description_txt= findViewById(R.id.splash_description_txt)

        imageView.animation=topAnimation
        title_txt.animation=bottomAnimation
        description_txt.animation=bottomAnimation
        val handler= Handler()
        handler.postDelayed({
    val intent= Intent(this@SplashScreenActivity,GirisActivity::class.java)
    startActivity(intent)
    finish()
},3000)







            }
        }





