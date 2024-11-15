package com.example.hidden_calculator

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var resultTV: TextView
    lateinit var mainBTN: Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        resultTV=findViewById(R.id.resultTV)
        mainBTN=findViewById(R.id.mainBTN)
        mainBTN.setOnClickListener {
            var intent= Intent(this,SecondActivity::class.java)
            lounchSomeAcivity.launch(intent)
        }
    }
    val lounchSomeAcivity=registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){result->
        if(result.resultCode== RESULT_OK){
            val data=result.data
            val textResult=data!!.getStringExtra("Check")
            resultTV.text=textResult
            Toast.makeText(this,"$textResult передан",Toast.LENGTH_LONG).show()
        } else Toast.makeText(this,"Отмена",Toast.LENGTH_LONG).show()
    }

}