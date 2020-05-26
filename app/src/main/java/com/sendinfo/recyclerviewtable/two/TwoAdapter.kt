package com.sendinfo.recyclerviewtable.two

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.sendinfo.recyclerviewtable.DataUtils
import com.sendinfo.recyclerviewtable.R
import com.sendinfo.recyclerviewtable.RoomInfo

/**
 * 多类型适配器。
 * 因为页面有 多个 recyclerView ，公用一个 适配器就可以了，根据不用的 viewType 展示不同的布局
 */
class TwoAdapter : BaseMultiItemQuickAdapter<RoomInfo, BaseViewHolder>(null) {

    var mRVScrollListener: RVScrollListener? = null

    init {
        // 日期
        addItemType(RoomInfo.item_room_date, R.layout.item_room_date)
        // 一行
        addItemType(RoomInfo.item_room_row, R.layout.item_content_row)
        // 一行中的订单
        addItemType(RoomInfo.item_room_center, R.layout.item_room_center)
    }

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        super.onItemViewHolderCreated(viewHolder, viewType)

        // 内容体中的每个item都有recyclerView,取出来单独填充数据和添加同步滑动机制
        if (viewType == RoomInfo.item_room_row) {
            val mAdapter = TwoAdapter()
            val lineList = viewHolder.getView<RecyclerView>(R.id.recyclerLine)
            lineList.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            lineList.adapter = mAdapter
            mAdapter.setNewInstance(DataUtils.getDatas(RoomInfo.item_room_center))

            mRVScrollListener?.addRecyclerView(lineList)
        }
    }

    override fun convert(baseViewHolder: BaseViewHolder, roomInfo: RoomInfo) {
        when (baseViewHolder.itemViewType) {
            RoomInfo.item_room_date -> {
                baseViewHolder.setText(R.id.tvTop, roomInfo.roomDate)
                baseViewHolder.setText(R.id.tvBottom, roomInfo.roomWeek)
            }
            RoomInfo.item_room_row -> {
                baseViewHolder.setText(R.id.tvTop, roomInfo.roomName)
                baseViewHolder.setText(R.id.tvBottom, roomInfo.roomType)
            }
            RoomInfo.item_room_center -> {
                baseViewHolder.setText(R.id.tvTop, roomInfo.roomGuestName)
                baseViewHolder.setText(R.id.tvBottom, "${roomInfo.roomBegin}")
            }
        }
    }

}
