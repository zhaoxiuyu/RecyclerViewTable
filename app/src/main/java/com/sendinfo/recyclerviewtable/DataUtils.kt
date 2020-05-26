package com.sendinfo.recyclerviewtable

import com.blankj.utilcode.constant.TimeConstants
import com.blankj.utilcode.util.TimeUtils
import java.util.ArrayList

object DataUtils {

    /**
     * 模拟数据
     * itemViewType item的类型
     * count 循环多少次
     */
    fun getDatas(itemViewType: Int, count: Int = 14): MutableList<RoomInfo> {

        val topDatas = ArrayList<RoomInfo>()
        val sdf = TimeUtils.getSafeDateFormat("MM-dd")
        val day = TimeConstants.DAY

        for (i in 0..count) {
            val rq = TimeUtils.getStringByNow(i.toLong(), sdf, day)
            val xq = TimeUtils.getChineseWeek(TimeUtils.getStringByNow(i.toLong(), day))
            val roomInfo = RoomInfo(itemViewType)

            roomInfo.roomDate = rq
            roomInfo.roomWeek = xq

            if (i % 2 == 0) {
                roomInfo.roomName = "AV 大床房"
                roomInfo.roomType = "300$i"

                roomInfo.roomGuestName = "入住"
                roomInfo.roomBegin = true
            } else {
                roomInfo.roomName = "SM 大床房"
                roomInfo.roomType = "200$i"

                roomInfo.roomGuestName = "空房"
                roomInfo.roomBegin = false
            }

            topDatas.add(roomInfo)
        }
        return topDatas
    }

}