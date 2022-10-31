package com.example.homework1.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework1.ItemListAdapter
import com.example.homework1.R
import com.example.homework1.databinding.FragmentListBinding
import com.example.homework1.viewmodel.ItemViewModel
import com.example.homework1.viewmodel.ItemViewModelFactory

class ListFragment : Fragment() {

    private val viewModel: ItemViewModel by viewModels {
        ItemViewModelFactory(this)
    }

    private lateinit var recycler: RecyclerView
    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater)
        recycler = binding.recyclerView
        chooseLayout()

        val fab = binding.floatingActionButton

        recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy < 0) {
                    fab.hide()
                } else if (dy > 0) {
                    fab.show()
                }
            }
        })

        val adapter = ItemListAdapter()
        viewModel.list.observe(viewLifecycleOwner) { adapter.setData(it) }
        recycler.adapter = adapter

        binding.floatingActionButton.setOnClickListener {
            viewModel.addElem(0)
            viewModel.list.value?.let { it1 ->
                recycler.scrollToPosition(it1.size - 1)
                adapter.notifyItemInserted(it1.size - 1)
            }
        }
        return binding.root
    }

    private fun chooseLayout() {
        recycler.layoutManager =
            GridLayoutManager(
                activity,
                requireContext().resources.getInteger(R.integer.columns_count)
            )
    }
}
