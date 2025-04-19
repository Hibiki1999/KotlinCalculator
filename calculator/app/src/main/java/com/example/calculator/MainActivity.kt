package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import android.widget.*

class MainActivity : ComponentActivity() {
    private lateinit var number:TextView
    private lateinit var num1:Button
    private lateinit var num2:Button
    private lateinit var num3:Button
    private lateinit var num4:Button
    private lateinit var num5:Button
    private lateinit var num6:Button
    private lateinit var num7:Button
    private lateinit var num8:Button
    private lateinit var num9:Button
    private lateinit var num0:Button
    private lateinit var plus:Button
    private lateinit var minus:Button
    private lateinit var divide:Button
    private lateinit var multiple:Button
    private lateinit var equal:Button
    private lateinit var clear:Button
    private lateinit var plusMinus:Button
    private lateinit var percent:Button
    private lateinit var dot:Button

    enum class Status{
        first,
        second,
        afterCal
    }

    var input1 = 0.0f;
    var input2 = 0.0f;
    var opt = 0;
    var showString = "";
    var currentState = Status.first;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        FindWidget()
        SubWidget()
    }

    private fun FindWidget()
    {
        number = findViewById<TextView>(R.id.ShowText)
        num1 = findViewById<Button>(R.id.one_num)
        num2 = findViewById<Button>(R.id.two_num)
        num3 = findViewById<Button>(R.id.three_num)
        num4 = findViewById<Button>(R.id.four_num)
        num5 = findViewById<Button>(R.id.five_num)
        num6 = findViewById<Button>(R.id.six_num)
        num7 = findViewById<Button>(R.id.seven_num)
        num8 = findViewById<Button>(R.id.eight_num)
        num9 = findViewById<Button>(R.id.nine_num)
        num0 = findViewById<Button>(R.id.zero_num)
        plus = findViewById<Button>(R.id.plus_mark)
        minus  = findViewById<Button>(R.id.minus_mark)
        multiple  = findViewById<Button>(R.id.multiple)
        divide  = findViewById<Button>(R.id.divide)
        plusMinus  = findViewById<Button>(R.id.plusminus)
        percent  = findViewById<Button>(R.id.percent)
        dot  = findViewById<Button>(R.id.dot)
        equal = findViewById<Button>(R.id.equal_mark)
        clear = findViewById<Button>(R.id.clear)
    }

    private fun SubWidget()
    {
        num1.setOnClickListener{ AttchText("1"  )}
        num2.setOnClickListener{ AttchText("2"  )}
        num3.setOnClickListener{ AttchText("3"  )}
        num4.setOnClickListener{ AttchText("4"  )}
        num5.setOnClickListener{ AttchText("5"  )}
        num6.setOnClickListener{ AttchText("6"  )}
        num7.setOnClickListener{ AttchText("7"  )}
        num8.setOnClickListener{ AttchText("8"  )}
        num9.setOnClickListener{ AttchText("9"  )}
        num0.setOnClickListener{ AttchText("0"  )}
        plus.setOnClickListener{ AttachOpt("+")}
        minus.setOnClickListener{ AttachOpt("-")}
        multiple.setOnClickListener{ AttachOpt("*")}
        divide.setOnClickListener{ AttachOpt("/")}
        dot.setOnClickListener{ AttchText(".")}
        equal.setOnClickListener{ Calculate()}
        clear.setOnClickListener{ Clear()}
        percent.setOnClickListener{ Percent() }
        plusMinus.setOnClickListener{ PlusMinus() }
    }

    private fun AttchText(property: String){
        if(property == "."){
            if(showString.contains(property)){
                return;
            }
        }

        when(currentState){
            Status.first->{
                showString += property
                input1 = showString.toFloat()
                number.text = showString
            }
            Status.second->{
                showString += property
                input2 = showString.toFloat()
                number.text = showString
            }
            Status.afterCal->{
                Clear()
                showString += property
                input1 = showString.toFloat()
                number.text = showString
            }
            else->{

            }
        }
    }

    private fun AttachOpt(property: String){
        opt =  when(property) {
            "+" -> 0
            "-" -> 1
            "*" -> 2
            "/" -> 3
            else -> -1
        }
        currentState = Status.second
        showString = ""
    }

    private fun PlusMinus(){
        when(currentState){
            Status.first->{
                input1 = -input1
                showString = input1.toString()
                number.text = showString
            }
            Status.second->{
                input2 = -input2
                showString = input2.toString()
                number.text = showString
            }
            Status.afterCal->{
                input1 = -input1
                showString = input1.toString()
                number.text = showString
            }
            else->{

            }
        }
    }

    private fun Percent(){
        when(currentState){
            Status.first->{
                input1 = input1 / 100.0f
                showString = input1.toString()
                number.text = showString
            }
            Status.second->{
                input2 = input2/ 100.0f
                showString = input2.toString()
                number.text = showString
            }
            Status.afterCal->{
                input1 = input1 / 100.0f
                showString = input1.toString()
                number.text = showString
            }
            else->{

            }
        }
    }

    private fun Calculate(){
        var res = 0.0f
        when(opt){
            0-> res = input1 + input2
            1-> res = input1 - input2
            2-> res = input1 * input2
            3-> res = input1 / input2
            else -> {}
        }
        number.text = res.toString()
        currentState = Status.afterCal
        input1 = res
        showString = res.toString()
    }

    private fun Clear(){
        input1 = 0.0f
        input2 = 0.0f
        opt = 0
        showString = ""
        currentState = Status.first
        number.text = ""
    }
}
