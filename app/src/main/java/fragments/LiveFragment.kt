package com.ahmadtakkoush.appledeveloper

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment

class LiveFragment : Fragment() {

    var mWebView: WebView? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater.inflate(R.layout.fragment_live, container, false)
        mWebView = v.findViewById(R.id.WebView) as WebView
        mWebView!!.loadUrl("https://apple.com/apple-events/event-stream")


        // Enable Javascript
        val webSettings: WebSettings = mWebView!!.settings
        webSettings.javaScriptEnabled = true

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView!!.webViewClient = WebViewClient()

        return v
    }

}