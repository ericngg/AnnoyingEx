package com.example.annoyingex

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class ApiManager(context: Context) {

    private val queue: RequestQueue = Volley.newRequestQueue(context)

    private lateinit var messages: JSONArray

    private var success: Boolean = false

    companion object {
        const val TAG: String = "http_request"
    }

    fun fetchData() {
        val url = "https://raw.githubusercontent.com/echeeUW/codesnippets/master/ex_messages.json"

        val request = StringRequest(Request.Method.GET, url, { response ->
            val json = JSONObject(response)
            messages = json.getJSONArray("messages")
            success = true
        }, { error ->
            Log.e(TAG, "Error occurred: $error")
        })

        queue.add(request)
    }

    fun returnRandomMessage(): String {
        return if (success) {
            val random = (0..messages.length()).random()
            messages[random].toString()
        } else {
            "Unable to retrieve message"
        }
    }

}