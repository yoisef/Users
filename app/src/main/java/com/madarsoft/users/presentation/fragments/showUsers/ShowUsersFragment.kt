package com.madarsoft.users.presentation.fragments.showUsers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.madarsoft.users.adapters.UsersAdapter
import com.madarsoft.users.R
import com.madarsoft.users.databinding.FragmentShowUsersBinding
import com.madarsoft.users.utils.Status
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ShowUsersFragment : Fragment() {

    private lateinit var mAdapter : UsersAdapter

    private val viewModel: ShowUsersViewModel by viewModels()
    lateinit var binding : FragmentShowUsersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_show_users, container, false)


        //setup  recyclerView to show saved users
        setupRecyclerView()
        //Observe get users operation & set recyclerView adapter data
        observeUsers()

        //Get Saved Users From Database
        viewModel.getUsers()


        return binding.root
    }

    private fun setupRecyclerView()
    {
        binding.recyclerUsers.layoutManager = LinearLayoutManager(context)
        mAdapter = UsersAdapter(arrayListOf())
        binding.recyclerUsers.adapter =mAdapter
    }

    private fun observeUsers()
    {
        lifecycleScope.launchWhenStarted {
            viewModel.getUsersStatus.collect{

                it ->
                when(it.status)
                {
                    Status.SUCCESS ->{
                        binding.progressBar.visibility=View.GONE
                        it.data?.let { it1 -> mAdapter.updateDayListItems(it1) }
                    }
                    Status.LOADING ->{
                        binding.progressBar.visibility=View.VISIBLE
                    }
                    Status.ERROR ->{
                        binding.progressBar.visibility=View.GONE

                    }
                }

            }

        }

    }

}