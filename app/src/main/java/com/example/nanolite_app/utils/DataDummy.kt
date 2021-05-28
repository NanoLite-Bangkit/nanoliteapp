package com.example.nanolite_app.utils

import com.example.nanolite_app.domain.model.Recycler
import com.example.nanolite_app.domain.model.Waste

object DataDummy {

    fun getScanningDummy(): ArrayList<Waste>{
        val list = ArrayList<Waste>()

        list.add(Waste(
                "rahmanarief99@gmail.com",
                "arief",
                "botol",
                "27-05-2021",
                "content://com.android.providers.media.documents/document/image%3A18418",
                "plastic"
        ))

        list.add(Waste(
            "rahmanarief99@gmail.com",
            "arief",
            "kardus",
            "27-05-2021",
            "content://com.android.providers.media.documents/document/image%3A18429",
            "cardboard"
        ))
        return list
    }

    fun getRecommendation(): ArrayList<Recycler>{
        val list = ArrayList<Recycler>()

        list.add(Recycler(
            "plastic",
            "https://www.tokomesin.com/berikut-cara-membuat-daur-ulang-botol-plastik-yang-bisa-anda-coba.html"
        ))

        list.add(Recycler(
            "cardboard",
            "https://www.rimma.co/52183/inspiration/tips/daripada-dibuang-ubah-kardus-bekas-menjadi-4-barang-bermanfaat-ini/"
        ))

        list.add(Recycler(
            "cardboard",
            "https://web.archive.org/web/20130617101216/http://ohoh-blog.blogspot.mx:80/2013/06/diy-cardboard-photo-frame.html"
        ))



        return list
    }
}