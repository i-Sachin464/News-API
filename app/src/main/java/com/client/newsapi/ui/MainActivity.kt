package com.client.newsapi.ui

//import com.bumptech.glide.Glide
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TableLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.client.newsapi.R
import com.client.newsapi.database.DatabaseManager
import com.client.newsapi.model.News
import com.client.newsapi.model.NewsResponse
import com.client.newsapi.rest.ApiClient.client
import com.client.newsapi.rest.ApiInterface
import com.client.newsapi.ui.adapter.StaggeredRecyclerviewAdapter
import com.client.newsapi.utils.OnClickListener
import com.client.newsapi.utils.ProgressUtil
import com.client.newsapi.utils.RecyclerViewClickListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


public class MainActivity : AppCompatActivity() {
    private val context = this@MainActivity

    lateinit var tableLayout: TableLayout
    lateinit var recyclerView: RecyclerView
    private var API_KEY = ""
    private var NUM_COLUMNS = 1
    lateinit var databaseManager: DatabaseManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        getResponseData()

    }

    private fun getResponseData() {
        if (API_KEY.isEmpty()) {
            API_KEY = baseContext.resources.getString(R.string.api_key)
        }
        val apiService = client!!.create(ApiInterface::class.java)
//        https://newsapi.org/v2/top-headlines?api_key={Your Api key}
        val data: MutableMap<String, String> = HashMap()
        data["country"] = "us"
        data["apiKey"] = API_KEY

        val call: Call<NewsResponse> = apiService.getHeadlines(data)
        call.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                ProgressUtil.startProgressDialog(context, "Fetching data from server.")
//                val statusCode = response.code()
                try {
                    val newsResponse: NewsResponse? = response.body();
                    val news: List<News> = newsResponse?.articles as List<News>
                    databaseManager.insertNewsList(news)
//                    Log.e("response", news.toString())
                } catch (e: Exception) {

                } finally {
                    var resultList: List<News?> = databaseManager.fetchNewsList();
                    setData(resultList)
                    ProgressUtil.stopProgressDialog(context)
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                // Log error here since request failed
                Log.e("", t.toString())
            }
        })
    }

    private fun init() {
        databaseManager = DatabaseManager(context)
//        tableLayout = findViewById(R.id.table_layout)
        recyclerView = findViewById(R.id.recyclerView)
    }

    fun setData(list: List<News?>) {
//        recyclerView = findViewById(R.id.recycler_view)
        var recyclerViewAdapter =
            StaggeredRecyclerviewAdapter(this, list.filterNotNull())
        val staggeredGridLayoutManager =
            StaggeredGridLayoutManager(NUM_COLUMNS, LinearLayoutManager.VERTICAL)
        recyclerView.layoutManager = staggeredGridLayoutManager
        recyclerView.adapter = recyclerViewAdapter
        recyclerView.addOnItemTouchListener(
            RecyclerViewClickListener(context,
                recyclerView, object : OnClickListener {
                    override fun onClick(view: View?, position: Int) {
//                        recyclerViewAdapter = (StaggeredRecyclerviewAdapter) recyclerView.;
                        var news: News = recyclerViewAdapter.getList()[position];
                        startActivity(Intent(context, DetailedNewsActivity::class.java).putExtra("newsExtra",news))

                    }

                    override fun onLongClick(view: View?, position: Int) {
//                        Toast.makeText(context, "Long press on position :" + position + 1, Toast.LENGTH_LONG ).show()
                    }
                })
        )


//        list.forEach() {
//            val tableRow = TableRow(context)
//            val layoutParams: TableRow.LayoutParams =
//                TableRow.LayoutParams(
//                    TableRow.LayoutParams.MATCH_PARENT,
//                    TableRow.LayoutParams.WRAP_CONTENT
//                )
//            tableRow.weightSum = 1f
//            tableRow.layoutParams = layoutParams
//
//            val scale: Float = context.getResources().getDisplayMetrics().density
//            val pixels = (0 * scale + 0.5f).toInt()
//
//            val title = TextView(context)
//            title.width = pixels
////            title.layoutParams = viewParams
//            val description = TextView(context)
//            description.width = pixels
////            description.layoutParams = viewParams
//            var imageUri: String = ""
//            val imageView = ImageView(context)
//            imageView.layoutParams = ViewGroup.LayoutParams(pixels, MATCH_PARENT)
//
//            if (it != null) {
//                title.text = it.title
//                description.text = it.description
//                imageUri = it.urlToImage
//            }
//            if (!imageUri.equals("")) {
////                Glide.with(this).load(imageUri).into(imageView);
////                Picasso.with(context).load(imageUri).fit().centerCrop()
////                    .placeholder(R.drawable.user_placeholder_error)
////                    .error(R.drawable.user_placeholder_error)
////                    .into(imageView);
//            } else {
////                imageView.setImageResource(R.drawable.user_placeholder_error)
//            }
//
//            tableRow.addView(title)
//            tableRow.addView(description)
//            tableRow.addView(imageView)
//            tableLayout.addView(tableRow)
//        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onResume() {
        super.onResume()
//        this.registerReceiver(
//            ConnectivityChangeReceiver(), IntentFilter(
//                ConnectivityManager.CONNECTIVITY_ACTION
//            )
//        )
//        val status = context?.let { NetworkUtil.getConnectivityStatusString(it) }
//        Log.e("Receiver ", "" + status)
//        if (status == "Not connected to Internet") {
//            Snackbar.make(content, "Internet not available", Snackbar.LENGTH_INDEFINITE).show()
//            Log.e("Receiver ", "not connected")
//        } else {
//            Snackbar.make(content, "Connected", Snackbar.LENGTH_SHORT).show()
//            Log.e("Receiver ", "connected")
//        }
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}