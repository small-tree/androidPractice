package xianchao.com.practice.workmanager

import android.view.View
import android.view.ViewGroup
import androidx.work.Data
import androidx.work.WorkRequest
import androidx.work.Worker
import io.reactivex.Scheduler
import xianchao.com.basiclib.utils.checkIsEmpty
import java.lang.StringBuilder

const val WORKER_USERINFO = "WORKER_USERINFO"
const val WORKER_RESULT = "WORKER_RESULT"


class AnalysisWorker : Worker {

    constructor() : super()
    lateinit var a :View

    override fun doWork(): WorkerResult {

        WorkManagerActivity.consoleLog("doWork on " + Thread.currentThread().name)
        val string = inputData.getString(WORKER_USERINFO, "")

        WorkManagerActivity.consoleLog("AnalysisWorker-inputdata::" + string)
        if (string.checkIsEmpty() || string.split("|").size < 2) {
            WorkManagerActivity.consoleLog("doWork...failure")
            return WorkerResult.FAILURE
        } else {
            val split = string.split("|")
            val toString = StringBuilder()
                    .append("名字：")
                    .append(split.get(0))
                    .append(" 性别：")
                    .append(split.get(1))
                    .toString()

            outputData = Data.Builder().putString(WORKER_RESULT, toString).build()
            WorkManagerActivity.consoleLog("doWork...success")
            return WorkerResult.SUCCESS
        }

    }
}