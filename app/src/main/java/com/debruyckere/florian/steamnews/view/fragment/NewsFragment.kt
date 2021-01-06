package com.debruyckere.florian.steamnews.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModelProvider
import com.debruyckere.florian.steamnews.R
import com.debruyckere.florian.steamnews.viewmodel.NewsViewModel

class NewsFragment : Fragment() {

    private var mViewModel: NewsViewModel? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)
        val bundleUrl = requireArguments().get("newsUrl").toString()

        mViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        mViewModel!!.setWeb(bundleUrl)

        val webView : WebView = view.findViewById(R.id.web_news)
        webView.webViewClient = WebViewClient()
        val settings = webView.settings
        settings.javaScriptEnabled = true


        mViewModel!!.getWeb().observe(viewLifecycleOwner){ url: String ->
            run{
                webView.loadUrl(url)
            }
        }

        // Inflate the layout for this fragment
        return view
    }
}