package xianchao.com.practice.jobscheduler

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Handler
import android.support.annotation.RequiresApi
import android.util.Log
import xianchao.com.practice.LOG_TAG
import xianchao.com.practice.jobscheduler.JobSchedulerActivity.Companion.consoleLog
import java.io.FileDescriptor
import java.io.PrintWriter

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class PracticeJobService : JobService {
    constructor() : super()


    override fun onStopJob(params: JobParameters?): Boolean {
        Log.d(LOG_TAG, "onStopJob() called with: params = [" + params + "]")

        JobSchedulerActivity.consoleLog("onStopJob->>")
        return true
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        Log.d(LOG_TAG, "onStartJob() called with: params = [" + params + "]");


        consoleLog("onStartJob.....")
        consoleLog("param->>" + params!!.extras.getString(JOB_SERVICE_MSG))
        consoleLog("duration->>" + params!!.extras.getLong(JOB_SERVICE_DUIATION))

        Handler().postDelayed({
            jobFinished(params, false)
        }, params!!.extras.getLong(JOB_SERVICE_DUIATION))

        return false
    }


    override fun onConfigurationChanged(newConfig: Configuration?) {
        Log.d(LOG_TAG, "onConfigurationChanged() called with: newConfig = [" + newConfig + "]");
        super.onConfigurationChanged(newConfig)
    }

    override fun onRebind(intent: Intent?) {
        Log.d(LOG_TAG, "onRebind() called with: intent = [" + intent + "]");
        super.onRebind(intent)
    }

    override fun dump(fd: FileDescriptor?, writer: PrintWriter?, args: Array<out String>?) {
        super.dump(fd, writer, args)
        Log.d(LOG_TAG, "dump() called with: fd = [" + fd + "], writer = [" + writer + "], args = [" + args + "]");
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
        Log.d(LOG_TAG, "onStartCommand() called with: intent = [" + intent + "], flags = [" + flags + "], startId = [" + startId + "]");
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(LOG_TAG, "onCreate() called");
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Log.d(LOG_TAG, "onLowMemory() called");
    }

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
        Log.d(LOG_TAG, "onStart() called with: intent = [" + intent + "], startId = [" + startId + "]");
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        Log.d(LOG_TAG, "onTaskRemoved() called with: rootIntent = [" + rootIntent + "]");
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)

    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
        Log.d(LOG_TAG, "onUnbind() called with: intent = [" + intent + "]");
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_TAG, "onDestroy() called");
    }
}