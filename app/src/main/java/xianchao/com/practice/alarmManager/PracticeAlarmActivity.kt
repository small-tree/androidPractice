package xianchao.com.practice.alarmManager

import android.app.AlarmManager
import android.app.Notification
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_practice_alarm.*
import xianchao.com.basiclib.base.BaseActivity
import xianchao.com.basiclib.utils.BundleUtils
import xianchao.com.basiclib.utils.checkIsEmpty
import xianchao.com.practice.R
import xianchao.com.practice.jobscheduler.JobSchedulerActivity

class PracticeAlarmActivity : AppCompatActivity() {


    companion object {
        var tvConsole: TextView? = null
        fun consoleLog(msg: String) {
            if (tvConsole != null) {
                tvConsole!!.text = tvConsole!!.text.toString() + "\\\n" + msg
            }
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initView()
    }

    fun getLayoutId(): Int {
        return R.layout.activity_practice_alarm
    }

    fun initView() {

        tvConsole = tv_console


        btn_task.setOnClickListener {


            val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

            val intent = Intent()
            intent.component = ComponentName(this, AlarmEventReceiver::class.java)
            intent.action = "self_borad_cast"
            intent.putExtras(BundleUtils.createWith("msg", "msg"))

            val broadcast = PendingIntent.getBroadcast(this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT)

            consoleLog("AlarmManager create...")

            if (et_delay.text.toString().checkIsEmpty()) {
                consoleLog("AlarmManager send when ${0}s after")
                alarmManager.set(AlarmManager.RTC, System.currentTimeMillis(), broadcast)
            } else {
                var time = et_delay.text.toString().toInt()
                consoleLog("AlarmManager send when ${time}s after")
                alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + (time * 1000), broadcast)
            }
        }


        btn_wakeup_task.setOnClickListener {

            val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

            val intent = Intent()
            intent.component = ComponentName(this, AlarmEventReceiver::class.java)
            intent.action = "self_borad_cast"
            intent.putExtras(BundleUtils.createWith("msg", "msg"))

            val broadcast = PendingIntent.getBroadcast(this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT)

            consoleLog("AlarmManager create...")

            if (et_delay.text.toString().checkIsEmpty()) {
                consoleLog("AlarmManager send when ${0}s after")
                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), broadcast)
            } else {
                var time = et_delay.text.toString().toInt()
                consoleLog("AlarmManager send when ${time}s after")
                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (time * 1000), broadcast)
            }
        }

    }

}
