package com.sun.moviedb.view.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import androidx.annotation.Keep
import com.google.android.material.shape.CutCornerTreatment
import com.google.android.material.shape.MaterialShapeDrawable
import com.sun.moviedb.R

/**
 * Created by nguyenxuanhoi on 2019-08-01.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
class TopLeftCutBackgroundView : View {
    private val shapeDrawable = MaterialShapeDrawable()

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.TopLeftCutBackgroundView)
        color = a.getColor(R.styleable.TopLeftCutBackgroundView_backgroundColor, Color.MAGENTA)
        maxCutSize = a.getDimension(R.styleable.TopLeftCutBackgroundView_topLeftCutSize, 0f)
        a.recycle()
        background = shapeDrawable
        syncCutSize()
    }

    @set:Keep
    var color: Int = Color.MAGENTA
        set(value) {
            shapeDrawable.setTint(value)
            field = value
        }

    private var maxCutSize: Float = 0f
        set(value) {
            field = value
            syncCutSize()
        }

    @set:Keep
    var cutProgress: Float = 1f
        set(value) {
            field = value
            syncCutSize()
        }
    private fun cutSize(startValue: Float, endValue: Float, fraction: Float) = startValue + fraction * (endValue - startValue)

    private fun syncCutSize() {
        val shapeModel = shapeDrawable.shapeAppearanceModel
        val newCutSize = cutSize(0f, maxCutSize, cutProgress)
        if (newCutSize != shapeModel.topLeftCorner?.cornerSize) {
            shapeModel.topLeftCorner = CutCornerTreatment(newCutSize)
            shapeDrawable.shapeAppearanceModel = shapeModel
        }
    }
}
