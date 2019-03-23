package xianchao.com.practice

import android.Manifest
import android.app.Service
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.app.job.JobService
import android.content.ComponentName
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.os.Messenger
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import xianchao.com.basiclib.extension.extStartActivity
import xianchao.com.practice.jobscheduler.JobSchedulerActivity
import xianchao.com.practice.jobscheduler.PracticeJobService
import java.util.logging.Handler
import java.util.logging.LogManager

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_job_service.setOnClickListener {
            extStartActivity(JobSchedulerActivity::class.java)
        }



    }

    var handler = object : android.os.Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
        }
    }

    override fun onStart() {
        super.onStart()
        val messenger = Messenger(handler)
        val intent = Intent(this, PracticeJobService::class.java)
        intent.putExtra("AAAAAAAA", messenger)
        startService(intent)
    }

    override fun onStop() {
        super.onStop()
        stopService(Intent(this, PracticeJobService::class.java))
    }
}
