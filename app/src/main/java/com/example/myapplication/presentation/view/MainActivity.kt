package com.example.myapplication.presentation.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.presentation.viewModel.MainViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(),EatItemFragment.OnEditingFinishedListener {
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: EatListAdapter
    private var eatItemContainer: FragmentContainerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        eatItemContainer = findViewById(R.id.eat_item_container)
        setupRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.eatList.observe(this) {
            adapter.submitList(it)
        }
        val buttonAddItem = findViewById<FloatingActionButton>(R.id.button_add_shop_item)
        buttonAddItem.setOnClickListener {
            if (isOnePaneMode()) {
                val intent = EatItemActivity.newIntentAddItem(this)
                startActivity(intent)
            } else {
                launchFragment(EatItemFragment.newInstanceAddItem())
            }
        }
    }

    private fun isOnePaneMode(): Boolean {
        return eatItemContainer == null
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.eat_item_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setupRecyclerView() {
        adapter = EatListAdapter()
        val recyclerView =
            findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.recycledViewPool.setMaxRecycledViews(
            EatListAdapter.ENABLED,
            EatListAdapter.MAX_POOL_SIZE
        )
        recyclerView.recycledViewPool.setMaxRecycledViews(
            EatListAdapter.DISABLED,
            EatListAdapter.MAX_POOL_SIZE
        )
        setupLongClickListener()

        setupClickListener()

        setupSwipeListener(recyclerView)
    }


    private fun setupSwipeListener(recyclerView: RecyclerView?) {
        val callback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapter.currentList[viewHolder.adapterPosition]
                viewModel.deletEatItem(item)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun setupClickListener() {
        adapter.onEatItemClickListener = {
            if (isOnePaneMode()) {
                val intent = EatItemActivity.newIntentEditItem(this, it.id)
                startActivity(intent)
            } else {
                launchFragment(EatItemFragment.newInstanceEditItem(it.id))

            }
        }
    }

    private fun setupLongClickListener() {
        adapter.onEatItemLongClickListener = {
            viewModel.changeEnableState(it)
        }
    }

    override fun onEditFinished() {
        Toast.makeText(this,"Seccess",Toast.LENGTH_SHORT).show()
        supportFragmentManager.popBackStack()
    }
}

