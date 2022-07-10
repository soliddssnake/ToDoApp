package com.techcareer.todoapp.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.techcareer.todoapp.R
import com.techcareer.todoapp.databinding.FragmentAnasayfaBinding
import com.techcareer.todoapp.ui.adapter.YapilacaklarAdapter
import com.techcareer.todoapp.ui.viewmodel.AnasayfaFragmentViewModel
import com.techcareer.todoapp.util.gecisYap
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnasayfaFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var tasarim: FragmentAnasayfaBinding
    private lateinit var viewModel: AnasayfaFragmentViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_anasayfa, container, false)
        tasarim.anasayfaFragment = this

        tasarim.anasayfaToolbarBaslik = "Yapılacaklar"
        (activity as AppCompatActivity).setSupportActionBar(tasarim.toolbarAnasayfa)

        viewModel.yapilacaklarListesi.observe(viewLifecycleOwner){
            val adapter = YapilacaklarAdapter(requireContext(),it,viewModel)
            tasarim.yapilacaklarAdapter = adapter
        }

        return tasarim.root
    }

    fun fabTikla(v:View){
        Navigation.gecisYap(v,R.id.kayitGecis)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val tempViewModel: AnasayfaFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
        val item = menu.findItem(R.id.action_ara)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        viewModel.ara(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        viewModel.ara(newText)
        return true
    }

    override fun onResume() {
        super.onResume()
        viewModel.yapilacaklariYukle()
    }

}