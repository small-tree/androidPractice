package xianchao.com.practice.jobscheduler

import android.annotation.TargetApi
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_job_scheduler.*
import xianchao.com.basiclib.utils.checkIsEmpty
import xianchao.com.basiclib.utils.checkNotEmpty
import xianchao.com.practice.R

const val JOB_SERVICE_DUIATION = "JOB_SERVICE_DUIATION"
const val JOB_SERVICE_MSG = "JOB_SERVICE_MSG"


fun consoleLog(msg: String) {
    if (JobSchedulerActivity.tvConsole != null) {
        JobSchedulerActivity.tvConsole!!.text = JobSchedulerActivity.tvConsole!!.text.toString() + "\\\n" + msg
    }
}

class JobSchedulerActivity : AppCompatActivity() {


    companion object {
        var tvConsole: TextView? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_scheduler)
        btn_start_service.setOnClickListener {
            startJob()
        }

        btn_cancel_service.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                var jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
                jobScheduler.cancelAll()
            }
        }
    }


    var jobId = 0

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    fun startJob() {
        tvConsole = tv_console

        var jobService = ComponentName(this, PracticeJobService::class.java)
        val builder = JobInfo.Builder(jobId++, jobService)
        builder.setMinimumLatency(2000L)
        builder.setOverrideDeadline(2000L)
        val bundle = PersistableBundle()
        if (et_duration.text.toString().checkNotEmpty()) {
            bundle.putLong(JOB_SERVICE_DUIATION, et_duration.text.toString().toLong())
        }
        bundle.putString(JOB_SERVICE_MSG, "这是参数")
        builder.setExtras(bundle)

        consoleLog("启动 服务-->  ${2000} 毫秒后执行")
        var jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.schedule(builder.build())

    }
}
