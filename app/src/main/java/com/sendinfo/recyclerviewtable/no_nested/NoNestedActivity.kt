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
class NoNestedActivity : AppCompatActivity(), NoHorizontalScrollView.LeftRight {

    /**
     * 左侧 和 中间订单 RecyclerView 滑动监听
     */
    private val leftScroll by lazy { NoOnScrollListener(rvContent) }
    private val centerScroll by lazy { NoOnScrollListener(rvLeft) }

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

        // 注册是否水平滑动到最左侧和最右侧
        nhsv.setLeftRight(this)

        // 添加触摸事件
        rvLeft.addOnItemTouchListener(object : NoTouchListener() {
            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
                when (e.action) {
                    MotionEvent.ACTION_DOWN -> {
                        Log.d("房型列表", "左侧 按下")

                        rv.stopScroll()
                        rvContent.stopScroll()

                        rv.removeOnScrollListener(leftScroll)
                        rvContent.removeOnScrollListener(centerScroll)

                        rv.addOnScrollListener(leftScroll)
                    }
                    MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                        Log.d("房型列表", "左侧 抬起 或 取消")
                    }
                }
            }
        })
        rvContent.addOnItemTouchListener(object : NoTouchListener() {
            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
                when (e.action) {
                    MotionEvent.ACTION_DOWN -> {
                        Log.d("订单列表", "中间 按下")

                        rv.stopScroll()
                        rvLeft.stopScroll()

                        rv.removeOnScrollListener(centerScroll)
                        rvLeft.removeOnScrollListener(leftScroll)

                        rv.addOnScrollListener(centerScroll)
                    }
                    MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                        Log.d("订单列表", "中间 抬起 或 取消")
                    }
                }
            }
        })
    }

    override fun left(isLeft: Boolean) {
        LogUtils.d("滑动到了最左侧")
    }

    override fun right(isRight: Boolean) {
        LogUtils.d("滑动到了最右侧")
    }

}
