package com.sendinfo.recyclerviewtable.no_nested

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.sendinfo.recyclerviewtable.R
import com.sendinfo.recyclerviewtable.RoomInfo

/**
 * 多类型适配器。
 * 因为页面有 多个 recyclerView ，公用一个 适配器就可以了，根据不用的 viewType 展示不同的布局
 */
class NoNestedAdapter : BaseMultiItemQuickAdapter<RoomInfo, BaseViewHolder>(null) {
    init {
        // 日期
        addItemType(RoomInfo.item_room_date, R.layout.item_room_date)
        // 房型
        addItemType(RoomInfo.item_room_type, R.layout.item_room_type)
        // 订单
        addItemType(RoomInfo.item_room_center, R.layout.item_room_center)
    }

    override fun convert(baseViewHolder: BaseViewHolder, roomInfo: RoomInfo) {
        when (baseViewHolder.itemViewType) {
            RoomInfo.item_room_date -> {
                baseViewHolder.setText(R.id.tvTop, roomInfo.roomDate)
                baseViewHolder.setText(R.id.tvBottom, roomInfo.roomWeek)
            }
            RoomInfo.item_room_type -> {
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
