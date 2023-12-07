package com.example.myrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListHeroAdapter (private val listHero: ArrayList<Hero>) : RecyclerView.Adapter<ListHeroAdapter.ListViewHolder>() { // membuat kelas adapter yang menerima array list hero sbg parameter
    private lateinit var onItemClickCallback: OnItemClickCallback // membuat variable untuk menampung aksi ketika item di RecyclerView diklik

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) { // inisisialisasi variabel onItemClickCallback dengan objek yang diberikan
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) { // ViewHolder dalam RecyclerView. ViewHolder adalah wrapper seperti View yang berisi layout untuk setiap item dalam daftar RecyclerView
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo) // inisialisasi setiap komponen pada layout item
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder { // create ViewHolder (tampilan untuk setiap item di recycler view)
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_hero, parent, false) // mengubah layout XML menjadi object View
        return ListViewHolder(view) // return the object
    }

    override fun getItemCount(): Int  = listHero.size // jumlah item yang ditampilkan

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) { // menghubungkan data ke ViewHolder
        val (name, description, photo) = listHero[position] // ambil data dari array list hero berdasarkan posisi
        Glide.with(holder.itemView.context) //masukkan data ke view object (holder)
            .load(photo) // url gambar
            .into(holder.imgPhoto) // imageView mana yang akan diterapkan
        holder.tvName.text = name
        holder.tvDescription.text = description
        holder.itemView.setOnClickListener{onItemClickCallback.onItemClicked(listHero[holder.adapterPosition])} // memanggil fungsi onItemClicked memberikan data yang diklik sebagai parameter
    }

    interface OnItemClickCallback { //  deklarasi interface yang digunakan untuk menangani aksi ketika item di RecyclerView diklik
        fun onItemClicked(data: Hero)
    }
}