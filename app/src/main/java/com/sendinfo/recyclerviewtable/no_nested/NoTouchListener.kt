package com.sendinfo.recyclerviewtable.no_nested

import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

/**
 * recyclerView 列表触摸监听
 */
open class NoTouchListener : RecyclerView.OnItemTouchListener {

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        // 当前Rv处于空闲状态,就调用onTouchEvent重置一下监听
        // 如果不是空闲状态，就说明之前的滚动监听还存在，可以继续同步滚动
        if (rv.scrollState == RecyclerView.SCROLL_STATE_IDLE) {
            onTouchEvent(rv, e)
        }
        return false
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
    }

}