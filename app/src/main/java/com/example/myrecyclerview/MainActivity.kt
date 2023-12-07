package com.example.myrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvHeroes: RecyclerView // deklarasi variabel untuk menyimpan object RecyclerView
    private val list = ArrayList<Hero>() // deklarasi variabel untuk menyimpan ArrayList Hero

    override fun onCreate(savedInstanceState: Bundle?) { //inisialisasi awal ketika aktivitas dibuat. menerima sebuah parameter berupa savedInstanceState, yang merupakan objek Bundle yang menyimpan data dari aktivitas sebelumnya "jika ada"
        super.onCreate(savedInstanceState) // menjalankan proses inisialisasi
        setContentView(R.layout.activity_main) // menetapkan layout yang akan ditampilkan oleh aktivitas

        rvHeroes = findViewById(R.id.rv_heroes) //inisialisasi variabel rvHeroes dengan objek RecyclerView
        rvHeroes.setHasFixedSize(true) //menetapkan ukuran RecyclerView tetap

        list.addAll(getListHeroes()) // menambahkan semua data (array string dan array gambar) dari fungsi getListHeroes ke variabel list.
        showRecyclerList() // menampilkan daftar pahlawan dalam bentuk RecyclerView dengan menggunakan adapter dan layout manager
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean { // menambahkan menu ke aktivitas. menerima objek Menu yang menyimpan item-item menu
        menuInflater.inflate(R.menu.menu_main, menu) // mengubah layout XML menjadi objek Menu
        return super.onCreateOptionsMenu(menu) // menjalankan proses
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { // Fungsi ini bertugas untuk menangani aksi ketika item menu opsi dipilih.
        when (item.itemId) { // cek item yang dipilih
            R.id.action_list -> { // kalau action_list...
                rvHeroes.layoutManager = LinearLayoutManager(this) // tampilkan dalam bentuk linear
            }
            R.id.action_grid -> { // kalau action_grid...
                rvHeroes.layoutManager = GridLayoutManager(this, 2) // tampilkan dalam bentuk grid dengan ukuran 2
            }
        }
        return super.onOptionsItemSelected(item) // menjalankan proses
    }

    private fun getListHeroes(): ArrayList<Hero> { // deklarasi fungsi yang mengembalikan koleksi data dari objek Hero yang dibuat dari sumber daya aplikasi, seperti array string dan array gambar.
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val listHero = ArrayList<Hero>()
        for (i in dataName.indices) {
            val hero = Hero(dataName[i], dataDescription[i], dataPhoto[i])
            listHero.add(hero)
        }
        return listHero
    }

    private fun showRecyclerList() { // menampilkan daftar pahlawan dalam bentuk RecyclerView dengan menggunakan adapter dan layout manager
        rvHeroes.layoutManager = LinearLayoutManager(this) // mengatur item-item di RecyclerView
        val listHeroAdapter = ListHeroAdapter(list) // menghubungkan data dari ArrayList list dengan RecyclerView
        rvHeroes.adapter = listHeroAdapter // menetapkan adapter untuk RecyclerView.

        listHeroAdapter.setOnItemClickCallback(object : ListHeroAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Hero) {
                showSelectedHero(data)
            }
        })
    }

    private fun showSelectedHero(hero: Hero) { // menampilkan pahlawan yang dipilih dengan menggunakan Toast, yaitu sebuah kelas yang digunakan untuk menampilkan pesan singkat di layar
        Toast.makeText(this, "Kamu memilih " + hero.name, Toast.LENGTH_SHORT).show()
    }
}