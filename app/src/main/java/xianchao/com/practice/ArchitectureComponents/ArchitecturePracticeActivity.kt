package xianchao.com.practice.ArchitectureComponents

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import kotlinx.android.synthetic.main.activity_architecture_practice.*
import xianchao.com.basiclib.extension.extStartActivity
import xianchao.com.practice.ArchitectureComponents.liveData.PageViewModel
import xianchao.com.practice.ArchitectureComponents.liveData.createPageViewData
import xianchao.com.practice.MainActivity
import xianchao.com.practice.R
import java.lang.RuntimeException
import java.util.*

class ArchitecturePracticeActivity : AppCompatActivity() {

    lateinit var pageViewData: MutableLiveData<PageViewModel.PageViewData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_architecture_practice)


        pageViewData = createPageViewData()


//        pageViewData.observe(this, Observer<PageViewModel.PageViewData> {
//            // update UI
//            tv_lifecycle.text = it?.lifecycle
//        })

    }

    override fun onResume() {
        super.onResume()
        if (pageViewData.value == null) {
            pageViewData.value = PageViewModel.PageViewData()
        }
        pageViewData.value!!.lifecycle = "onResume"

        pageViewData.setValue(pageViewData.value)
    }

    override fun onStop() {
        super.onStop()

        if (pageViewData.value == null) {
            pageViewData.value = PageViewModel.PageViewData()
        }
        pageViewData.value!!.lifecycle = "onStop"

        pageViewData.setValue(pageViewData.value)
    }

}
