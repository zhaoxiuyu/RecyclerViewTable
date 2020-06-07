package com.sendinfo.recyclerviewtable.no_nested

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils
import com.sendinfo.recyclerviewtable.DataUtils
import com.sendinfo.recyclerviewtable.R
import com.sendinfo.recyclerviewtable.RoomInfo
import kotlinx.android.synthetic.main.activity_no_nested.*

/**
 * 多个recyclerView设置滚动监听，垂直 水平 滚动同时带动其他recyclerView滚动
 */
class NoNestedActivity : AppCompatActivity() {

    private var isScroll = false

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

        initLisenter()
    }

    private fun initLisenter() {
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

        var isAdd = false
        // 添加触摸事件
        rvLeft.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            private var mLastY: Int = 0
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {

                // 当前Rv处于空闲状态
                if (rv.scrollState == RecyclerView.SCROLL_STATE_IDLE) {
                    onTouchEvent(rv, e)
                    return false
                }
                if (rvContent.scrollState != RecyclerView.SCROLL_STATE_IDLE) {
                    onTouchEvent(rv, e)
                    return true
                }
                return false
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
//                Log.d("NoNestedActivity", "onTouchEvent")

                when (e.action) {
                    MotionEvent.ACTION_DOWN -> {
                        Log.d("NoNestedActivity", "左侧 按下")

                        rvLeft.stopScroll()
                        rvContent.stopScroll()

                        rvLeft.removeOnScrollListener(leftScroll)
                        rvContent.removeOnScrollListener(contentScroll)

                        rvLeft.addOnScrollListener(leftScroll)
                    }
                    MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                        Log.d("NoNestedActivity", "左侧 抬起 或 取消")

                        rvLeft.removeOnScrollListener(leftScroll)
                    }
                }

//                // 若是手指按下的动作，且中间列表处于空闲状态
//                if (e.action == MotionEvent.ACTION_DOWN && rvContent.scrollState == RecyclerView.SCROLL_STATE_IDLE) {
//                    // 记录当前列表的y坐标,对当前列表设置滚动监听
//                    mLastY = rv.scrollY
//                    // 左侧列表滑动的时候，移除中间列表滑动监听
//                    rvContent.removeOnScrollListener(contentScroll)
//                    // 注册左侧列表滑动监听
//                    rv.addOnScrollListener(leftScroll)
//                    Log.d("NoNestedActivity", "注册滑动监听,移除其他rv滑动监听")
//                } else {
//                    // 当前列表抬起手指时，移除当前列表的滚动监听
//                    if (e.action == MotionEvent.ACTION_UP && rv.scrollY == mLastY) {
//                        rv.removeOnScrollListener(leftScroll)
//                        Log.d("NoNestedActivity", "移除监听")
//                    } else {
//                        Log.d("NoNestedActivity", "不作处理")
//                    }
//                }
            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
            }
        })

        rvContent.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            private var mLastY: Int = 0

            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                // 当前Rv处于空闲状态
                if (rv.scrollState == RecyclerView.SCROLL_STATE_IDLE) {
                    onTouchEvent(rv, e)
                }
                return false
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
                when (e.action) {
                    MotionEvent.ACTION_DOWN -> {
                        Log.d("NoNestedActivity2", "中间 按下")

                        rvLeft.stopScroll()
                        rvContent.stopScroll()

                        rvLeft.removeOnScrollListener(leftScroll)
                        rvContent.removeOnScrollListener(contentScroll)

                        rvContent.addOnScrollListener(contentScroll)
                    }
                    MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                        Log.d("NoNestedActivity2", "中间 抬起 或 取消")

                        rvContent.removeOnScrollListener(contentScroll)
                    }
                }

//                // 若是手指按下的动作，且左侧列表处于空闲状态
//                if (e.action == MotionEvent.ACTION_DOWN && rvLeft.scrollState == RecyclerView.SCROLL_STATE_IDLE) {
//                    // 记录当前列表的y坐标,对当前列表设置滚动监听
//                    mLastY = rv.scrollY
//                    // 中间列表滑动的时候，移除左侧列表滑动监听
//                    rvLeft.removeOnScrollListener(leftScroll)
//                    // 注册中间列表滑动监听
//                    rv.addOnScrollListener(contentScroll)
//                } else {
//                    // 当前列表抬起手指时，移除当前列表的滚动监听
//                    if (e.action == MotionEvent.ACTION_UP && rv.scrollY == mLastY) {
//                        rv.removeOnScrollListener(contentScroll)
//                    }
//                }
            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
            }
        })
    }

    // 左侧 的 滑动监听
    private val leftScroll = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            Log.d("NoNestedActivity", "左侧 滑动 onScrolled")
            // 左侧列表滑动时带动中间订单滑动
            rvContent.scrollBy(dx, dy)
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            // 空闲状态就取消当前的滑动监听
            Log.d("NoNestedActivity", "左侧 ${RecyclerView.SCROLL_STATE_IDLE}")
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                recyclerView.removeOnScrollListener(this)
            }
        }
    }

    // 中间内容体，订单滑动监听
    private val contentScroll = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            // 中间内容列表滑动带动左侧列表滑动
            Log.d("NoNestedActivity2", "中间 滑动 onScrolled")
            rvLeft.scrollBy(dx, dy)
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            // 空闲状态就取消当前的滑动监听
            Log.d("NoNestedActivity2", "中间 ${RecyclerView.SCROLL_STATE_IDLE}")
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                recyclerView.removeOnScrollListener(this)
            }
        }
    }

}
