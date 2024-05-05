package com.example.firstapp_activity

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firstapp_activity.databinding.ActivityMainBinding

data class Pessoa(val nome: String, val numeroTelefone: String)

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var listaPessoas: ListView
    private lateinit var nomePessoaEditText: EditText
    private lateinit var numeroTelefoneEditText: EditText
    private lateinit var botaoAdicionarPessoa: Button

    private val pessoas = mutableListOf<Pessoa>()
    private lateinit var adapter: ArrayAdapter<Pessoa>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize UI elements using their IDs from the layout file
        listaPessoas = findViewById(R.id.lista_pessoas)
        nomePessoaEditText = findViewById(R.id.nome_pessoa)
        numeroTelefoneEditText = findViewById(R.id.numero_telefone)
        botaoAdicionarPessoa = findViewById(R.id.botao_adicionar_pessoa)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, pessoas)
        listaPessoas.adapter = adapter

        botaoAdicionarPessoa.setOnClickListener {
            val nome = nomePessoaEditText.text.toString()
            val numeroTelefone = numeroTelefoneEditText.text.toString()

            if (nome.isNotEmpty() && numeroTelefone.isNotEmpty()) {
                val pessoa = Pessoa(nome, numeroTelefone)
                pessoas.add(pessoa)
                adapter.notifyDataSetChanged()

                nomePessoaEditText.text.clear()
                numeroTelefoneEditText.text.clear()
            }
        }

        // Your existing code for handling window insets (optional)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
