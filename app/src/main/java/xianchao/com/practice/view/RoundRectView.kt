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
//
        var rect = Rect(width / 4, height / 4, width / 4 * 3, height / 4 * 3)
        val paint = Paint()
        paint.color = Color.BLUE
        var path = Path()
        path.addRoundRect(RectF(rect),50f,50f, Path.Direction.CCW)
//        path.addRect(RectF(0f, 0f, width * 1.0f, height * 1.0f), Path.Direction.CCW)

        path.lineTo(0f,0f)
        path.lineTo(width.toFloat(),0f)
        path.lineTo(width.toFloat(),height.toFloat())
        path.lineTo(0f,height.toFloat())
        path.lineTo(0f,0f)

        canvas.drawPath(path, paint)


        var frame = Rect(0, 0, width, height)
        // 绘制取景框外的暗灰色的表面，分四个矩形绘制
        paint.color = Color.parseColor("#44000000")


    }

}