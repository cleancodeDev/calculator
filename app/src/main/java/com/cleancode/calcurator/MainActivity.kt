package com.cleancode.calcurator

import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val expressionTextView: TextView by lazy {
        findViewById(R.id.expressionTextView)
    }

    private val resultTextView: TextView by lazy {
        findViewById(R.id.resultTextView)
    }

    private var isOperator = false
    private var hasOperator = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun buttonClicked(v: View) {
        when (v.id) {
            R.id.number0 -> numberButtonClick("0")
            R.id.number1 -> numberButtonClick("1")
            R.id.number2 -> numberButtonClick("2")
            R.id.number3 -> numberButtonClick("3")
            R.id.number4 -> numberButtonClick("4")
            R.id.number5 -> numberButtonClick("5")
            R.id.number6 -> numberButtonClick("6")
            R.id.number7 -> numberButtonClick("7")
            R.id.number8 -> numberButtonClick("8")
            R.id.number9 -> numberButtonClick("9")
            R.id.buttonPlus -> operatorButtonClicked("+")
            R.id.buttonMinus -> operatorButtonClicked("-")
            R.id.buttonMulti -> operatorButtonClicked("*")
            R.id.buttonDivider -> operatorButtonClicked("/")
            R.id.buttonModulo -> operatorButtonClicked("%")
        }

    }

    private fun numberButtonClick(number: String) {
        if (isOperator) {
            expressionTextView.append(" ")
        }
        isOperator = false
        val expressionText = expressionTextView.text.split(" ")
        if (expressionText.isNotEmpty() && expressionText.last().length >= 15) {
            Toast.makeText(this, "15자리까지만 입력이 가능합니다.", Toast.LENGTH_SHORT).show()
            return
        } else if (number == "0" && expressionText.last().isEmpty()) {
            Toast.makeText(this, "0은 제일 앞에 올 수 없습니다.", Toast.LENGTH_SHORT).show()
            return
        }
        expressionTextView.append(number)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun operatorButtonClicked(operator: String) {
        if (expressionTextView.text.isEmpty()) {
            return
        }
        when {
            isOperator -> {
                val text = expressionTextView.text.toString()
                expressionTextView.text = text.dropLast(1) + operator
            }
            hasOperator -> {
                Toast.makeText(this, "연산자는 한번 만 사용할 수 있습니다", Toast.LENGTH_SHORT).show()
                return
            }
            else -> {
                expressionTextView.append(" $operator")
            }
        }

        val ssb = SpannableStringBuilder(expressionTextView.text)
        ssb.setSpan(
            ForegroundColorSpan(getColor(R.color.green)),
            expressionTextView.text.length - 1,
            expressionTextView.text.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        expressionTextView.text = ssb
        isOperator = true
        hasOperator = true
    }

    fun historyButtonClicked(v: View) {

    }

    fun resultButtonClicked(v: View) {

    }

    fun clearButtonClicked(v: View) {

    }
}