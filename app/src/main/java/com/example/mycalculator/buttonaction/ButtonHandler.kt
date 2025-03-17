package com.example.mycalculator.buttonaction

import android.view.View
import android.widget.Button
import android.widget.TextView

class ButtonHandler (private val myTextView1: TextView, private val myTextView2: TextView) {

    fun onNumberClick(view: View) {
        val buttonText = (view as Button).text.toString()
        myTextView2.append(buttonText)
    }

    fun onToggleSign(view: View) {
        if (!checkEqualZero(myTextView2) && !checkEmpty(myTextView2)) {
            myTextView2.text = "-" + myTextView2.text.trim().toString()
        }
    }

    fun onOperatorClick(view: View) {
        val button = view as Button
        val operator = button.text.toString()

        val expression1 = myTextView1.text.toString().trim()
        val expression2 = myTextView2.text.toString().trim()

        val hasOperator = """\s[\+\-\*/]\s""".toRegex().containsMatchIn(expression1)
        val regex = """(-?\d*\.?\d*)\s*([\+\-\*/])\s*(-?\d*\.?\d*)""".toRegex()
        val match = regex.find(expression1)

        if (expression1.isEmpty() && expression2.isEmpty()) return

        if (expression1.isEmpty() && expression2.isNotEmpty() && operator != "=") {
            myTextView1.text = "$expression2 $operator "
            myTextView2.text = ""
            return
        }

        myTextView2.text = ""
    }







    fun checkEmpty (myTextView: TextView) : Boolean{
        return myTextView.text.toString().trim().isEmpty()
    }

    fun checkEqualZero (myTextView: TextView) : Boolean {
        return myTextView.text.toString() == "0"
    }

    fun calculation (number1: Double, operator: String, number2: Double) {
        val result = when (operator) {
            "+" -> number1 + number2
            "-" -> number1 - number2
            "x" -> number1 * number2
            "/" -> number2 / number2
            else -> "Error"
        }
        myTextView1.text = result.toString()
        myTextView2.text = ""
    }

//    fun calculation (myTextView: TextView) {
//        val expression = myTextView.text.toString().trim()
//
//        val regex = """(-?\d*\.?\d*)\s*([\+\-\*/])\s*(-?\d*\.?\d*)""".toRegex()
//
//        val match = regex.find(expression)
//
//        if (match != null) {
//            val (num1, operator, num2) = match.destructured
//
//            val number1 = num1.toDoubleOrNull()
//            val number2 = num2.toDoubleOrNull()
//
//            if (number1 != null && number2 != null) {
//                val result = when (operator) {
//                    "+" -> number1 + number2
//                    "-" -> number1 - number2
//                    "x" -> number1 * number2
//                    "/" -> number2 / number2
//                    else -> "Error"
//                }
//                myTextView1.text = result.toString()
//                myTextView2.text = ""
//            }
//        } else {
//            myTextView1.text = "Error"
//        }
//    }


}