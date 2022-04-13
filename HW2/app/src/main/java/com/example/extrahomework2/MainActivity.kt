package com.example.extrahomework2

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.iterator
import androidx.gridlayout.widget.GridLayout
import java.util.*
import kotlinx.android.synthetic.main.
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View

activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    var score=0
    var han = Handler()
    var runnable= Runnable {  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hideImages()
        object: CountDownTimer(15000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                timeText.text="Time: ${millisUntilFinished/1000}"
            }
            override fun onFinish() {
                timeText.text= "Time: 0"
                han.removeCallbacks(runnable)
                for(image in gridLayout){
                    image.visibility= View.INVISIBLE
                }
                var alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game is over")
                alert.setMessage("Play again?")
                alert.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                    val intent = intent
                    finish()
                    startActivity(intent)
                })
                alert.setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
                    Toast.makeText(this@MainActivity, "Game Over", Toast.LENGTH_LONG).show()
                })
                alert.show()
            }
        }.start()
    }

    fun increaseScore(view: View){
        score=score+1
        scoreText.text="Score: ${score}"
    }

    fun hideImages(){
        runnable= object: Runnable{
            override fun run() {
                for(image in gridLayout){

                    image.visibility= View.INVISIBLE
                }
                val random= Random()
                val randomNumber= random.nextInt(3)
                val randomNumber2=random.nextInt(3)
                val view: View
                view= imageView1
                var params = GridLayout.LayoutParams(view.layoutParams)
                params.rowSpec = GridLayout.spec(randomNumber,3)
                params.columnSpec = GridLayout.spec(randomNumber2,3)
                view.layoutParams = params
                view.visibility= View.VISIBLE

                han.postDelayed(runnable, 500)
            }

        }
        han.post(runnable)



    }
}