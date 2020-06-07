package com.sendinfo.recyclerviewtable.no_nested

import android.content.Context
import android.util.AttributeSet
import android.widget.HorizontalScrollView

/**
 * 自定义 HorizontalScrollView，向外提供滑动监听功能
 */
class NoHorizontalScrollView : HorizontalScrollView {

    private var scrollViewListener: ScrollViewListener? = null

    constructor(context: Context) : super(context)

    constructor(
        context: Context, attrs: AttributeSet,
        defStyle: Int
    ) : super(context, attrs, defStyle)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}


    override fun onScreenStateChanged(screenState: Int) {
        super.onScreenStateChanged(screenState)
    }

    override fun onScrollChanged(x: Int, y: Int, oldx: Int, oldy: Int) {
        super.onScrollChanged(x, y, oldx, oldy)
        scrollViewListener?.onScrollChanged(this, x, y, oldx, oldy)
    }

    fun setScrollViewListener(scrollViewListener: ScrollViewListener) {
        this.scrollViewListener = scrollViewListener
    }

    interface ScrollViewListener {
        fun onScrollChanged(
            scrollView: NoHorizontalScrollView,
            x: Int,
            y: Int,
            oldx: Int,
            oldy: Int
        )
    }

}