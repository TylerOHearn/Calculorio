package com.example.calculatorapp.utils

import java.text.DecimalFormat

class OperatorUtils {
    companion object {
        fun decimalCheck (equation: String) : Boolean{
            return equation.contains(".")
        }

         fun isOperatorAdded(value: String): Boolean {
            return if (value.startsWith("-")) {
                false
            } else {
                (value.contains("/")
                        || value.contains("*")
                        || value.contains("-")
                        || value.contains("+"))
            }
        }

        fun removeZeroAfterDecimal(result: Double): String {
            val df = DecimalFormat()
            df.maximumFractionDigits = 5
            return df.format(result)
        }
    }
}