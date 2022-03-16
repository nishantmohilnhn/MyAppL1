package com.example.mytest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import java.time.temporal.TemporalAmount
private const val TAG = "MainActivity"
private const val INITAL_TIP_PERC = 15
class MainActivity : AppCompatActivity() {
    private lateinit var etBaseAmount: EditText
    private lateinit var seekBarTip: SeekBar
    private lateinit var tvPercentLab:TextView
    private lateinit var tvTipAmount:TextView
    private lateinit var tvTotalAmount:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etBaseAmount = findViewById(R.id.etBaseAmount)
        seekBarTip = findViewById(R.id.seekBarTip)
        tvPercentLab =findViewById(R.id.tvPercentLab)
        tvTipAmount= findViewById(R.id.tvTipAmount)
        tvTotalAmount=findViewById(R.id.tvTotalAmount)
        seekBarTip.progress =INITAL_TIP_PERC
        tvPercentLab.text="$INITAL_TIP_PERC%"
        seekBarTip.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
            Log.i(TAG, "onProgressChanged $p1")
                tvPercentLab.text = "$p1%"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
        
    }
}