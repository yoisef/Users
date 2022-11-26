package com.madarsoft.users.utils

import androidx.recyclerview.widget.DiffUtil
import com.madarsoft.users.domain.models.User

class UserDiffCallback(private val mOldDayList: List<User>, private val mNewDayList: List<User>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldDayList.size
    }

    override fun getNewListSize(): Int {
        return mNewDayList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldDayList[oldItemPosition].id === mNewDayList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldone = mOldDayList[oldItemPosition]
        val newone = mNewDayList[newItemPosition]
        return oldone == newone
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        // Implement method if you're going to use ItemAnimator
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}