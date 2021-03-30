package com.example.insights

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.Toast

class pdfViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_view)
        //Toast.makeText(this,"Clicked", Toast.LENGTH_SHORT).show()
        val webView: WebView=findViewById(R.id.webView)
        webView.loadUrl("https://www.google.co.in/")
    }
}