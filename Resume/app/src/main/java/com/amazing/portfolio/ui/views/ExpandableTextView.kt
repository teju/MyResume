package com.amazing.portfolio.ui.views

import android.animation.ObjectAnimator
import android.content.Context
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.AttributeSet
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import com.amazing.portfolio.R

class ExpandableTextView : AppCompatTextView {
    private var textView: TextView
    private var maxLine = 3
    private var isViewMore = true

    constructor(context: Context?) : super(context) {
        Companion.context = context
        textView = this
        initViews()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context,
        attrs
    ) {
        Companion.context = context
        textView = this
        initViews()
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        Companion.context = context
        textView = this
        initViews()
    }

    fun initViews() {
        if (textView.text.toString().isEmpty()) {
            return
        }
        if (textView.tag == null) {
            textView.tag = textView.text
        }
        val vto = textView.viewTreeObserver
        vto.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val text: String
                var expandText = "See "
                val lineEndIndex: Int
                val obs = textView.viewTreeObserver
                obs.removeGlobalOnLayoutListener(this)
                val lineCount = textView.layout.lineCount
                expandText += if (isViewMore) "More" else "Less"
                if (lineCount <= maxLine) {
                    lineEndIndex =
                        textView.layout.getLineEnd(textView.layout.lineCount - 1)
                    text = textView.text.subSequence(0, lineEndIndex).toString()
                } else if (isViewMore && maxLine > 0 && textView.lineCount >= maxLine) {
                    lineEndIndex = textView.layout.getLineEnd(maxLine - 1)
                    text = textView.text.subSequence(0, lineEndIndex - expandText.length + 1)
                        .toString() + " " + expandText
                    val animation = ObjectAnimator.ofInt(textView, "maxLines", 3)
                    animation.setDuration(100).start()
                } else {
                    lineEndIndex =
                        textView.layout.getLineEnd(textView.layout.lineCount - 1)
                    text = textView.text.subSequence(0, lineEndIndex)
                        .toString() + " " + expandText
                    val animation = ObjectAnimator.ofInt(textView, "maxLines", 100)
                    animation.setDuration(100).start()
                }
                textView.text = text
                textView.movementMethod = LinkMovementMethod.getInstance()
                if (lineCount > maxLine)
                    textView.setText(
                    addClickablePartTextViewResizable(expandText),
                    BufferType.SPANNABLE
                )
                textView.isSelected = true
            }
        })
    }

    private fun addClickablePartTextViewResizable(expandText: String): SpannableStringBuilder {
        val string = textView.text.toString()
        val expandedStringBuilder = SpannableStringBuilder(string)
        if (string.contains(expandText)) {
            expandedStringBuilder.setSpan(object : ClickableSpan() {
                override fun onClick(widget: View) {
                    textView.layoutParams = textView.layoutParams
                    textView.setText(textView.tag.toString(), BufferType.SPANNABLE)
                    textView.invalidate()
                    maxLine = if (isViewMore) -1 else 3
                    isViewMore = !isViewMore
                    initViews()
                }

                override fun updateDrawState(ds: TextPaint) {
                    ds.isUnderlineText = true
                    ds.color = Companion.context!!.resources
                        .getColor(R.color.colorAccent)
                }
            }, string.indexOf(expandText), string.length, 0)
        }
        return expandedStringBuilder
    }

    companion object {
        private var context: Context? = null
    }
}