package com.example.giris_sayfasi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2

class AnasayfaFragment : Fragment() {

    private lateinit var viewPager1: ViewPager2
    private lateinit var viewPager2: ViewPager2
    private val viewPagerItemList1 = ArrayList<ViewPagerItem>()
    private val viewPagerItemList2 = ArrayList<ViewPagerItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_anasayfa, container, false)

        // ViewPager1 ve ViewPager2'yi başlat ve bağla
        viewPager1 = view.findViewById(R.id.viewpager1)
        viewPager2 = view.findViewById(R.id.viewpager2)

        // Veri listesi oluştur
        val images1 = arrayOf(
            R.drawable.konak,
            R.drawable.alsancak,
            R.drawable.hasanaga,
            R.drawable.kucukpark
        )

        val images2 = arrayOf(
            R.drawable.efes,
            R.drawable.asansor
        )

        val headings1 = arrayOf("Konak", "Alsancak" , "Hasanağa" , "Küçükpark")
        val headings2 = arrayOf("Efes Antik Kenti" , "Tarihi Asansör")

        val descriptions1 = arrayOf(
            getString(R.string.konakAciklama),
            getString(R.string.alsancakAcikalma),
            getString(R.string.hasanagaAciklama),
            getString(R.string.kucukparkAciklama)
        )

        val descriptions2 = arrayOf(
            getString(R.string.efesAciklama),
            getString(R.string.asansorAciklama)
        )

        for (i in images1.indices) {
            val item = ViewPagerItem(images1[i], headings1[i], descriptions1[i])
            viewPagerItemList1.add(item)
        }

        for (i in images2.indices){
            val item = ViewPagerItem(images2[i], headings2[i], descriptions2[i])
            viewPagerItemList2.add(item)
        }

        // Adapter bağlama

        viewPager1.adapter = VPAdapter(viewPagerItemList1) {

        }
        viewPager2.adapter = VPAdapter(viewPagerItemList2) { selectedItem ->
            mekanSayfasinaGonder(selectedItem)
        }

        // ViewPager1 ayarları
        viewPager1.clipToPadding = false
        viewPager1.clipChildren = false
        viewPager1.offscreenPageLimit = 2
        viewPager1.getChildAt(0).overScrollMode = View.OVER_SCROLL_NEVER

        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.offscreenPageLimit = 2
        viewPager2.getChildAt(0).overScrollMode = View.OVER_SCROLL_NEVER

        return view
    }

    private fun mekanSayfasinaGonder(selectedItem: ViewPagerItem){
        val mekanFragment = MekanFragment()
        val bundle = Bundle()

        bundle.putString("mekanAdi", selectedItem.heading)
        bundle.putString("mekanAciklama", selectedItem.description)
        bundle.putInt("mekanResmiID", selectedItem.imageID)
        mekanFragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.frameLayout , mekanFragment)
            .addToBackStack(null)
            .commit()
    }
}