package com.example.mytest

import android.animation.ArgbEvaluator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.content.ContextCompat

private const val TAG = "MainActivity"
private const val INITAL_TIP_PERC = 15
class MainActivity : AppCompatActivity() {
    private lateinit var etBaseAmount: EditText
    private lateinit var seekBarTip: SeekBar
    private lateinit var tvPercentLab:TextView
    private lateinit var tvTipAmount:TextView
    private lateinit var tvTotalAmount:TextView
    private lateinit var tvTipDic:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etBaseAmount = findViewById(R.id.etBaseAmount)
        seekBarTip = findViewById(R.id.seekBarTip)
        tvPercentLab =findViewById(R.id.tvPercentLab)
        tvTipAmount= findViewById(R.id.tvTipAmount)
        tvTotalAmount=findViewById(R.id.tvTotalAmount)
        tvTipDic=findViewById(R.id.tvTipDic)
        seekBarTip.progress =INITAL_TIP_PERC
        tvPercentLab.text="$INITAL_TIP_PERC%"
        updatMsg(INITAL_TIP_PERC)
        seekBarTip.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
            Log.i(TAG, "onProgressChanged $p1")
                tvPercentLab.text = "$p1%"
                computTipTotle()
                updatMsg(p1)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
        etBaseAmount.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
            Log.i(TAG, "after chag $p0")
                computTipTotle()
            }
        })
    }

    private fun updatMsg(tipperr: Int) {
        val tipDisc = when (tipperr){
            in 0..9 -> "Poor"
            in 10..14 -> "Acceptable"
            in 15..20 ->"Good"
            in 21..25 ->"Grate"
            else ->"Amazzing"
        }
        tvTipDic.text=tipDisc

        val color = ArgbEvaluator().evaluate(
            tipperr.toFloat()/seekBarTip.max,
            ContextCompat.getColor(this, R.color.colorWor),
            ContextCompat.getColor(this, R.color.colorBest)
        ) as Int
    tvTipDic.setTextColor(color)
    }

    private fun computTipTotle() {
        if (etBaseAmount.text.isEmpty()){
            tvTipAmount.text=""
            tvTotalAmount.text=""
            return
        }
        val bassAmount = etBaseAmount.text.toString().toDouble()
        val tipPercent =seekBarTip.progress
        val tipAmount = bassAmount * tipPercent /100
        val totalAmount = bassAmount + tipAmount
        tvTipAmount.text ="%.2f".format(tipAmount)
        tvTotalAmount.text= "%.2f".format(totalAmount)
    }
}