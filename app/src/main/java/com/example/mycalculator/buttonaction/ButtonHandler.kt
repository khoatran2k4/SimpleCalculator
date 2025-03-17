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
        val text = myTextView2.text.toString().trim()

        if (!checkEqualZero(myTextView2) && !checkEmpty(myTextView2)) {
            if (text.startsWith("-")) {
                myTextView2.text = "-" + myTextView2.text.trim().toString()
            } else {
                myTextView2.text = "-$text"
            }
        }
    }

    fun onOperatorClick(view: View) {
        val button = view as Button
        val mainOperator = button.text.toString()

        Log.d("DEBUG", "Button text: '$mainOperator'")

        val expression1 = myTextView1.text.toString().trim()
        val expression2 = myTextView2.text.toString().trim()

        Log.d("DEBUG", "Operator Clicked: '$mainOperator'")
        Log.d("DEBUG", "expression1: '$expression1', expression2: '$expression2'")


        val regex = """(-?\d*\.?\d*)\s*([+\-x/])$""".toRegex()

        val match = regex.find(expression1)

        try {
            if (match != null) {
                val (num1, operator) = match.destructured
                Log.d("DEBUG", "Match not null")
                if (expression2 != "") {

                    var number1 = num1.toDouble()
                    var number2 = expression2.toDouble()
                    val result = calculation(number1, operator, number2)

                    Log.d("Calculator", "Kết quả của phép tính: $number1 $operator $expression2 = $result")

                    if (mainOperator.toString() == "=") {
                        Log.d("DEBUG", "Operator equal =")
                        myTextView1.text = formatResult(result)
                    } else {
                        Log.d("DEBUG", "Operator not equal =")
                        myTextView1.text = "${formatResult(result)} $mainOperator"
                    }
                } else {
                    if (mainOperator.toString() != "=") {
                        myTextView1.text = "${num1.toString()} $mainOperator"
                    }
                }
            } else if (expression1 != "") {
                if (mainOperator.toString() != "=" && expression2 != "") {
                    var number1 = expression1.toDouble()
                    var number2 = expression2.toDouble()
                    val result = calculation(number1, mainOperator, number2)
                    myTextView1.text = result.toString()
                } else if (mainOperator != "=" && expression2 == "") {
                    myTextView1.text = "$expression1 $mainOperator"
                }
            } else if (expression1.toString() == "") {
                Log.d("DEBUG", "Match null")
                if (expression2 != "") {
                    myTextView1.text = "$expression2 $mainOperator "
                }
            }
        } catch (e: Exception) {
            Log.e("ERROR", "Exception in onOperatorClick: ${e.message}", e)
        }
        myTextView2.text = ""
    }

    fun onClear(view: View) {
        myTextView2.text = ""
    }

    fun onClearAll(view: View) {
        myTextView1.text = ""
        myTextView2.text = ""
    }

    fun onBackSpace(view: View) {
        val text = myTextView2.text.toString()
        if (text != "") {
            myTextView2.text = text.substring(0, text.length - 1)
        }
    }


    fun checkEmpty (myTextView: TextView) : Boolean{
        return myTextView.text.toString().trim() == ""
    }

    fun checkEqualZero (myTextView: TextView) : Boolean {
        return myTextView.text.toString() == "0"
    }

    fun formatResult(result: Double): String {
        return if (result % 1 == 0.0) {
            result.toInt().toString()
        } else {
            String.format("%.2f", result)
        }
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