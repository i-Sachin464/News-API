package com.client.newsapi.utils

import android.os.AsyncTask
import android.util.Log
import com.client.newsapi.R
import com.client.newsapi.model.News
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

public class Util {
    companion object {
        class FetchData :
            AsyncTask<Void?, Void?, String?>() {
            override fun onPreExecute() {
                super.onPreExecute()
//            ProgressUtil.startProgressDialog();
            }

            override fun onPostExecute(s: String?) {
                super.onPostExecute(s)

            }

            override fun doInBackground(vararg p0: Void?): String? {
                var urlConnection: HttpURLConnection? = null
                var reader: BufferedReader? = null

                val stringURL =
                    "${R.string.url}/v2/top-headlines?country=us&apiKey=${R.string.api_key}"

                var jsonStr: String? = null
                try {
                    val url = URL(stringURL)
                    urlConnection = url.openConnection() as HttpURLConnection
                    urlConnection.setRequestMethod("GET")
                    urlConnection.connect()
                    val lengthOfFile: Int = urlConnection.getContentLength()
                    // Read the input stream into a String
                    val inputStream: InputStream = urlConnection.getInputStream()
                    val buffer = StringBuffer()
                    if (inputStream == null) {
                        return null
                    }
                    reader = BufferedReader(InputStreamReader(inputStream))
                    var line: String
                    while (reader.readLine().also { line = it } != null) {
                        buffer.append(
                            """
                        $line
                        
                        """.trimIndent()
                        )
                    }
                    if (buffer.length == 0) {
                        return null
                    }
                    jsonStr = buffer.toString()
                    Log.e("Json", jsonStr)
                    val jsonArray = JSONArray(jsonStr)
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
//                        val contactModel = News()
//                        contactModel.setID(jsonObject.getString("id"))
//                        contactModel.setName(jsonObject.getString("name"))
//                        contactModel.setPhone(jsonObject.getString("mobile"))
//                        contactModel.setImageURL(jsonObject.getString("profile_imge"))
//                    contactModels.add(contactModel)
                    }
                    return jsonStr
                } catch (e: IOException) {
                    Log.e("PlaceholderFragment", "Error ", e)
                } catch (e: JSONException) {
                    e.printStackTrace()
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect()
                    }
                    if (reader != null) {
                        try {
                            reader.close()
                        } catch (e: IOException) {
                            Log.e("PlaceholderFragment", "Error closing stream", e)
                        }
                    }
                }
                return jsonStr
            }
        }
    }
}