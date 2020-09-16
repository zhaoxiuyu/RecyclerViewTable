package com.sendinfo.recyclerviewtable.two

import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils
import java.util.*

class RVScrollListener : RecyclerView.OnScrollListener(), View.OnTouchListener {

    private val hashSet = HashSet<RecyclerView>()

    // 第一个可见视图的位置
    private var firstPos = -1

    // 父视图给定子视图的右边缘偏移量
    private var firstOffset = -1

    /**
     * @param dx 水平滚动距离,dx > 0 左滑,dx < 0 右滑
     * @param dy 垂直滚动距离,dy > 0 上滑,dy < 0 下滑
     */
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        LogUtils.d("dx = $dx ; dy = $dy")

        val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
        // 返回第一个可见视图的适配器位置。此位置不包括上次布局传递后调度的适配器更改。
        val pos = linearLayoutManager.findFirstVisibleItemPosition()
        // 返回给定索引处的子视图
        val firstVisibleItem = linearLayoutManager.getChildAt(0)
        if (firstVisibleItem != null) {
            // 返回父视图中给定子视图的右边缘
            val firstRight = linearLayoutManager.getDecoratedRight(firstVisibleItem)
            for (rvSet in hashSet) {
                if (recyclerView != rvSet) {
                    val layoutManager = rvSet.layoutManager as LinearLayoutManager
                    if (layoutManager != null) {
                        firstPos = pos
                        firstOffset = firstRight
                        // 把 item 移动到 可见 item 的第一项,即使它已经在可见的 item 之中
                        // offset 表示 item 移动到第一项后跟 recyclerView 上下边界之间的距离(默认0)
                        layoutManager.scrollToPositionWithOffset(firstPos + 1, firstRight)
                    }
                }
            }
        }
    }

    fun addRecyclerView(recyclerView: RecyclerView) {
        // item改变不会影响RecyclerView的宽高，设置这个值可以避免重新计算
        recyclerView.setHasFixedSize(true)

        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        LogUtils.d("firstPos = $firstPos ; firstOffset = $firstOffset")
        if (layoutManager != null && firstPos > 0 && firstOffset > 0) {
            // 滚动到指定适配器的位置
            layoutManager.scrollToPositionWithOffset(firstPos + 1, firstOffset)
        }
        // 把RecyclerView添加到HashSet集合
        hashSet.add(recyclerView)

        recyclerView.setOnTouchListener(this)
        recyclerView.addOnScrollListener(this)
    }

    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        when (motionEvent.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> for (rv in hashSet) {
                rv.stopScroll()
            }
        }
        return false
    }

}