package com.example.mycalculator.buttonaction

import android.util.Log
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

        Log.d("DEBUG", "Operator Clicked: '$operator'")
        Log.d("DEBUG", "expression1: '$expression1', expression2: '$expression2'")

        // val hasOperator = """\s[\+\-\*/]\s""".toRegex().containsMatchIn(expression1)
        // val regex = """(-?\d*\.?\d*)\s*([\+\-\*/])\s*$""".toRegex()

        val regex = """(-?\d*\.?\d*)\s*([+\-x/])$""".toRegex()

        val match = regex.find(expression1)

        try {
            if (match != null) {
                Log.d("DEBUG", "Match not null")
                if (expression2 != "") {

                    val (num1, operator) = match.destructured
                    var number1 = num1.toDouble()
                    var number2 = expression2.toDouble()
                    val result = calculation(number1, operator, number2)

                    Log.d("Calculator", "Kết quả của phép tính: $number1 $operator $expression2 = $result")

                    if (operator == "=") {
                        myTextView1.text = result.toString()
                    } else {
                        myTextView1.text = "${result.toString()} $operator"
                    }
                } else {
                    if (operator != "=") {
                        myTextView1.text = "$expression2 $operator"
                    }
                }
            } else if (expression1 != "") {
                if (operator != "=" && expression2 != "") {
                    var number1 = expression1.toDouble()
                    var number2 = expression2.toDouble()
                    val result = calculation(number1, operator, number2)
                    myTextView1.text = result.toString()
                } else if (operator != "=" && expression2 == "") {
                    myTextView1.text = "$expression1 $operator"
                }
            } else if (expression1 == "") {
                Log.d("DEBUG", "Match null")
                if (expression2 != "") {
                    myTextView1.text = "$expression2 $operator "
                }
            }
        } catch (e: Exception) {
            Log.e("ERROR", "Exception in onOperatorClick: ${e.message}", e)
        }
        myTextView2.text = ""
    }





    fun checkEmpty (myTextView: TextView) : Boolean{
        return myTextView.text.toString().trim() == ""
    }

    fun checkEqualZero (myTextView: TextView) : Boolean {
        return myTextView.text.toString() == "0"
    }

    fun calculation (number1: Double, operator: String, number2: Double): Double{
        val result: Double = when (operator) {
            "+" -> number1 + number2
            "-" -> number1 - number2
            "x" -> number1 * number2
            "/" -> number1 / number2
            else -> 0.0
        }
        //myTextView1.text = result.toString()
        //myTextView2.text = ""
        return result
    }




}