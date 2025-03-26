package com.example.giris_sayfasi

import DatabaseHelper
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GirisEkrani : AppCompatActivity() {

    lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_giris)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        db = DatabaseHelper(this)

        val kullaniciAdi = findViewById<EditText>(R.id.kullanici_adi_text)
        val sifre = findViewById<EditText>(R.id.sifre_text)
        val girisButonu = findViewById<Button>(R.id.giris_button)
        val kayitButonu = findViewById<Button>(R.id.kayit_button)

        girisButonu.setOnClickListener {
            val kullaniciAdiGiris = kullaniciAdi.text.toString()
            val sifreGirisi = sifre.text.toString()
            val kullaniciyiKontrolEt = db.kullaniciyi_kontrol_et(kullaniciAdiGiris , sifreGirisi)


            if (kullaniciyiKontrolEt){
                val intent = Intent(this, Anasayfa::class.java)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this, "Kullanıcı adı veya şifre yanlış", Toast.LENGTH_SHORT).show()
            }
        }

        kayitButonu.setOnClickListener {
            val intent = Intent(this, Kayit::class.java)
            startActivity(intent)
            finish()
        }
    }
}