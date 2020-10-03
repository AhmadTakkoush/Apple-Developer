package com.ahmadtakkoush.appledeveloper

import android.annotation.SuppressLint
import android.net.http.SslError
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.webkit.*
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    var mWebView: WebView? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater.inflate(R.layout.fragment_home, container, false)
        mWebView = v.findViewById(R.id.WebView) as WebView
        mWebView!!.loadUrl("https://developer.apple.com/forums")

        // Enable Javascript
        val webSettings: WebSettings = mWebView!!.settings
        webSettings.javaScriptEnabled = true

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView!!.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                spin_kit?.visibility = GONE
            }

            override fun onReceivedError(
                view: WebView,
                request: WebResourceRequest,
                error: WebResourceError
            ) {
                spin_kit?.visibility = GONE
            }

            override fun onReceivedHttpError(
                view: WebView, request: WebResourceRequest, errorResponse: WebResourceResponse
            ) {
                spin_kit?.visibility = GONE
            }

            override fun onReceivedSslError(
                view: WebView, handler: SslErrorHandler,
                error: SslError
            ) {
                spin_kit?.visibility = GONE
            }
        }
        return v
    }
}