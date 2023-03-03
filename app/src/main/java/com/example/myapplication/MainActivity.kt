package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.databinding.ActivityMainBinding
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val masOfOperators = arrayOf("+","-","*","/")
        var time:Double
        var time1:Long = 0
        var allTime = 0.0
        var counter = 0
        var allCounter = 0
        var trueCounter = 0
        var falseCounter = 0

        fun setValue():Triple<String,String,String>
        {
            binding.operator.text = masOfOperators.random()
            binding.firstValue.text = (10..99).random().toString()
            binding.secondValue.text = (10..99).random().toString()
            if (binding.operator.text=="/")
            {
                while (binding.firstValue.text.toString().toDouble()%binding.firstValue.text.toString().toDouble()!=0.0)
                {
                    binding.firstValue.text = (10..99).random().toString()
                    binding.secondValue.text = (10..99).random().toString()
                }
            }
            return Triple(binding.firstValue.text.toString(),binding.secondValue.text.toString(),binding.operator.text.toString())
        }

        fun operations()
        {
            val rnd1 = (0..1).random()
            val rnd2 = (0..100).random()
            binding.result.text = when (binding.operator.text){
                "+" -> ((binding.firstValue.text.toString().toInt()+binding.secondValue.text.toString().toInt())+rnd1*rnd2).toString()
                "-" -> (binding.firstValue.text.toString().toInt()-binding.secondValue.text.toString().toInt()+rnd1*rnd2).toString()
                "*" -> (binding.firstValue.text.toString().toInt()*binding.secondValue.text.toString().toInt()+rnd1*rnd2).toString()
                "/" -> (binding.firstValue.text.toString().toInt()/binding.secondValue.text.toString().toInt()+rnd1*rnd2).toString()
                else -> binding.result.text
            }

        }
        fun check():Boolean
        {
            return when (binding.operator.text)
            {
                "+" -> (binding.firstValue.text.toString().toInt()+binding.secondValue.text.toString().toInt())==binding.result.text.toString().toInt()
                "-" -> (binding.firstValue.text.toString().toInt()-binding.secondValue.text.toString().toInt())==binding.result.text.toString().toInt()
                "*" -> (binding.firstValue.text.toString().toInt()*binding.secondValue.text.toString().toInt())==binding.result.text.toString().toInt()
                "/" -> (binding.firstValue.text.toString().toInt()/binding.secondValue.text.toString().toInt())==binding.result.text.toString().toInt()
                else -> false
            }
        }
        fun procentCount():Double
        {
            return (((trueCounter.toDouble() / (binding.allCheckedCount.text.toString().toDouble() / 100.0)) * 100).roundToInt().toDouble() / 100)
        }

        fun getTime(Time:Double)
        {
            if(counter==0)
            {
                binding.minCounter.text=Time.toString()
            }
            if(binding.minCounter.text.toString().toDouble()>Time)
            {
                binding.minCounter.text=Time.toString()
            }
            if(binding.maxCounter.text.toString().toDouble()<Time)
            {
                binding.maxCounter.text=Time.toString()
            }
            ++counter
            allTime+=Time
            binding.middleCounter.text=(((allTime/counter.toDouble())*100).roundToInt().toDouble()/100).toString()
        }

        binding.btnStart.setOnClickListener()
        {
            binding.btnStart.isEnabled = false
            binding.btnTrue.isEnabled = true
            binding.btnFalse.isEnabled = true
            time1=System.currentTimeMillis()
            setValue()
            operations()

        }

        binding.btnTrue.setOnClickListener()
        {
            binding.allCheckedCount.text=(++allCounter).toString()
            if (check())
            {
                binding.trueCount.text=(++trueCounter).toString()
                trueMessage()
            }
            else
            {
                binding.falseCount.text=(++falseCounter).toString()
                falseMessage()
            }
            binding.trueProcent.text = procentCount().toString()+"%"
            time = ((((System.currentTimeMillis().toDouble()-time1.toDouble())/1000.0)*100).roundToInt().toDouble()/100)
            getTime(time)
            time=0.0
            time1=System.currentTimeMillis()
            setValue()
            operations()
        }

        binding.btnFalse.setOnClickListener()
        {
            binding.allCheckedCount.text=(++allCounter).toString()
            if (!check())
            {
                binding.trueCount.text=(++trueCounter).toString()
                trueMessage()
            }
            else
            {
                binding.falseCount.text=(++falseCounter).toString()
                falseMessage()
            }
            binding.trueProcent.text = procentCount().toString()+"%"
            time = ((((System.currentTimeMillis().toDouble()-time1.toDouble())/1000.0)*100).roundToInt().toDouble()/100)
            getTime(time)
            time=0.0
            time1=System.currentTimeMillis()
            setValue()
            operations()
        }



    }
    fun trueMessage()
    {
        val messageOfTrue = AlertDialog.Builder(this)
        messageOfTrue.setTitle("Правильно!")
        messageOfTrue.setMessage("ьььп")
        messageOfTrue.setPositiveButton("Продолжить") {_,_->}
        messageOfTrue.show()
    }
    fun falseMessage()
    {
        val messageOfFalse = AlertDialog.Builder(this)
        messageOfFalse.setTitle("Не правильно!")
        messageOfFalse.setMessage("ьььп")
        messageOfFalse.setPositiveButton("Продолжить") {_,_->}
        messageOfFalse.show()
    }
}