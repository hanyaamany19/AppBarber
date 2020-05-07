package com.example.adminlogin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class AntrianAdapter (val antrianList: ArrayList<Antrian>): RecyclerView.Adapter<AntrianAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v= LayoutInflater.from(parent?.context).inflate(R.layout.antrian_list, parent, false)
        return  ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return antrianList.size
    }

    class  ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textViewName = itemView.findViewById(R.id.tNama) as TextView
        val textViewNo = itemView.findViewById(R.id.tNoAntrian) as TextView
        val textViewModel = itemView.findViewById(R.id.tModel) as TextView
        val textViewLayanan = itemView.findViewById(R.id.tLayanan) as TextView
        val textViewTime = itemView.findViewById(R.id.tTime) as TextView
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val antr: Antrian=antrianList[position]
        holder?.textViewName?.text = antr.name
        holder?.textViewNo?.text = antr.no_antrian
        holder?.textViewModel?.text = antr.model
        holder?.textViewLayanan?.text = antr.layanan
        holder?.textViewTime?.text = antr.time
    }
}