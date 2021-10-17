package com.example.top10downloaderapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import java.net.HttpURLConnection

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.textcell.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import org.json.JSONObject
import java.lang.Exception
import java.net.URL

class MainActivity : AppCompatActivity() {
    lateinit var rv:RecyclerView
    lateinit var btn:Button
    val parser = XMLParser()
var url = ""
    lateinit var al:List<AppName>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv = findViewById(R.id.recyclerView)
        btn = findViewById(R.id.button)
        al = listOf()


        btn.setOnClickListener {
            requestApi()

        }




    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mymenu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

                R.id.it1 -> { url ="http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=10/xml" }
                R.id.it2 -> {url = "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=100/xml"}
                else -> return super.onOptionsItemSelected(item)

        }
        return super.onOptionsItemSelected(item)

    }




    private fun requestApi(){

        CoroutineScope(IO).launch {
            val data = async {
                fetchdata()
            }.await()
            if (data.isNotEmpty())
            {
                updateRV(data)


            }
        }
    }

    private fun fetchdata():String{
        var response=""
        try {
            response = URL(url).readText(Charsets.UTF_8)
        }catch (e: Exception){
            println("Error $e")
        }
        return response
    }

    private suspend fun updateRV(data:String){
        withContext(Dispatchers.Main)
        {

            var nn = parser.parse(data.byteInputStream())
            Log.d("nn","$nn")
            al = nn
            rv.adapter?.notifyDataSetChanged()
            rv.adapter = MyAdapter(al)
            rv.layoutManager = LinearLayoutManager(this@MainActivity)

        }


    }
}