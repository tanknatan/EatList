package com.example.myapplication.presentation.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.domain.EatItem

class EatItemActivity : AppCompatActivity(), EatItemFragment.OnEditingFinishedListener {


    private var screenMode = MODE_UNKNOWN
    private var eatItemId = EatItem.UNDEFINED_ID


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eat_item)
        parseIntent()
        if (savedInstanceState == null) {
            launchRightMode()
        }
    }



    private fun launchRightMode() {
        val fragment = when (screenMode) {
            MODE_EDIT -> EatItemFragment.newInstanceEditItem(eatItemId)
            MODE_ADD -> EatItemFragment.newInstanceAddItem()
            else -> throw RuntimeException("Unknown screen mode $screenMode")
        }
    supportFragmentManager.beginTransaction()
        .replace(R.id.eat_item_container, fragment)
        .commit()
    }



    fun parseIntent() {
        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
            throw RuntimeException("Screen mode not found")

        }
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if (mode != MODE_ADD && mode != MODE_EDIT) {
            throw RuntimeException("Screen mode unknown")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!intent.hasExtra(EXTRA_EAT_ITEM_ID)) {
                throw RuntimeException("Eat item id not found")
            }
            eatItemId = intent.getIntExtra(EXTRA_EAT_ITEM_ID, EatItem.UNDEFINED_ID)

        }
    }



    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val MODE_ADD = "mode_add"
        private const val MODE_EDIT = "mode_edit"
        private const val EXTRA_EAT_ITEM_ID = "extra_eat_item_id"
        private const val MODE_UNKNOWN = ""

        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, EatItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditItem(context: Context, eatItemId: Int): Intent {
            val intent = Intent(context, EatItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_EAT_ITEM_ID, eatItemId)
            return intent
        }
    }

    override fun onEditFinished() {
        finish()
    }
}