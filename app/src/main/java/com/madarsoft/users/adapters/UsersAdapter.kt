package com.madarsoft.users.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.madarsoft.users.databinding.UserRawBinding
import com.madarsoft.users.domain.models.User
import com.madarsoft.users.utils.UserDiffCallback


class UsersAdapter (var days: ArrayList<User>) :  RecyclerView.Adapter<UsersAdapter.SearchViewHolder>() {

    private lateinit var binding: UserRawBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {

        binding = UserRawBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return SearchViewHolder(binding)

    }

    override fun getItemCount() = days.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val city = days[position]
        holder.bind(city)

    }


    fun updateDayListItems(newDays: List<User>) {
        val diffCallback = UserDiffCallback(this.days, newDays)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.days.clear()
        this.days.addAll(newDays)
        diffResult.dispatchUpdatesTo(this)
    }


    inner class SearchViewHolder(private val binding: UserRawBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(userDetails: User) {
            binding.user = userDetails
        }


    }
}

