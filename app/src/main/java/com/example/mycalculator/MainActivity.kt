package com.example.mycalculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mycalculator.buttonaction.ButtonHandler

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textView1: TextView = findViewById(R.id.textView)
        val textView2: TextView = findViewById(R.id.textView2)

        val buttonHandler = ButtonHandler(textView1, textView2)

        findViewById<Button>(R.id.button2).setOnClickListener { buttonHandler.onNumberClick(it) }
        findViewById<Button>(R.id.button5).setOnClickListener { buttonHandler.onNumberClick(it) }
        findViewById<Button>(R.id.button6).setOnClickListener { buttonHandler.onNumberClick(it) }
        findViewById<Button>(R.id.button7).setOnClickListener { buttonHandler.onNumberClick(it) }
        findViewById<Button>(R.id.button9).setOnClickListener { buttonHandler.onNumberClick(it) }
        findViewById<Button>(R.id.button10).setOnClickListener { buttonHandler.onNumberClick(it) }
        findViewById<Button>(R.id.button11).setOnClickListener { buttonHandler.onNumberClick(it) }
        findViewById<Button>(R.id.button13).setOnClickListener { buttonHandler.onNumberClick(it) }
        findViewById<Button>(R.id.button14).setOnClickListener { buttonHandler.onNumberClick(it) }
        findViewById<Button>(R.id.button15).setOnClickListener { buttonHandler.onNumberClick(it) }

        findViewById<Button>(R.id.button3).setOnClickListener { buttonHandler.onNumberClick(it) }

        findViewById<Button>(R.id.button4).setOnClickListener { buttonHandler.onOperatorClick(it) }
        findViewById<Button>(R.id.button8).setOnClickListener { buttonHandler.onOperatorClick(it) }
        findViewById<Button>(R.id.button12).setOnClickListener { buttonHandler.onOperatorClick(it) }
        findViewById<Button>(R.id.button16).setOnClickListener { buttonHandler.onOperatorClick(it) }
        findViewById<Button>(R.id.button20).setOnClickListener { buttonHandler.onOperatorClick(it) }

        findViewById<Button>(R.id.button).setOnClickListener { buttonHandler.onToggleSign(it) }

        findViewById<Button>(R.id.button17).setOnClickListener { buttonHandler.onClear(it) }
        findViewById<Button>(R.id.button18).setOnClickListener { buttonHandler.onClearAll(it) }
        findViewById<Button>(R.id.button19).setOnClickListener { buttonHandler.onBackSpace(it) }




    }
}