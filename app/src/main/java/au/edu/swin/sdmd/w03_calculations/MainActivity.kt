package au.edu.swin.sdmd.w03_calculations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onStop(){
        super.onStop()
        Log.i("Lifecycle","stopped")
    }

    override fun onRestart(){
        super.onRestart()
        Log.i("Lifecycle","restarted")
    }

    override fun onResume(){
        super.onResume()
        Log.i("Lifecycle","resumed")
    }

    override fun onPause(){
        super.onPause()
        Log.i("Lifecycle","restarted")
    }

    var operator = "plus"

    private var result:Int = 0
    //This means that the result can be used in onSaveInstanceState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("Lifecycle","instance was created")

        val number1 = findViewById<EditText>(R.id.number1)
        val number2 = findViewById<EditText>(R.id.number2)

        val equals = findViewById<Button>(R.id.equals)
        equals.setOnClickListener {
            val result = when(operator) {
                "plus" -> add(number1.text.toString(), number2.text.toString())
                "mult" -> mult(number1.text.toString(), number2.text.toString())
                else -> add(number1.text.toString(), number2.text.toString())
            }

            // TODO: show result on the screen
            val answer = findViewById<TextView>(R.id.answer)
            answer.text = result.toString()
        }
    }

    // adds two numbers together
    private fun add(number1: String, number2: String) = number1.toInt() + number2.toInt()
    private fun mult(number1: String, number2: String) = number1.toInt() * number2.toInt()

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.radioPlus ->
                    if (checked) {
                        operator = "plus"
                    }
                R.id.radioMult ->
                    if (checked) {
                        operator = "mult"
                    }
            }
        }
    }


    override fun onStart(){
        super.onStart()
        Log.i("Lifecycle", "started")
    }

    override fun onDestroy(){
        super.onDestroy()
        Log.i("Lifecycle","destroyed" )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val result = findViewById<TextView>(R.id.answer)
        outState.putString("ANSWER",result.text.toString())

        Log.i("Lifecycle", "onSaveInstanceState")
    }
    //You are overriding from the inherited class AppCompat
}