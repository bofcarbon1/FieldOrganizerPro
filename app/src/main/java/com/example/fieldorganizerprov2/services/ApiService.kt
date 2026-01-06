package com.example.fieldorganizerprov2.services

import com.example.fieldorganizerprov2.contacts.models.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import org.json.JSONArray


class ApiService {
    suspend fun getContacts(): List<Contact> {

        val apikey =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InltaHRzaHdod3Bob2tubmxuYXRwIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjY1MTE5NjUsImV4cCI6MjA4MjA4Nzk2NX0.XqV2YW3I0Z8nK6rkFQlowUNNIxN2GEYzSftgY1Ca3jA"
        //val endpoint = "https://ymhtshwhwphoknnlnatp.supabase.co/rest/v1/contacts?select=*"
        //val endpoint = "https://ymhtshwhwphoknnlnatp.supabase.co/rest/v1/contacts"
        val endpoint = "https://ymhtshwhwphoknnlnatp.supabase.co/rest/v1/contacts?select=*"

        return withContext(Dispatchers.IO) {
            val url = URL(endpoint)
            val connection = url.openConnection() as HttpsURLConnection // Note: HttpsURLConnection!

            // Headers for Supabase http
            // API key is needed to implement API access
            connection.setRequestProperty("apikey", apikey)
            // We exchange in JSON
            connection.setRequestProperty("Content-Type", "application/json")
            connection.setRequestProperty("Accept", "application/json")

            // Set method to GET
            connection.requestMethod = "GET"
            // Connect to
            connection.connect()

            // Check response
            if (connection.responseCode == 200) {
                val json = connection.inputStream.bufferedReader().readText()
                parseContactsWithOrgJson(json)
                //Json.decodeFromString<List<Contact>>(json)
            } else {
                // Read error
                val error = connection.errorStream?.bufferedReader()?.readText()
                throw IOException("HTTP ${connection.responseCode}: $error")
            }
        }

    }

    fun parseContactsWithOrgJson(jsonString: String): List<Contact> {
        val contacts = mutableListOf<Contact>()

        val jsonArray = JSONArray(jsonString)

        for (i in 0 until jsonArray.length()) {
            val obj = jsonArray.getJSONObject(i)

            // Get values (handles null/optional)
            //val id = obj.optLong(id, "")
            val firstName = obj.optString("first_name", "")
            val lastName = obj.optString("last_name", "")
            val phone = obj.optString("phone", "")

            // Only add if we have minimum data
            if (firstName.isNotEmpty() && lastName.isNotEmpty()) {
                contacts.add(Contact(
                    null,
                    firstName,
                    lastName,
                    phone,
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    ""
                ))
            }
        }

        return contacts
    }

    // Your full solution:
    //suspend fun getContacts(): List<Contact> {
    //    return withContext(Dispatchers.IO) {
    //        val jsonString = getRawJsonFromSupabase()  // Your working HTTP

            // Option 1: Manual parsing (above)
            // parseContactsManual(jsonString)

            // Option 2: Android's org.json
     //       parseContactsWithOrgJson(jsonString)
     //   }
    //}


}



