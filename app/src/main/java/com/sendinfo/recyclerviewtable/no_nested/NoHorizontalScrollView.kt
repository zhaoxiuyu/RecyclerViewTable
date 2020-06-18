package com.sendinfo.recyclerviewtable.no_nested

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.HorizontalScrollView

/**
 * 自定义 HorizontalScrollView，向外提供滑动监听功能
 */
class NoHorizontalScrollView : HorizontalScrollView {

    constructor(context: Context) : super(context)

    constructor(
        context: Context, attrs: AttributeSet,
        defStyle: Int
    ) : super(context, attrs, defStyle)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    private var calCount = 0

    // 如果水平滑动 就取消两个列表的滑动监听
    override fun onScrollChanged(x: Int, y: Int, oldx: Int, oldy: Int) {
        super.onScrollChanged(x, y, oldx, oldy)

        val view = getChildAt(0)
        if (x == 0) {
            Log.d("MyHorizontalScrollView", "最左侧")
            mLeftRight?.left(true)
        } else {
            mLeftRight?.left(false)
        }

        // 视图的宽度+滚动视图显示的左边缘部分
        if (width + scrollX == view.width) {
            calCount++
            if (calCount == 1) {
                Log.d("MyHorizontalScrollView", "最右侧")
                mLeftRight?.right(true)
            }
        } else {
            calCount = 0
            mLeftRight?.right(false)
        }

    }

    // 滚动到右边
    fun focusRIGHT() {
        Log.d("RoomStateFragment", "滚动到底部")
        fullScroll(FOCUS_RIGHT)
    }

    // 滚动到左边
    fun focusLEFT() {
        Log.d("RoomStateFragment", "滚动到顶部")
        fullScroll(FOCUS_LEFT)
    }

    /**
     * 最左边和最右边的回调,true 最边缘了，false 不是最边缘
     */
    private var mLeftRight: LeftRight? = null

    fun setLeftRight(mLeftRight: LeftRight) {
        this.mLeftRight = mLeftRight
    }

    interface LeftRight {
        fun left(isLeft: Boolean)
        fun right(isRight: Boolean)
    }

}