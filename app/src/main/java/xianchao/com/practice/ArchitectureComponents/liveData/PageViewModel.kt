package xianchao.com.practice.ArchitectureComponents.liveData

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PageViewModel : ViewModel {
    public val pageViewData: MutableLiveData<PageViewData> by lazy { MutableLiveData<PageViewData>() }

    constructor() : super()


    class PageViewData {
        var lifecycle = ""
    }
}


fun createPageViewData(): MutableLiveData<PageViewModel.PageViewData> {
    return MutableLiveData<PageViewModel.PageViewData>()
}
