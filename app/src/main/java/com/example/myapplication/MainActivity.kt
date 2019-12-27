package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGet.setOnClickListener(){

            val studentId = txtStudentId.text.toString()

            getStudent(studentId)
        }


        btnAdd.setOnClickListener(){
            val studentId = txtStudentId.text.toString()
            val studentName = txtStudentName.text.toString()

            addStudent(studentId, studentName)
        }

    }


    // function for network call
    fun getStudent(studentId:String) {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url: String = "http://demo.onmyfinger.com/home/getById?id=" + studentId

        // Request a string response from the provided URL.
        val stringReq = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->

                var strResp = response.toString()
                val jsonObj: JSONObject = JSONObject(strResp)

                val name = jsonObj.get("name").toString()

                if (name == "null"){
                    Toast.makeText(this, "Not Found", Toast.LENGTH_LONG).show()
                }else{
                    txtStudentName.setText(name.toString())
                }
            },

            Response.ErrorListener {
                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
            })

        queue.add(stringReq)
    }



    fun addStudent(studentId:String, studentName:String) {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url: String = "http://demo.onmyfinger.com/home/Add?id=" + studentId + "&name=" + studentName

        // Request a string response from the provided URL.
        val stringReq = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->

                var strResp = response.toString()
                val jsonObj: JSONObject = JSONObject(strResp)

                val name = jsonObj.get("name").toString()

                if (name!="null"){
                    Toast.makeText(this, "Add", Toast.LENGTH_LONG).show()
                }
            },

            Response.ErrorListener {
                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
            })

        queue.add(stringReq)
    }

}
