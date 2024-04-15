package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView


class Home : AppCompatActivity() {
    private lateinit var resultTextView: TextView
    private var operand1: Double = 0.0
    private var pendingOperator: String = ""
    private var isNewOperation: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        resultTextView = findViewById(R.id.resultTextView)

        val numberButtons = arrayOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
            R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9
        )
        for (buttonId in numberButtons) {
            val numberButton: Button = findViewById(buttonId)
            numberButton.setOnClickListener { onNumberButtonClick(numberButton) }
        }

        val operatorButtons = arrayOf(
            R.id.buttonAdd, R.id.buttonSubtract, R.id.buttonMultiply, R.id.buttonDivide
        )
        for (buttonId in operatorButtons) {
            val operatorButton: Button = findViewById(buttonId)
            operatorButton.setOnClickListener { onOperatorButtonClick(operatorButton) }
        }

        val decimalButton: Button = findViewById(R.id.buttonDecimal)
        decimalButton.setOnClickListener { onDecimalButtonClick() }

        val clearButton: Button = findViewById(R.id.buttonClear)
        clearButton.setOnClickListener { onClearButtonClick() }

        val equalsButton: Button = findViewById(R.id.buttonEquals)
        equalsButton.setOnClickListener { onEqualsButtonClick() }
    }

    private fun onNumberButtonClick(button: Button) {
        val number = button.text.toString()

        if (isNewOperation) {
            resultTextView.text = number
            isNewOperation = false
        } else {
            resultTextView.append(number)
        }
    }

    private fun onOperatorButtonClick(button: Button) {
        val operator = button.text.toString()
        val input = resultTextView.text.toString().toDouble()

        if (pendingOperator.isNotEmpty()) {
            val result = performOperation(operand1, input, pendingOperator)
            resultTextView.text = result.toString()
            operand1 = result
        } else {
            operand1 = input
        }

        pendingOperator = operator
        isNewOperation = true
    }

    private fun onDecimalButtonClick() {
        if (!resultTextView.text.contains(".")) {
            resultTextView.append(".")
        }
    }

    private fun onClearButtonClick() {
        resultTextView.text = ""
        operand1 = 0.0
        pendingOperator = ""
        isNewOperation = true
    }

    private fun onEqualsButtonClick() {
        val input = resultTextView.text.toString().toDouble()

        if (pendingOperator.isNotEmpty()) {
            val result = performOperation(operand1, input, pendingOperator)
            resultTextView.text = result.toString()
            operand1 = result
            pendingOperator = ""
            isNewOperation = true
        }
    }

    private fun performOperation(operand1: Double, operand2: Double, operator: String): Double {
        return when (operator) {
            "+" -> operand1 + operand2
            "-" -> operand1 - operand2
            "*" -> operand1 * operand2
            "/" -> operand1 / operand2
            else -> throw IllegalArgumentException("Invalid operator")
        }
    }
}