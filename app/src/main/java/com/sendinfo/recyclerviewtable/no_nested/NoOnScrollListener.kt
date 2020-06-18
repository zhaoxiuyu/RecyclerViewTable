package com.sendinfo.recyclerviewtable.no_nested

import android.util.Log
import androidx.recyclerview.widget.RecyclerView

/**
 * 监听处理 RecyclerView 的滑动事件,带动另一个一起滑动
 */
class NoOnScrollListener(private val rv: RecyclerView) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        /**
         * 在滑动的时候 带着其他的 RecyclerView 跟着一起滑动
         */
        rv.scrollBy(dx, dy)
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
//     滑动完成处于空闲状态的时候就取消当前 RecyclerView 的滑动监听器
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//            recyclerView.removeOnScrollListener(this)
            Log.d("订单列表", "中间 抬起 或 取消")
        }
    }

}