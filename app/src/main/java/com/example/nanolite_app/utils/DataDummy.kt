package com.example.nanolite_app.utils

import com.example.nanolite_app.domain.model.Recycler
import com.example.nanolite_app.domain.model.Waste

object DataDummy {

    fun getScanningDummy(): ArrayList<Waste>{
        val list = ArrayList<Waste>()

        list.add(Waste(
                0,
                "rahmanarief99@gmail.com",
                "plastic",
                "27-05-2021",
                "content://media/external/images/media/18620",
                "anorganik"
        ))

        list.add(Waste(
                1,
            "rahmanarief99@gmail.com",
            "cardboard",
            "27-05-2021",
            "content://media/external/images/media/18429",
            "anorganik"
        ))
        return list
    }

    fun getRecommendation(): ArrayList<Recycler>{
        val list = ArrayList<Recycler>()

        list.add(Recycler(
            "Plastic",
            "https://www.tokomesin.com/berikut-cara-membuat-daur-ulang-botol-plastik-yang-bisa-anda-coba.html"
        ))

        list.add(Recycler(
            "Cardboard",
            "https://www.rimma.co/52183/inspiration/tips/daripada-dibuang-ubah-kardus-bekas-menjadi-4-barang-bermanfaat-ini/"
        ))

        list.add(Recycler(
            "Cardboard",
            "https://web.archive.org/web/20130617101216/http://ohoh-blog.blogspot.mx:80/2013/06/diy-cardboard-photo-frame.html"
        ))



        return list
    }
}