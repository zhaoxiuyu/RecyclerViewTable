package com.sendinfo.recyclerviewtable.no_nested

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

/**
 * 处理被 HorizontalScrollView 嵌套时，手指快速上下滚动事件被抢先处理问题
 */
class NoRecyclerView : RecyclerView {

//    private var mDownX: Float = 0.toFloat()
//    private var mDownY: Float = 0.toFloat()

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    /**
     * 事件分发
     * dispatchTouchEvent->onInterceptTouchEvent->onTouchEvent
     */
//    override fun dispatchTouchEvent(e: MotionEvent): Boolean {
//        when (e.action) {
//            MotionEvent.ACTION_DOWN -> {
//                mDownX = e.rawX
//                mDownY = e.rawY
//            }
//            MotionEvent.ACTION_MOVE -> {
//                val rawX = e.rawX
//                val rawY = e.rawY
//                val dx = Math.abs(mDownX - rawX)
//                val dy = Math.abs(mDownY - rawY)
//                if (dy > 5 && dx > 5 && dx > dy) {
//                    parent.requestDisallowInterceptTouchEvent(false)
//                } else {
    //请求所有父控件及祖宗控件不要拦截事件
//                    parent.requestDisallowInterceptTouchEvent(true)
//                }
//            }
//        }
//        return super.dispatchTouchEvent(e)
//    }

}