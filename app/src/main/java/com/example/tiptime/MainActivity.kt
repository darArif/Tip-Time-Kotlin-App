package com.example.tiptime

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil
import kotlin.math.cos

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tipCalculatorButtonId.setOnClickListener {
            calculateTip()
            handleKeyEvent2(binding.tipCalculatorButtonId)
        }
        binding.costOfServiceEditTextId.setOnKeyListener { view, keyCode, _ ->
            handleKeyEvent1(view, keyCode)
        }
    }

    private fun calculateTip() {
        val costOfService = binding.costOfServiceEditTextId.text.toString().toDoubleOrNull()
        if (costOfService == null) {
            displayTipAndCost(0.0, 0.0)
            return
        }
        var tip: Double = when (binding.radioGroupId.checkedRadioButtonId) {
            R.id.radio_button_25_id -> costOfService * (25.0 / 100.0)
            R.id.radio_button_18_id -> costOfService * (18.0 / 100.0)
            R.id.radio_button_15_id -> costOfService * (15.0 / 100.0)
            else -> 0.0
        }

        if (binding.roundUpSwitchId.isChecked) {
            tip = ceil(tip)
        }

        displayTipAndCost(costOfService, tip)
    }

    private fun displayTipAndCost(costOfService: Double, tip: Double) {
        binding.tipResultId.text =
            getString(R.string.tip_cost_text, NumberFormat.getCurrencyInstance().format(tip))
        binding.totalServiceCostId.text = getString(
            R.string.total_cost_text,
            NumberFormat.getCurrencyInstance().format(costOfService + tip)
        )
    }

    private fun handleKeyEvent1(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }

    private fun handleKeyEvent2(view: View): Boolean {
        // Hide the keyboard
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        return true
    }
}