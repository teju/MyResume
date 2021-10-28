package com.amazing.portfolio.ui.fragments.features_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.amazing.portfolio.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail.*


class FeatureOneFragmentDetail : Fragment() {
    var url: String = ""
    var weburl: String = "https://www.tutorialspoint.com/android/android_webview_layout.htm"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(activity!!)
            .load(url)
            .into(header)
        wv1.setWebViewClient(MyBrowser())

        wv1.getSettings().setLoadsImagesAutomatically(true)
        wv1.getSettings().setJavaScriptEnabled(true)
        wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY)
        wv1.loadUrl(weburl)
    }

    private class MyBrowser : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
            view.loadUrl(url)
            return true
        }
    }
}