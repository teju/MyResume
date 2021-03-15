package com.amazing.portfolio.ui.adapters

import android.content.Context
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amazing.portfolio.R
import java.lang.Exception

class HorizontalCarouselRecyclerView(
    context: Context,
    attrs: AttributeSet
) : RecyclerView(context, attrs) {

    private var firstCompletelyVisibleItemPosition: Int = 0
    private var viewsToChangeColor: List<Int> = listOf()
    private val activeColor by lazy { ContextCompat.getColor(context, R.color.theme_orange) }
    private val inactiveColor by lazy { ContextCompat.getColor(context, R.color.soft_transparent_grey) }
    fun initialize(newAdapter: ItemAdapter) {
        layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
        newAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                post {
                    try {
                        val sidePadding = (width / 2) - (getChildAt(0).width / 2)
                        //setPadding(sidePadding, 100, sidePadding, 100)
                        //scrollToPosition(0)

                        addOnScrollListener(object : OnScrollListener() {
                            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                                super.onScrolled(recyclerView, dx, dy)

                                try {
                                    firstCompletelyVisibleItemPosition =
                                        (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                                } catch (e: Exception) {

                                }
                                onScrollChanged()
                            }
                        })
                    } catch (e:Exception){

                    }
                }
            }
        })
        adapter = newAdapter
    }

    fun setViewsToChangeColor(viewIds: List<Int>) {
        viewsToChangeColor = viewIds
    }

    private fun onScrollChanged() {
        post {
            (0 until childCount).forEach { position ->
                val child = getChildAt(position)
                val childCenterX = (child.left  + child.right) / 2
                val scaleValue = getGaussianScale(childCenterX, 1f, 0.5f, 500.toDouble())
                child.scaleX = scaleValue - 0.5f
                child.scaleY = scaleValue - 0.4f
                colorView(child, scaleValue,position)
            }
        }
    }

    private fun colorView(
        child: View,
        scaleValue: Float,
        position: Int
    ) {
        val saturationPercent = (scaleValue + 1) / 1f

        val alphaPercent = scaleValue / 2f
        val matrix = ColorMatrix()
        matrix.setSaturation(saturationPercent)

        viewsToChangeColor.forEach { viewId ->
            val viewToChangeColor = child.findViewById<View>(viewId)
            when (viewToChangeColor) {

                is RelativeLayout -> {
                    if(alphaPercent >= 0.7) {
                        viewToChangeColor.alpha = 1.0f
                    } else {
                        viewToChangeColor.alpha = 0.5f
                    }
                }
            }
        }
    }

    private fun getGaussianScale(
        childCenterX: Int,
        minScaleOffest: Float,
        scaleFactor: Float,
        spreadFactor: Double
    ): Float {
        val recyclerCenterX = (left + right) / 2
        return (Math.pow(
            Math.E,
            -Math.pow(childCenterX - recyclerCenterX.toDouble(), 2.toDouble()) / (2 * Math.pow(
                spreadFactor,
                2.toDouble()
            ))
        ) * scaleFactor + minScaleOffest).toFloat()
    }

}