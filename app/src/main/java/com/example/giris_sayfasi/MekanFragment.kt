package com.example.giris_sayfasi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment


class MekanFragment : Fragment(){

    private lateinit var googleMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mekan , container, false)

        val mekanResmiID = arguments?.getInt("mekanResmiID") ?: R.drawable.konak
        val mekanAciklama = arguments?.getString("mekanAciklama") ?: "Açıklama eksik"
        val mekanAdi = arguments?.getString("mekanAdi") ?: "Açıklama eksik"

        val mekanAdiText = view.findViewById<TextView>(R.id.mekanAdi)
        val mekanAciklamaText = view.findViewById<TextView>(R.id.mekanAciklama)
        val mekanResmi = view.findViewById<ImageView>(R.id.mekanResmi)

        mekanAdiText.text = mekanAdi
        mekanAciklamaText.text = mekanAciklama
        mekanResmi.setImageResource(mekanResmiID)

        return view
    }


}