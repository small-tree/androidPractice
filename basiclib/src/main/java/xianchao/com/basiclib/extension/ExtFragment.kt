package xianchao.com.basiclib.extension

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment


inline fun Fragment.extStartActivity(activity: Class<out Activity>) {
    val intent = Intent(getActivity(), activity)
    startActivity(intent)
}

inline fun Fragment.extStartActivity(activity: Class<out Activity>, bundle: Bundle) {
    val intent = Intent(getActivity(), activity)
    intent.putExtras(bundle)
    startActivity(intent)
}