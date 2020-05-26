package com.sendinfo.recyclerviewtable.two

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sendinfo.recyclerviewtable.DataUtils
import com.sendinfo.recyclerviewtable.R
import com.sendinfo.recyclerviewtable.RoomInfo
import kotlinx.android.synthetic.main.activity_two.*

/**
 * 这个使用和 ScrollablePanel 这个库逻辑一样的
 * 内容体中的每个 Item 都嵌套了 RecyclerView
 * 每个 Item 中的 RecyclerView 和 顶部的 RecyclerView 添加到 map 中进行同步滑动
 */
class TwoActivity : AppCompatActivity() {

    private val mRVScrollListener by lazy { RVScrollListener() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two)

        // 左上角
        val view = LayoutInflater.from(this).inflate(R.layout.item_left_top_info, first_item, false)
        first_item.addView(view)

        // 顶部
        val headerAdapter = TwoAdapter()
        recyclerHeader.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerHeader.adapter = headerAdapter
        headerAdapter.setNewInstance(DataUtils.getDatas(RoomInfo.item_room_date))

        mRVScrollListener.addRecyclerView(recyclerHeader)

        // 主体部分，
        val contentAdapter = TwoAdapter()
        contentAdapter.mRVScrollListener = mRVScrollListener
        val contentDatas = mutableListOf<String>()
        for (i in 0..15) {
            contentDatas.add("我是房型$i")
        }
        recyclerCntent.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerCntent.adapter = contentAdapter
        contentAdapter.setNewInstance(DataUtils.getDatas(RoomInfo.item_room_row))

    }

}