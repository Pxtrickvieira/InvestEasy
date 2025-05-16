package com.example.investeasy

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.investeasy.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.pow

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnCalcular.setOnClickListener {
            val aporteStr = binding.tieInsAporte.text.toString()
            val mesesStr = binding.tieInsMeses.text.toString()
            val jurosStr = binding.tieInsJuros.text.toString()


            if (aporteStr.isBlank() || mesesStr.isBlank() || jurosStr.isBlank()) {

                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val aporte = aporteStr.toDoubleOrNull()
            val meses = mesesStr.toIntOrNull()
            val jurosPercent = jurosStr.toDoubleOrNull()


            if (aporte == null || meses == null || jurosPercent == null) {
                Toast.makeText(this, "Por favor, insira valores válidos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val i = jurosPercent / 100.0

            val valorFinal = if (i != 0.0) {
                aporte * ((1 + i).pow(meses.toDouble()) - 1) / i
            } else {

                aporte * meses
            }

            val rendimento = valorFinal - (aporte * meses)

            val formatador = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
            binding.txtValorLabel.text = formatador.format(valorFinal)
            binding.txtValorVrLabel.text = formatador.format(rendimento)

        }

        binding.btnLimpar.setOnClickListener {
            binding.tieInsAporte.setText("")
            binding.tieInsMeses.setText("")
            binding.tieInsJuros.setText("")
            binding.txtValorLabel.text = "0.0"
            binding.txtValorVrLabel.text = "0.0"
        }
    }
}