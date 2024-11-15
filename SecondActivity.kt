package com.example.hidden_calculator

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var argOneET: EditText
    lateinit var argTwoET: EditText
    lateinit var plusBTN: Button
    lateinit var difBTN: Button
    lateinit var multBTN: Button
    lateinit var divBTN: Button
    lateinit var transferBTN: Button
    var result=0.0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        argOneET = findViewById(R.id.argOneET)
        argTwoET = findViewById(R.id.argTwoET)
        plusBTN = findViewById(R.id.plusBTN)
        difBTN = findViewById(R.id.difBTN)
        multBTN = findViewById(R.id.multBTN)
        divBTN = findViewById(R.id.divBTN)
        transferBTN = findViewById(R.id.transferBTN)
        transferBTN.setOnClickListener {
            val intent = Intent()
            intent.putExtra("Check", result.toString())
            setResult(RESULT_OK, intent)
            finish()
        }
        plusBTN.setOnClickListener(this)
        difBTN.setOnClickListener(this)
        multBTN.setOnClickListener(this)
        divBTN.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if(argOneET.text.isEmpty()||argTwoET.text.isEmpty()) return
        val first=argOneET.text.toString().toDouble()
        val second=argTwoET.text.toString().toDouble()
        result=when(v?.id){
            R.id.plusBTN->Operations(first,second).plus()
            R.id.difBTN->Operations(first,second).dif()
            R.id.multBTN->Operations(first,second).mult()
            R.id.divBTN->Operations(first,second).div()
            else->0.0
        }
    }
}
