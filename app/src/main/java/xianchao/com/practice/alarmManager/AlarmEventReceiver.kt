package xianchao.com.practice.alarmManager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import xianchao.com.practice.LOG_TAG

class AlarmEventReceiver : BroadcastReceiver{
    constructor() : super()

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(LOG_TAG, "onReceive() called with: context = [" + context + "], intent = [" + intent + "]")
        PracticeAlarmActivity.consoleLog("onReceive: msg is ${intent!!.getStringExtra("msg")}")
    }


}