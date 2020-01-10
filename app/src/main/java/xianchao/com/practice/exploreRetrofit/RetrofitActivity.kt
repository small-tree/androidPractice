package xianchao.com.practice.exploreRetrofit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_retrofit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xianchao.com.practice.R
import java.util.ArrayList


class RetrofitActivity : AppCompatActivity() {

    var adapter:ListAdapter?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)
        RetrofitClient.init()
        adapter = ListAdapter()
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter
        getData()


    }

    private fun getData() {

        val paremters = HashMap<String, String>()
        paremters.put("v", "1.0")
        paremters.put("month", "01")
        paremters.put("day", "01")
        paremters.put("key", "c1748773ea07da2a11ecb45c332b2f8e")

        RetrofitClient.getNetApi().getHistoricalToday(paremters)
                .enqueue(object : Callback<HistoricalTodayBean> {
                    override fun onFailure(call: Call<HistoricalTodayBean>, t: Throwable) {
                        println("")
                    }

                    override fun onResponse(call: Call<HistoricalTodayBean>, response: Response<HistoricalTodayBean>) {
                        val body = response.body()
                        adapter?.result = body?.result
                        println("")
                    }
                })

    }


    class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

        var result: ArrayList<HistoricalTodayBean.Item>? = null
            set(value) {
                field = value
                notifyDataSetChanged()
            }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.MyViewHolder {
            val inflate = LayoutInflater.from(parent.context).inflate(R.layout.retrofit_item, parent, false)
            return MyViewHolder(inflate)
        }

        override fun getItemCount(): Int {
            return result?.size ?: 0
        }

        override fun onBindViewHolder(holder: ListAdapter.MyViewHolder, position: Int) {
            val item = result!![position]!!
            holder.tv_title.setText(item.title)
            holder.tv_date.setText("${item.year} ${item.month} ${item.day}")
            holder.tv_lunar.setText("${item.lunar}")
            holder.tv_des.setText("${item.des}")
        }


        class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var tv_title = itemView.findViewById<TextView>(R.id.tv_title)
            var tv_date = itemView.findViewById<TextView>(R.id.tv_date)
            var tv_lunar = itemView.findViewById<TextView>(R.id.tv_lunar)
            var tv_des = itemView.findViewById<TextView>(R.id.tv_des)
        }
    }
}
