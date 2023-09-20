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
    private lateinit var snackbar:Snackbar
    private lateinit var sPref:SharedPreferences
    private var count:String = ""
    private var temp:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sPref = getPreferences(MODE_PRIVATE)
        if (sPref.getString("count","").toString().isNotEmpty()){
            temp = sPref.getString("count","").toString().toInt()
        }

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
            Inc()
            Save()
            Load()
            val toast = Toast.makeText(this,"Clicks count: $temp",Toast.LENGTH_SHORT)
            toast.show()
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

    fun Inc(){
        temp++
    }
    fun Save(){
        sPref = getPreferences(MODE_PRIVATE)
        val ed = sPref.edit()
        ed.putString("count",temp.toString())
        ed.apply()
    }

    fun Load(){
        sPref = getPreferences(MODE_PRIVATE)
        count = sPref.getString("count","").toString()
    }

    override fun onStop(){
        super.onStop()
        Save()
    }

    override fun onPause() {
        super.onPause()
        Save()
    }
}