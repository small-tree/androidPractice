package fanrong.cwvwalled.listener

import android.app.Dialog

interface FRDialogBtnListener {
    fun onCancel(dialog: Dialog)
    fun onConfirm(dialog: Dialog)
}