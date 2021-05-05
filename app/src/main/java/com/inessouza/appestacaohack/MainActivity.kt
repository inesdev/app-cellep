package com.inessouza.appestacaohack

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.inessouza.appestacaohack.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = intent.getStringArrayExtra("INTENT_EMAIL")

        val sharedPrefs = getSharedPreferences(
            "cadastro_$email",
            Context.MODE_PRIVATE
        )

        // Recuperar dados no arquivo de preferências compartilhadas

        val nome = sharedPrefs.getString("NOME", "")
        val sobrenome = sharedPrefs.getString("SOBRENOME", "")
        val continente = sharedPrefs.getString("CONTINENTE", "")

        // Exibir os dados recuperados na tela
        binding.txvMainNome.text = "$nome $sobrenome"
        binding.txvMainEmail.text = email.toString()
        binding.txvMainContinente.text = continente

        binding.btnMainSair.setOnClickListener {
            val alert = AlertDialog.Builder(this)

            alert.setTitle("Atenção")
            // Definindo o corpo da mensagem
            alert.setMessage("Deseja realmente sair?")

            alert.setPositiveButton("Sair") {dialog, witch ->
                val mIntent = Intent(this, LoginActivity::class.java)
                startActivity(mIntent)
                finishAffinity()
            }

            // Definindo o rótulo do botão e aguardando o click
            alert.setNeutralButton("Não") { dialog, wich -> }

            // Desabilitar a possibilidade do usuário cancelar a caixa de diálogo
            // ao clicar fora dela, assim o usuário é
            // obrigado a interagir com os botões
            alert.setCancelable(false)

            alert.show()
        }

        binding.btnMainSite.setOnClickListener{
            val mIntent = Intent(this, WebActivity::class.java)
            startActivity(mIntent)
        }
    }
}