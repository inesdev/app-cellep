package com.inessouza.appestacaohack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.inessouza.appestacaohack.databinding.ActivityWebBinding

class WebActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Habilitando a execução do JavaScript
        binding.wbvWeb.settings.javaScriptEnabled = true

        //Carregar um endereço web

        binding.wbvWeb.loadUrl("https://br.cellep.com/estacaohack")

        binding.wbvWeb.webViewClient = WebViewClient()
    }
}