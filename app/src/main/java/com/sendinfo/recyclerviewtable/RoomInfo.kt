package com.sendinfo.recyclerviewtable

import com.chad.library.adapter.base.entity.MultiItemEntity

class RoomInfo(override val itemType: Int) : MultiItemEntity {

    companion object {
        // 日期
        const val item_room_date = 0
        // 房型
        const val item_room_type = 1
        // 订单
        const val item_room_center = 2


        // 一行
        const val item_room_row = 3
    }

    // 房型信息
    var roomType: String = ""
    var roomName: String = ""
    var roomId: Long = 0

    // 日期信息
    var roomDate: String = ""
    var roomWeek: String = ""
    var roomNumber: Int = 0

    // 具体数据
    var roomGuestName = ""
    var roomStatus = ""
    var roomBegin = false

}