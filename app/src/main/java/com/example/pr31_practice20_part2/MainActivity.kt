package com.example.pr31_practice20_part2

import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.pr31_practice20_part2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var snackbar:Snackbar
    lateinit var sPref:SharedPreferences
    var count:String = ""
    var temp:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .show()
        }

        binding.button2.setOnClickListener { view ->
            snackbar = Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            snackbar.setAction("Action", View.OnClickListener { Toast.makeText(this,"Ура каникулы? \uD83C\uDF38",Toast.LENGTH_SHORT).show() })
            snackbar.setActionTextColor(Color.YELLOW)
            snackbar.show()

        }

        binding.button3.setOnClickListener { view ->
            temp++
            Save()
            Load()
            val toast = Toast.makeText(this,"Clicks count: $temp",Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.END,200,900)
            toast.show()
        }

        sPref = getPreferences(MODE_PRIVATE)
        if (sPref.getString("count","").toString().isNotEmpty()){
            temp = sPref.getString("count","").toString().toInt()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun Save(){
        val ed = sPref.edit()
        ed.putString("count","").toString()
        ed.apply()
    }

    fun Load(){
        count = sPref.getString("count","").toString()
    }

    override fun onStop(){
        super.onStop()
        Save()
    }
}