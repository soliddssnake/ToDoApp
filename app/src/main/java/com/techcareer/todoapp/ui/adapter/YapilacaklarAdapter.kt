package com.techcareer.todoapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.techcareer.todoapp.R
import com.techcareer.todoapp.data.entity.Yapilacaklar
import com.techcareer.todoapp.databinding.CardTasarimBinding
import com.techcareer.todoapp.ui.fragment.AnasayfaFragmentDirections
import com.techcareer.todoapp.ui.viewmodel.AnasayfaFragmentViewModel
import com.techcareer.todoapp.util.gecisYap

class YapilacaklarAdapter(
    var mContext: Context, var yapilacaklarListesi: List<Yapilacaklar>, var viewModel: AnasayfaFragmentViewModel
) : RecyclerView.Adapter<YapilacaklarAdapter.CardTasarimTutucu>() {
    inner class CardTasarimTutucu(tasarim: CardTasarimBinding) : RecyclerView.ViewHolder(tasarim.root) {
        var tasarim: CardTasarimBinding

        init {
            this.tasarim = tasarim
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim: CardTasarimBinding = DataBindingUtil.inflate(layoutInflater, R.layout.card_tasarim, parent, false)
        return CardTasarimTutucu(tasarim)
    }

    override fun getItemCount(): Int {
        return yapilacaklarListesi.size
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val yapilacak = yapilacaklarListesi[position]
        val t = holder.tasarim
        t.yapilacakNesnesi = yapilacak
        t.imageViewSilResim.setOnClickListener {
            Snackbar.make(it, "${yapilacak.yapilacak_is} silinsin mi?", Snackbar.LENGTH_LONG).setAction("EVET") {
                viewModel.sil(yapilacak.yapilacak_id)
            }.show()
        }

        t.satirCard.setOnClickListener {
            val gecis = AnasayfaFragmentDirections.detayGecis(yapilacak = yapilacak)
            Navigation.gecisYap(it,gecis)
        }
    }
}