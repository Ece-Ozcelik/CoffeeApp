package com.eceozcelik.coffeeapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class CoffeesAdapter(private val mContext: Context,private val coffeesList: List<Coffees>) : RecyclerView.Adapter<CoffeesAdapter.CoffeeTasarimNesneTutucu>(){


    inner class CoffeeTasarimNesneTutucu(view:View):RecyclerView.ViewHolder(view){

        var latte_imageview = ImageView(mContext)
        var latte_baslik= TextView(mContext)
        var latte_fiyat= TextView(mContext)
        var buttonSepeteEkle= Button(mContext)

        init {


            latte_imageview= view.findViewById(R.id.latte_imageview)
            latte_baslik= view.findViewById(R.id.latte_baslik)
            latte_fiyat= view.findViewById(R.id.latte_fiyat)
            buttonSepeteEkle= view.findViewById(R.id.buttonSepeteEkle)


        }


    }
    override fun getItemCount(): Int {
        return coffeesList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeTasarimNesneTutucu {
      val tasarim = LayoutInflater.from(mContext).inflate(R.layout.coffee_tasarim,parent,false)
        return CoffeeTasarimNesneTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CoffeeTasarimNesneTutucu, position: Int) {
      val coffee= coffeesList[position]
        holder.latte_baslik.text= coffee.coffee_ad
        holder.latte_fiyat.text= "${coffee.coffee_fiyat} TL"

        holder.latte_imageview.setImageResource(
            mContext.resources.getIdentifier(coffee.coffee_ad,"drawable",mContext.packageName))

        holder.buttonSepeteEkle.setOnClickListener{
            Toast.makeText(mContext,"${coffee.coffee_ad} Add to List",Toast.LENGTH_SHORT).show()
        }


    }




}
