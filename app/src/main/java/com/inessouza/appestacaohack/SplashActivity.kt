package com.inessouza.appestacaohack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Executando instruções após um determinado tempo
        Handler(Looper.getMainLooper()).postDelayed({
            // Uma das utilidades da classe Intent é usar-la para abrir uma activity.
            // A classe Intent define de onde estamos para onde vamos.
            val mIntent = Intent(this, LoginActivity::class.java)
            //Método responsável por executar a Internet
            startActivity(mIntent)
            //Remover a tela da pilha de memória
            finish()
        }, 5000)
    }
}