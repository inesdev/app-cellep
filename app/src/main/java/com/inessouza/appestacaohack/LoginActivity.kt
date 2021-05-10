package com.inessouza.appestacaohack

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.inessouza.appestacaohack.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    // Técnica utilizada para inicializar uma variável sem atribuir um valor, somente o tipo
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Quando o botão for clicado, faça
        binding.btnLoginEntrar.setOnClickListener {
            // Aqui os dados digitados são capturados e salvos em variáveis imutáveis
            val email = binding.edtLoginEmail.text.toString().trim().toLowerCase()
            val senha = binding.edtLoginSenha.text.toString().trim()

            if(email.isEmpty()){
                binding.edtLoginEmail.error = "Campo obrigatório!"
                binding.edtLoginEmail.requestFocus()
            } else if(senha.isEmpty()) {
                binding.edtLoginSenha.error = "Campo obrigatório!"
                binding.edtLoginSenha.requestFocus()
            } else {
                // Acesso ao arquivo de preferências compartilhadas
                val sharedPrefs = getSharedPreferences(
                        "Cadastro_$email",
                        Context.MODE_PRIVATE
                )
                // Recuperar os dados no arquivo através das chaves criadas
                val emailPrefs = sharedPrefs.getString("Email", "")
                val senhaPrefs = sharedPrefs.getString("Senha", "")

                if(email == emailPrefs && senha == senhaPrefs) {
                    Toast.makeText(this, "Usuário Logado", Toast.LENGTH_LONG).show()

                    val mIntent = Intent(this, MainActivity::class.java)
                    // Passando o E-mail via Intent para a MainActivity
                    mIntent.putExtra("INTENT_EMAIL", email)
                    startActivity(mIntent)

                    finish()

                } else {
                    Toast.makeText(this, "Usuário ou Senha inválidos", Toast.LENGTH_LONG).show()
                }
            }
        }
        binding.btnLoginCadastrar.setOnClickListener {
            val mIntent = Intent(this, CadastroActivity::class.java)
            startActivity(mIntent)
        }
    }
}