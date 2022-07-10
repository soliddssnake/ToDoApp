package com.techcareer.todoapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.techcareer.todoapp.data.entity.Yapilacaklar
import com.techcareer.todoapp.data.repo.YapilacaklarDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnasayfaFragmentViewModel @Inject constructor(var yrepo: YapilacaklarDaoRepository) : ViewModel() {
    var yapilacaklarListesi = MutableLiveData<List<Yapilacaklar>>()

    init {
        yapilacaklariYukle()
        yapilacaklarListesi = yrepo.yapilacaklariGetir()
    }

    fun ara(aramaKelimesi: String) {
        yrepo.yapilacakAra(aramaKelimesi)
    }

    fun sil(yapilacak_id: Int) {
        yrepo.yapilacakSil(yapilacak_id)
    }

    fun yapilacaklariYukle() {
        yrepo.tumYapilacaklariAl()
    }
}