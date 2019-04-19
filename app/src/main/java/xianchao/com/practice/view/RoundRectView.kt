package xianchao.com.practice.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class RoundRectView : View {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        var rect = Rect(width / 4, height / 4, width / 4 * 3, height / 4 * 3)
        val paint = Paint()
        paint.color = Color.BLUE
        var path = Path()
        path.addRect(RectF(rect), Path.Direction.CCW)
        path.addRect(RectF(0f, 0f, width * 1.0f, width * 1.0f), Path.Direction.CCW)
        canvas.drawPath(path, paint)


    }

}