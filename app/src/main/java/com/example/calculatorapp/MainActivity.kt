package com.example.calculatorapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.calculatorapp.utils.OperatorUtils


class MainActivity : AppCompatActivity() {

    private var tvInput: TextView? = null
    private var numOne: Button? = null
    private var numTwo: Button? = null
    private var numThree: Button? = null
    private var numFour: Button? = null
    private var numFive: Button? = null
    private var numSix: Button? = null
    private var numSeven: Button? = null
    private var numEight: Button? = null
    private var numNine: Button? = null
    private var numZero: Button? = null
    private var add: Button? = null
    private var subtract: Button? = null
    private var multiply: Button? = null
    private var divide: Button? = null
    private var decimal: Button? = null
    private var equal: Button? = null

    var lastNumeric : Boolean = false
    var lastDot : Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)
        numOne = findViewById(R.id.buttonOne)
        numTwo = findViewById(R.id.buttonTwo)
        numThree = findViewById(R.id.buttonThree)
        numFour = findViewById(R.id.buttonFour)
        numFive = findViewById(R.id.buttonFive)
        numSix = findViewById(R.id.buttonSix)
        numSeven = findViewById(R.id.buttonSeven)
        numEight = findViewById(R.id.buttonEight)
        numNine = findViewById(R.id.buttonNine)
        numZero = findViewById(R.id.buttonZero)
        add = findViewById(R.id.buttonAdd)
        subtract = findViewById(R.id.buttonSubtract)
        multiply = findViewById(R.id.buttonMultiply)
        divide = findViewById(R.id.buttonDivide)
        decimal = findViewById(R.id.buttonDecimal)
        equal = findViewById(R.id.buttonEquals)
    }

    fun onDigit(view: View){
        tvInput?.append((view as Button).text)
        lastNumeric = true

    }

    fun onClear(view: View){
        tvInput?.text = ""
        lastNumeric = false
        lastDot = false
        toggleButtons(true)
    }

    fun onDecimal(view: View){
        if(lastNumeric && !lastDot){
            tvInput?.append(".")
            lastNumeric = false
            lastDot = true
        } else if (!lastNumeric && !lastDot) {
            tvInput?.append("0.")
            lastNumeric = false
            lastDot = true
        }
    }


    fun onOperator(view: View) {
        tvInput?.text?.let {

            if (lastNumeric && !OperatorUtils.isOperatorAdded(it.toString())) {
                tvInput?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }

    fun onEqual(view: View){
        if(lastNumeric){
            var tvValue = tvInput?.text.toString()
            var prefix = ""

            try {
                if(tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if(tvValue.contains("-")){
                    val splitValue = tvValue.split("-")

                    var one = splitValue[0]
                    val two = splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                        }

                    tvInput?.text = OperatorUtils.removeZeroAfterDecimal((one.toDouble() - two.toDouble()))
                }
                else if(tvValue.contains("+")){
                    val splitValue = tvValue.split("+")

                    var one = splitValue[0]
                    val two = splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = OperatorUtils.removeZeroAfterDecimal((one.toDouble() + two.toDouble()))
                }
                else if(tvValue.contains("/")){
                    val splitValue = tvValue.split("/")

                    var one = splitValue[0]
                    val two = splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    val test = (one.toDouble() / two.toDouble())
                    if (test.equals(Double.POSITIVE_INFINITY)) {
                        throw java.lang.ArithmeticException()
                    }

                    tvInput?.text = OperatorUtils.removeZeroAfterDecimal(test)
                }
                else if(tvValue.contains("*")){
                    val splitValue = tvValue.split("*")

                    var one = splitValue[0]
                    val two = splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = OperatorUtils.removeZeroAfterDecimal((one.toDouble() * two.toDouble()))
                }


            }catch (e: ArithmeticException){
                e.printStackTrace()
                toggleButtons(false)
                tvInput?.text = getString(R.string.Divide_by_zero_error_message)
            }

            lastDot = OperatorUtils.decimalCheck(tvInput?.text.toString())
        }
    }

    fun toggleButtons(toggle: Boolean) {
        numOne?.isEnabled = toggle
        numTwo?.isEnabled = toggle
        numThree?.isEnabled = toggle
        numFour?.isEnabled = toggle
        numFive?.isEnabled = toggle
        numSix?.isEnabled = toggle
        numSeven?.isEnabled = toggle
        numEight?.isEnabled = toggle
        numNine?.isEnabled = toggle
        numZero?.isEnabled = toggle
        add?.isEnabled = toggle
        subtract?.isEnabled = toggle
        multiply?.isEnabled = toggle
        divide?.isEnabled = toggle
        decimal?.isEnabled = toggle
        equal?.isEnabled = toggle
    }

}