package fragments

import Adapter.FeedAdapter
import Common.HTTPDataHandler
import Model.RSSObject
import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ahmadtakkoush.appledeveloper.R
import com.google.gson.Gson
import org.jsoup.Jsoup

class NewsFragment : Fragment() {

    lateinit var news_recyclerview: RecyclerView
    private val RSS_link = "https://developer.apple.com/news/rss/news.rss"
    private val RSS_to_JSON_API = "https://api.rss2json.com/v1/api.json?rss_url="

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_news, container, false)

        news_recyclerview = rootView.findViewById(R.id.recyclerView) as RecyclerView
        news_recyclerview.layoutManager = LinearLayoutManager(activity)
        loadRSS()
        return rootView
    }

    private fun loadRSS() {
        val loadRSSAsync = @SuppressLint("StaticFieldLeak")
        object : AsyncTask<String, String, String>() {
            var mDialog = ProgressBar(context)

            override fun onPostExecute(result: String?) {
                //mDialog.dismiss()
                val rssObject: RSSObject = Gson().fromJson<RSSObject>(result, RSSObject::class.java)
                val adapter = context?.let { FeedAdapter(rssObject, it) }
                news_recyclerview.adapter = adapter
                adapter?.notifyDataSetChanged()

            }

            override fun doInBackground(vararg params: String): String? {
                val result: String
                val http = HTTPDataHandler()
                result = http.GetHTTPDataHandler(params[0])
                return result
            }

            override fun onPreExecute() {
                //mDialog.setMessage("Loading...")
                //mDialog.show()
            }


        }
        val url_get_data = StringBuilder(RSS_to_JSON_API)
        url_get_data.append(RSS_link)
        loadRSSAsync.execute(url_get_data.toString())

    }
}