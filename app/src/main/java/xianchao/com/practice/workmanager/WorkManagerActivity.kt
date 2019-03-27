package xianchao.com.practice.workmanager

import android.app.Activity
import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.work.*
import kotlinx.android.synthetic.main.activity_work_manager.*
import xianchao.com.practice.R
import xianchao.com.practice.jobscheduler.JobSchedulerActivity

class WorkManagerActivity : AppCompatActivity() {


    companion object {
        var tvConsole: TextView? = null
        var activitySelf: Activity? = null
        fun consoleLog(msg: String) {
            activitySelf!!.runOnUiThread {

                if (tvConsole != null) {
                    tvConsole!!.append("\\\n" + msg)
                }
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)
        tvConsole = tv_console
        activitySelf = this
        btn_start_work.setOnClickListener {

            val inputdata = Data.Builder().putString(WORKER_USERINFO, "xianchao|男").build()

            val build = OneTimeWorkRequest
                    .Builder(AnalysisWorker::class.java)
                    .setInputData(inputdata)
                    .build()

            consoleLog("create OneTimeWorkRequest")
            consoleLog("work enqueue")

            WorkManager.getInstance().getStatusById(build.getId())//通过workRequest的id获取
                    .observe(this, object : Observer<WorkStatus> {
                        override fun onChanged(t: WorkStatus?) {
                            var data = t?.getOutputData()?.getString(WORKER_RESULT, "");

                            consoleLog("getOutputData:" + data)
                        }

                    })

            WorkManager.getInstance().enqueue(build)
//            var workContinuation1 = WorkManager.getInstance().beginWith(work1)
//            var workContinuation2 = WorkManager.getInstance().beginWith(work2)
//            WorkContinuation.combine(workContinuation1,workContinuation2)

        }

    }
}
