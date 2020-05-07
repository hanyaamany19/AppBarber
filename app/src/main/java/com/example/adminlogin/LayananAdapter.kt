package com.example.adminlogin

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.adminlogin.LayananAdapter.*

class LayananAdapter (val layananList: ArrayList<Layanan>): RecyclerView.Adapter<ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textName = itemView.findViewById(R.id.t_nama) as TextView
        val textHarga = itemView.findViewById(R.id.t_harga) as TextView
        val textKeterangan = itemView.findViewById(R.id.t_keterangan) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v= LayoutInflater.from(parent?.context).inflate(R.layout.layanan_list, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return layananList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val lyn: Layanan=layananList[position]
        holder?.textName?.text = lyn.name
        holder?.textHarga?.text = lyn.harga
        holder?.textKeterangan?.text = lyn.keterangan
    }
}