package com.sendinfo.recyclerviewtable.no_nested

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sendinfo.recyclerviewtable.DataUtils
import com.sendinfo.recyclerviewtable.R
import com.sendinfo.recyclerviewtable.RoomInfo
import kotlinx.android.synthetic.main.activity_no_nested.*

class NoNestedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_nested)

        // 左上角
        val view = LayoutInflater.from(this).inflate(R.layout.item_left_top_info, flFirst, false)
        flFirst.addView(view)

        // 左侧列表适配器
        val leftAdapter = NoNestedAdapter()
        rvLeft.layoutManager = LinearLayoutManager(this)
        rvLeft.adapter = leftAdapter
        leftAdapter.setNewInstance(DataUtils.getDatas(RoomInfo.item_room_date, 14))

        // 顶部列表适配器
        val headerAdapter = NoNestedAdapter()
        rvHeader.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvHeader.adapter = headerAdapter
        headerAdapter.setNewInstance(DataUtils.getDatas(RoomInfo.item_room_type, 14))

        // 中间列表适配器
        val contentAdapter = NoNestedAdapter()
        rvContent.layoutManager = GridLayoutManager(this, 15, LinearLayoutManager.VERTICAL, false)
        rvContent.adapter = contentAdapter
        contentAdapter.setNewInstance(DataUtils.getDatas(RoomInfo.item_room_center, 224))

        // HorizontalScrollView 滑动就取消 两个列表的滑动监听
        nhsv.setScrollViewListener(object : NoHorizontalScrollView.ScrollViewListener {
            override fun onScrollChanged(
                scrollView: NoHorizontalScrollView,
                x: Int,
                y: Int,
                oldx: Int,
                oldy: Int
            ) {
                rvLeft.removeOnScrollListener(leftScroll)
                rvContent.removeOnScrollListener(contentScroll)
            }
        })

        // 添加触摸事件
        rvLeft.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            private var mLastY: Int = 0
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                if (rv.scrollState == RecyclerView.SCROLL_STATE_IDLE) {
                    onTouchEvent(rv, e)
                }
                return false
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
                // 若是手指按下的动作，且另一个列表处于空闲状态
                if (e.action == MotionEvent.ACTION_DOWN && rvContent.scrollState == RecyclerView.SCROLL_STATE_IDLE) {
                    // 记录当前另一个列表的y坐标并对当前列表设置滚动监听
                    mLastY = rv.scrollY
                    rv.addOnScrollListener(leftScroll)
                } else {
                    // 若当前列表原地抬起手指时，移除当前列表的滚动监听
                    if (e.action == MotionEvent.ACTION_UP && rv.scrollY == mLastY) {
                        rv.removeOnScrollListener(leftScroll)
                    }
                }
            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
            }
        })

        rvContent.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            private var mLastY: Int = 0

            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                if (rv.scrollState == RecyclerView.SCROLL_STATE_IDLE) {
                    onTouchEvent(rv, e)
                }
                return false
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
                if (e.action == MotionEvent.ACTION_DOWN && rvLeft.scrollState == RecyclerView.SCROLL_STATE_IDLE) {
                    mLastY = rv.scrollY
                    rv.addOnScrollListener(contentScroll)
                } else {
                    if (e.action == MotionEvent.ACTION_UP && rv.scrollY == mLastY) {
                        rv.removeOnScrollListener(contentScroll)
                    }
                }
            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
            }
        })
    }

    // 左侧 的 滑动监听
    private val leftScroll = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            // 左侧列表滑动时带动中间订单滑动
            rvContent.scrollBy(dx, dy)
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            // 空闲状态就取消当前的滑动监听
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                recyclerView.removeOnScrollListener(this)
            }
        }
    }

    // 中间内容体，订单滑动监听
    private val contentScroll = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            // 中间内容列表滑动带动左侧列表滑动
            rvLeft.scrollBy(dx, dy)
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            // 空闲状态就取消当前的滑动监听
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                recyclerView.removeOnScrollListener(this)
            }
        }
    }

}
