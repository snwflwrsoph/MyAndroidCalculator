package com.sophia.mycalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    // 0 -> nothing
    // 1 -> addition
    // 2 -> substraction
    // 3 -> multiplication
    // 4 -> division
    var operation: Int = 0
    var number1: Double = 0.0
    var dot: Boolean = false
    lateinit var tv_num1: TextView
    lateinit var tv_num2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        tv_num1 = findViewById<TextView>(R.id.tv_num1)
        tv_num2 = findViewById<TextView>(R.id.tv_num2)
        val clearBtn: Button = findViewById<Button>(R.id.clear_btn)
        val equalBtn: Button = findViewById<Button>(R.id.equal_btn)


        equalBtn.setOnClickListener {
            var number2: Double = tv_num2.text.toString().toDouble()

            var answer: Double = 0.0

            when(operation){
                1 -> answer = number1 + number2
                2 -> answer = number1 - number2
                3 -> answer = number1 * number2
                4 -> {
                    // can't divide by zero
                    if(number2 == 0.0){
                        Toast.makeText(
                            this,
                            "You can't divide by zero",
                            Toast.LENGTH_SHORT
                        ).show()
                        answer = 0.0
                    }
                    else{
                        answer = number1 / number2
                    }
                }
            }

            if(answer % 1.0 == 0.0) {
                tv_num2.setText(answer.toInt().toString())
            }
            else{
                tv_num2.setText(answer.toString())
            }
            tv_num1.setText("")
        }

        clearBtn.setOnClickListener {
            tv_num1.setText("")
            tv_num2.setText("")
            number1 = 0.0
            operation = 0
        }
    }

    fun pressDigit(view: View){
        // val tv_num: TextView = findViewById<TextView>(R.id.tv_num2)
        var num: String = tv_num2.text.toString()

        when(view.id){
            R.id.zero_btn -> tv_num2.setText(num + '0')
            R.id.one_btn -> tv_num2.setText(num + '1')
            R.id.two_btn -> tv_num2.setText(num + '2')
            R.id.three_btn -> tv_num2.setText(num + '3')
            R.id.four_btn -> tv_num2.setText(num + '4')
            R.id.five_btn -> tv_num2.setText(num + '5')
            R.id.six_btn -> tv_num2.setText(num + '6')
            R.id.seven_btn -> tv_num2.setText(num + '7')
            R.id.eight_btn -> tv_num2.setText(num + '8')
            R.id.nine_btn -> tv_num2.setText(num + '9')
            R.id.dot_btn -> {
                if (!dot){
                    tv_num2.setText(num + '.')
                    dot = true
                }
                else{
                    Toast.makeText(
                        this,
                        "You can only have one point",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        }
    }

    fun clicOperation(view: View){
        number1 = tv_num2.text.toString().toDouble()
        var num2_text: String = tv_num2.text.toString()
        tv_num2.setText("")

        when(view.id){
            R.id.plus_btn -> {
                tv_num1.setText(num2_text + "+")
                operation = 1
            }

            R.id.minus_btn -> {
                tv_num1.setText(num2_text + "-")
                operation = 2
            }

            R.id.multiply_btn -> {
                tv_num1.setText(num2_text + "*")
                operation = 3
            }

            R.id.divide_btn -> {
                tv_num1.setText(num2_text + "/")
                operation = 4
            }
        }
    }
}