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
import kotlinx.android.synthetic.main.home_fragment.*
import android.widget.ImageView.ScaleType
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy.FIT_CENTER

import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.TransitionManager
import android.view.animation.AccelerateDecelerateInterpolator
import android.R.attr.duration
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.view.animation.AnimationSet
import android.R.attr.duration
import android.view.animation.TranslateAnimation
import kotlinx.android.synthetic.main.fragment_app_list.*


class AppListFragment : BaseFragment() {

    private val itemAdapter by lazy {
        ItemAdapter { position: Int, item: Item ->
            item_list.smoothScrollToPosition(position)
            val appDetailFragment = AppDetailFragment()
            home().setFragment(appDetailFragment)
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
        v = inflater.inflate(R.layout.fragment_app_list, container, false)
        return v
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