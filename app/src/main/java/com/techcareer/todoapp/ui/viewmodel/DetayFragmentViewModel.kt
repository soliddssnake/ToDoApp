package com.techcareer.todoapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.techcareer.todoapp.data.repo.YapilacaklarDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetayFragmentViewModel @Inject constructor(var yrepo: YapilacaklarDaoRepository) : ViewModel() {

    fun guncelle(yapilacak_id: Int, yapilacak_is: String) {
        yrepo.yapilacakGuncelle(yapilacak_id, yapilacak_is)
    }

}