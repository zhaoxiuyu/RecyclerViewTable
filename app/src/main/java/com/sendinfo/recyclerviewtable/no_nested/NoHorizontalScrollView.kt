package com.sendinfo.recyclerviewtable.no_nested

import android.content.Context
import android.widget.HorizontalScrollView
import android.util.AttributeSet

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

    fun setScrollViewListener(scrollViewListener: ScrollViewListener) {
        this.scrollViewListener = scrollViewListener
    }

    override fun onScrollChanged(x: Int, y: Int, oldx: Int, oldy: Int) {
        super.onScrollChanged(x, y, oldx, oldy)
        scrollViewListener?.onScrollChanged(this, x, y, oldx, oldy)
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