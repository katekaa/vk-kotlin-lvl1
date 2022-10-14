package com.example.homework1.view

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework1.App.Companion.appContext
import com.example.homework1.ItemListAdapter
import com.example.homework1.databinding.FragmentListBinding
import com.example.homework1.model.ResourcesProvider
import com.example.homework1.viewmodel.ItemViewModel;
import com.example.homework1.viewmodel.ItemViewModelFactory

class ListFragment : Fragment() {

    private val viewModel: ItemViewModel by viewModels {
        ItemViewModelFactory(
            ResourcesProvider(
                appContext
            ), this
        )
    }

    private var orientIsPortrait = true
    private lateinit var recycler: RecyclerView
    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater)
        recycler = binding.recyclerView
        orientIsPortrait =
            (activity?.resources?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT)
        chooseLayout()

        val fab = binding.floatingActionButton

        recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy < 0) {
                    fab.hide();
                } else if (dy > 0) {
                    fab.show();
                }
            }

        })

        val adapter = ItemListAdapter()
        viewModel.list.observe(viewLifecycleOwner) { adapter.setData(it) }
        recycler.adapter = adapter

        binding.floatingActionButton.setOnClickListener {
            viewModel.addElem(0)

            viewModel.list.value?.let { it1 ->
                viewModel.saveCurrentList(it1)
                recycler.scrollToPosition(it1.size - 1)
            }
            adapter.notifyDataSetChanged()
        }

        return binding.root
    }

    private fun chooseLayout() {
        recycler.layoutManager = if (orientIsPortrait) GridLayoutManager(activity, 3)
        else GridLayoutManager(activity, 4)
    }
}
