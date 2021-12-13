package com.cookandroid.lotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_search.*
import org.jsoup.Jsoup
import java.io.IOException
import java.lang.Exception

class SearchActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        var personalKey = intent.getStringExtra("PersonalKey")
        textView = findViewById(R.id.textView)
        textView.text = personalKey


        var myWebView: WebView = findViewById(R.id.webview)
        myWebView.webViewClient = WebViewClient()
        myWebView.loadUrl("https://www.dhlottery.co.kr/common.do?method=main")



        btnClose.setOnClickListener {
            finish()
        }
    }

    }
