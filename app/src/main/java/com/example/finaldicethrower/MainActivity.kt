package com.example.finaldicethrower

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.finaldicethrower.databinding.ActivityMainBinding
import android.text.method.ScrollingMovementMethod


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private var modifikator = 0
    private var pocetKostek = 1
    private var vysledek = 0
    private var tabule: String = ""
    private var cislaKostek = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.obrazovkaStarrychVysledku.setMovementMethod(ScrollingMovementMethod())


        binding.d2Btn.setOnClickListener {
            hodKostkou(1)
        }

        binding.d4Btn.setOnClickListener {
            hodKostkou(3)
        }

        binding.d6Btn.setOnClickListener {
            hodKostkou(5)
        }

        binding.d8Btn.setOnClickListener {
            hodKostkou(7)
        }

        binding.d10Btn.setOnClickListener {
            hodKostkou(9)
        }

        binding.d12Btn.setOnClickListener {
            hodKostkou(11)
        }

        binding.d20Btn.setOnClickListener {
            hodKostkou(19)
        }

        binding.d100Btn.setOnClickListener {
            hodKostkou(99)
        }







        binding.minusKostkuBtn.setOnClickListener {
            if (pocetKostek - 1 > 0) {
                pocetKostek -= 1
            } else {
                Toast.makeText(this, "Nelze mít méně jak jednu kostku", Toast.LENGTH_SHORT).show()
            }
            aktualizacePoctuKostek()
        }

        binding.minusModifikatorBtn.setOnClickListener {
            modifikator -= 1
            aktualizacePoctuModifikatoru()
        }

        binding.plusKostkuBtn.setOnClickListener {
            pocetKostek += 1
            aktualizacePoctuKostek()
        }

        binding.plusModifikatorBtn.setOnClickListener {
            modifikator += 1
            aktualizacePoctuModifikatoru()
        }

    }

    private fun aktualizacePoctuKostek()
    {
        binding.pocetKostekTxt.text = pocetKostek.toString()
    }
    private fun aktualizacePoctuModifikatoru()
    {
        binding.ModifikatorTxt.text = modifikator.toString()
    }

    private fun aktualizaceTabule(vysledky: String, kostky: String) {

        val uschovna = tabule

        tabule = "($kostky) + $modifikator = $vysledky${System.lineSeparator()}"
        tabule += uschovna


    }

    private fun vypis() {
        binding.vysledekHodu.text = vysledek.toString()

        binding.obrazovkaStarrychVysledku.text = tabule
        cislaKostek = ""


    }

    private fun hodKostkou(strany: Int) {
        var kostka = 0
        var promena = 0
        for (i in 0..pocetKostek - 1) {
            promena = (0..strany).random() + 1
            kostka += promena
            if (cislaKostek == "") {
                cislaKostek += "$promena"
            } else
            {
            cislaKostek += ",$promena"
            }
        }
        vysledek = kostka + modifikator
        aktualizaceTabule(vysledek.toString(), cislaKostek)

        vypis()
    }
}