package com.amazing.portfolio.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.transition.Fade
import android.transition.TransitionInflater
import android.transition.TransitionSet
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import com.amazing.portfolio.R
import com.amazing.portfolio.ui.adapters.ItemAdapter
import com.transitionseverywhere.extra.Scale
import kotlinx.android.synthetic.main.home_fragment.*
import android.widget.ImageView.ScaleType
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy.FIT_CENTER

import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.TransitionManager


class HomePageFragment : BaseFragment() {
    private val MOVE_DEFAULT_TIME: Long = 1000
    private val FADE_DEFAULT_TIME: Long = 300
    private val mDelayedTransactionHandler = Handler()
    private val mRunnable = Runnable {
        val nextFragment = LandingScreenFragment()
        // 2. Shared Elements Transition

        home().setFragment(LandingScreenFragment())
    }

    private val itemAdapter by lazy {
        ItemAdapter { position: Int, item: Item ->
            item_list.smoothScrollToPosition(position)

           // mDelayedTransactionHandler.postDelayed(mRunnable, 1000);

        }
    }
    private val possibleItems = listOf(
        Item("Airplanes", R.drawable.ic_launcher_foreground),
        Item("Cars", R.drawable.ic_launcher_foreground),
        Item("Food", R.drawable.ic_launcher_foreground),
        Item("Gas", R.drawable.ic_launcher_foreground),
        Item("Home", R.drawable.ic_launcher_foreground)
    )
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.home_fragment, container, false)
        return v
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    fun initUI() {
        val displayMetrics = DisplayMetrics()
        activity!!.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics)
        val screenWidth = displayMetrics.widthPixels
        item_list.initialize(itemAdapter)
        item_list.setViewsToChangeColor(listOf(R.id.list_item_background,R.id.list_item_icon))
        itemAdapter.context = activity!!
        itemAdapter.setItems(getLargeListOfItems())


    }
    private fun getLargeListOfItems(): List<Item> {
        val items = mutableListOf<Item>()
        (0..40).map { items.add(possibleItems.random()) }
        return items
    }
}
data class Item(
    val title: String,
    @DrawableRes val icon: Int
)