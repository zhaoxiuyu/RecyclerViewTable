package com.sendinfo.recyclerviewtable.no_nested

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager

class CustomLinearLayoutManager : LinearLayoutManager {

    private var isScrollHorizontally = false
    private var isScrollVertically = false

    constructor(context: Context?) : super(context) {}
    constructor(
        context: Context?,
        orientation: Int,
        reverseLayout: Boolean
    ) : super(context, orientation, reverseLayout) {
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
    }

    fun setScrollEnabled(isScrollHorizontally: Boolean = true, isScrollVertically: Boolean = true) {
        this.isScrollHorizontally = isScrollHorizontally
        this.isScrollVertically = isScrollVertically
    }

    override fun canScrollVertically(): Boolean {
        return isScrollVertically && super.canScrollVertically()
    }

    override fun canScrollHorizontally(): Boolean {
        return isScrollHorizontally && super.canScrollHorizontally()
    }

}