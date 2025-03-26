package com.example.giris_sayfasi

import DatabaseHelper
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class Kayit : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kayit)

        auth = Firebase.auth


        val kullaniciAdiText = findViewById<EditText>(R.id.kullanici_adi)
        val sifreText = findViewById<EditText>(R.id.sifre)
        val sifreTekrarText = findViewById<EditText>(R.id.sifre_tekrar)
        val kayitOlmaButonu = findViewById<Button>(R.id.kayit_butonu)
        val girisEkrani = findViewById<TextView>(R.id.girisTextView)

        kayitOlmaButonu.setOnClickListener {
            val kullaniciAdi = kullaniciAdiText.text.toString()
            val sifre = sifreText.text.toString()
            val sifreTekrar = sifreTekrarText.text.toString()

            if (kullaniciAdi.isEmpty() || sifre.isEmpty() || sifreTekrar.isEmpty()) {
                Toast.makeText(this, "Lütfen tüm alanları doldurun.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (sifre != sifreTekrar) {
                Toast.makeText(this, "Şifreler eşleşmiyor.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(kullaniciAdi, sifre)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Kayıt başarıyla tamamlandı.", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, GirisEkrani::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Kayıt başarısız oldu: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        girisEkrani.setOnClickListener {
            val intent = Intent(this, GirisEkrani::class.java)
            startActivity(intent)
            finish()
        }
    }
}


// Kullanıcı model sınıfı
data class Kullanici(val kullaniciAdi: String, val sifre: String)