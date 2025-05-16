package com.example.investeasy

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.investeasy.databinding.ActivityResultadoBinding

class ResultadoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultadoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityResultadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val aporte = intent.getStringExtra("aporte") ?: ""
        val meses = intent.getStringExtra("meses") ?: ""
        val juros = intent.getStringExtra("juros") ?: ""
        val valorFinal = intent.getDoubleExtra("valorFinal", 0.0)
        val rendimento = intent.getDoubleExtra("rendimento", 0.0)

        binding.tieInsAporteResult.setText(aporte)
        binding.tieInsMesesResult.setText(meses)
        binding.tieInsJurosResult.setText(juros)

        binding.txtValorLabelResult.text = String.format("R$ %.2f", valorFinal)
        binding.txtValorVrLabelResult.text = String.format("R$ %.2f", rendimento)
    }
}
