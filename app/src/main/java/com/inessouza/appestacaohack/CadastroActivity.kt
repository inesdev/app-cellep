package com.inessouza.appestacaohack

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.inessouza.appestacaohack.databinding.ActivityCadastroBinding

class CadastroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)
            // Criando uma lista de opções para o Spinner
        val listaContinentes = arrayListOf("Continente", "África", "Antártida", "América", "Ásia",
            "Europa", "Oceania")
        val spinnerAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            listaContinentes
        )

        binding.spnCadastroContinente.adapter = spinnerAdapter

        binding.btnCadastroCadastrar.setOnClickListener {
            val nome = binding.edtCadastroNome.text.toString().trim()
            val sobrenome = binding.edtCadastroSobrenome.text.toString().trim()
            val email = binding.edtCadastroEmail.text.toString().trim()
            val senha = binding.edtCadastroSenha.text.toString().trim()
            val continente = binding.spnCadastroContinente.selectedItem.toString()

            // Aqui os campos do formulário são validados

            if(nome.isEmpty() || sobrenome.isEmpty() || email.isEmpty() || senha.isEmpty() ||
                    continente == listaContinentes[0]) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show()
            } else {

                // Criação de uma referência a um arquivo de preferências compartilhadas
                val sharedPrefs = getSharedPreferences(
                    "cadastro_$email",
                    Context.MODE_PRIVATE
                )
                // Aqui a referência passa a ser editável
                val editPrefs = sharedPrefs.edit()

                // Aqui os dados são preparados para serem salvos no arquivo
                // Os dados são salvos no formato chave -> valor
                editPrefs.putString("NOME", nome)
                editPrefs.putString("SOBRENOME", sobrenome)
                editPrefs.putString("EMAIL", email)
                editPrefs.putString("SENHA", senha)
                editPrefs.putString("CONTINENTE", continente)

                // Aqui os dados são salvos no arquivo
                // O método apply realiza o processo de save
                editPrefs.apply()

                Toast.makeText(this, "Usuário cadastrado com sucesso", Toast.LENGTH_LONG).show()

                // Passagem de dados entre activities
                // O email será utilizado pela MainActivity para acessar o arquivo de preferências
                val mIntent = Intent(this, MainActivity::class.java)
                mIntent.putExtra("INTENT_EMAIL", email)
                startActivity(mIntent)

                // Este método remove todas as activities do empilhamento
                finishAffinity()
            }
        }
    }
}