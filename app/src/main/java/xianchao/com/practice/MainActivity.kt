package xianchao.com.practice

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import xianchao.com.basiclib.extension.extStartActivity
import xianchao.com.practice.ArchitectureComponents.ArchitecturePracticeActivity
import xianchao.com.practice.alarmManager.PracticeAlarmActivity
import xianchao.com.practice.jobscheduler.JobSchedulerActivity
import xianchao.com.practice.processConnect.ServiceTestActivity
import xianchao.com.practice.socket.SocketPracticeActivity
import xianchao.com.practice.view.TestViewActivity
import xianchao.com.practice.workmanager.WorkManagerActivity


class WithParentClickListener(val onClickListener: View.OnClickListener) : View.OnClickListener {

    override fun onClick(v: View) {
        onClickListener.onClick(v)
        (v.parent as ViewGroup).callOnClick()
    }
}

fun View.setWithParentClickListener(onClickListener: View.OnClickListener) {
    this.setOnClickListener(WithParentClickListener(onClickListener))

}

class MainActivity : AppCompatActivity() {


    val itemDatas: MutableList<ItemData> by lazy {
        mutableListOf<ItemData>(
                ItemData("job service") { extStartActivity(JobSchedulerActivity::class.java) },
                ItemData("AlarmManager") { extStartActivity(PracticeAlarmActivity::class.java) },
                ItemData("workManager") { extStartActivity(WorkManagerActivity::class.java) },
                ItemData("socket") { extStartActivity(SocketPracticeActivity::class.java) },
                ItemData("ArchitectureComponents") { extStartActivity(ArchitecturePracticeActivity::class.java) },
                ItemData("testView") { extStartActivity(TestViewActivity::class.java) },
                ItemData("ServiceTestActivity") { extStartActivity(ServiceTestActivity::class.java) }
        )
    }

    lateinit var popupWindow: PopupWindow
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        popupWindow = PopupWindow(this)

        rv_recyclerview.layoutManager = LinearLayoutManager(this)
        var pageAdapter = ListPageAdapter()
        rv_recyclerview.adapter = pageAdapter
        pageAdapter.setNewData(itemDatas)
        pageAdapter.setOnItemClickListener { adapter, view, position ->
            pageAdapter.getItem(position)!!.action()
        }
    }

    class ListPageAdapter : BaseQuickAdapter<ItemData, BaseViewHolder>(R.layout.item_home_page) {

        override fun convert(helper: BaseViewHolder, item: ItemData) {
            val textView = helper.itemView as TextView
            textView.text = item.text
        }
    }

    data class ItemData(var text: String, var action: () -> Unit)

}
