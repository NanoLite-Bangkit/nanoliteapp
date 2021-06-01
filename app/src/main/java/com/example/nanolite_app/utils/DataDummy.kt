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
                "Glass",
                "https://pelajaricaranya.blogspot.com/2017/02/cara-melebur-limbah-kaca-bekas-dan.html"
        ))

        list.add(Recycler(
                "Glass",
                "https://bisnisukm.com/sulap-sampah-kaca-menjadi-bisnis-daur-ulang.html"
        ))

        list.add(Recycler(
                "Glass",
                "https://alimudinharahap.wordpress.com/2014/10/16/daur-ulang-limbah-kaca/"
        ))

        list.add(Recycler(
            "Plastic",
            "https://www.tokomesin.com/berikut-cara-membuat-daur-ulang-botol-plastik-yang-bisa-anda-coba.html"
        ))

        list.add(Recycler(
                "Plastic",
                "https://www.arahenvironmental.com/bagaimana-cara-daur-ulang-plastik/"
        ))

        list.add(Recycler(
                "Plastic",
                "https://daihatsu.co.id/tips-and-event/tips-sahabat/detail-content/-cara-mendaur-ulang-sampah-plastik-dan-tahapannya/"
        ))

        list.add(Recycler(
                "Plastic",
                "https://www.99.co/blog/indonesia/daur-ulang-plastik/"
        ))

        list.add(Recycler(
            "Cardboard",
            "https://www.rimma.co/52183/inspiration/tips/daripada-dibuang-ubah-kardus-bekas-menjadi-4-barang-bermanfaat-ini/"
        ))

        list.add(Recycler(
            "Cardboard",
            "https://web.archive.org/web/20130617101216/http://ohoh-blog.blogspot.mx:80/2013/06/diy-cardboard-photo-frame.html"
        ))

        list.add(Recycler(
                "Cardboard",
                "https://kumparan.com/kumparansains/mengenal-jenis-kardus-dan-cara-mendaur-ulang-kardus-1533784667600045024/full"
        ))

        list.add(Recycler(
                "Cardboard",
                "https://www.ruparupa.com/blog/kerajinan-tangan-dari-kardus/"
        ))

        list.add(Recycler(
                "Metal",
                "https://pemol.id/teknik-daur-ulang-metal-yang-berpeluang-bisnis/"
        ))

        list.add(Recycler(
                "Metal",
                "https://hijauku.com/2013/04/28/daur-ulang-metal-selamatkan-lingkungan/"
        ))

        list.add(Recycler(
                "Paper",
                "https://www.yukepo.com/hiburan/tips/yuk-bikin-12-ide-daur-ulang-kertas-yang-nggak-terpakai-daripada-numpuk-terus/"
        ))

        list.add(Recycler(
                "Paper",
                "https://pemol.id/cara-daur-ulang-kertas-yang-bernilai-jual/"
        ))

        list.add(Recycler(
                "Paper",
                "https://id.wikihow.com/Membuat-Kertas-Daur-Ulang-dari-Kertas-Bekas"
        ))

        list.add(Recycler(
                "Trash",
                "http://www.funinthemaking.net/10-contoh-recycle-yang-mudah-dilakukan/"
        ))

        list.add(Recycler(
                "Trash",
                "https://www.rumah.com/panduan-properti/8-ide-daur-ulang-terbaik-yang-bisa-jadi-uang-tambahan-untuk-anda-26255"
        ))

        return list
    }
}