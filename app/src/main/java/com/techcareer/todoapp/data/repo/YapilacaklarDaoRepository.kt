package com.techcareer.todoapp.data.repo

import androidx.lifecycle.MutableLiveData
import com.techcareer.todoapp.data.entity.Yapilacaklar
import com.techcareer.todoapp.room.YapilacaklarDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class YapilacaklarDaoRepository(var ydao: YapilacaklarDao) {

    var yapilacaklarListesi: MutableLiveData<List<Yapilacaklar>>

    init {
        yapilacaklarListesi = MutableLiveData()
    }

    fun yapilacaklariGetir(): MutableLiveData<List<Yapilacaklar>> {
        return yapilacaklarListesi
    }

    fun yapilacakKayit(yapilacak_is:String){
        val job = CoroutineScope(Dispatchers.Main).launch {
            val yeniYapilacak = Yapilacaklar(0,yapilacak_is)
            ydao.yapilacakEkle(yeniYapilacak)
        }
    }

    fun yapilacakSil(yapilacak_id:Int){
        val job = CoroutineScope(Dispatchers.Main).launch {
            val silinecek = Yapilacaklar(yapilacak_id,"")
            ydao.yapilacakSil(silinecek)
            tumYapilacaklariAl()
        }
    }

    fun yapilacakGuncelle(yapilacak_id: Int,yapilacak_is: String){
        val job = CoroutineScope(Dispatchers.Main).launch {
            val guncellenecek = Yapilacaklar(yapilacak_id, yapilacak_is)
            ydao.yapilacakGuncelle(guncellenecek)
        }
    }

    fun yapilacakAra(aramaKelimesi: String){
        val job = CoroutineScope(Dispatchers.Main).launch {
            yapilacaklarListesi.value = ydao.yapilacaklarArama(aramaKelimesi)
        }
    }

    fun tumYapilacaklariAl() {
        val job = CoroutineScope(Dispatchers.Main).launch {
            yapilacaklarListesi.value = ydao.tumYapilacaklar()
        }
    }

}