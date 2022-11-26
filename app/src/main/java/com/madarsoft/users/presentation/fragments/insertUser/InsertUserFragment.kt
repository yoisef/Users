package com.madarsoft.users.presentation.fragments.insertUser

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.madarsoft.users.R
import com.madarsoft.users.databinding.FragmentInsertUserBinding
import com.madarsoft.users.domain.models.User
import com.madarsoft.users.utils.Resource
import com.madarsoft.users.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*


@AndroidEntryPoint
class InsertUserFragment : Fragment() {


    private lateinit var binding: FragmentInsertUserBinding
    private val viewModel: InsertUserViewModel by viewModels()
    private lateinit var textWatcher :TextWatcher

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_insert_user, container, false)

        initialization()
        observeInsertionState()

        return binding.root
    }


    private fun observeInsertionState()
    {
        /*
        collect data from SharedStateFlow to handle Single Events by param (reply=0) inside
        lifecycle coroutine scope with 'launchWhenStarted' to make flow aware with fragment lifecycle
         */
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.insertStatus.collect{
                    it ->
                when(it.status)
                {
                    Status.SUCCESS ->{

                        handleSuccessStatus()
                    }
                    Status.ERROR ->{

                        handleErrorStatus(it)
                    }
                    Status.LOADING ->{
                        binding.progressBar.visibility=View.VISIBLE


                    }
                }

            }
        }
    }

    private fun handleErrorStatus(it : Resource<Long>)
    {
        binding.progressBar.visibility=View.GONE
        if (it.data!=null)
        {
            Toast.makeText(context,it.message,Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleSuccessStatus()
    {
        binding.progressBar.visibility=View.GONE
        binding.inputEditTextUsername.text=null
        binding.inputEditTextUserage.text=null
        binding.inputEditTextJobTitle.text=null
        binding.genderRadioGroup.clearCheck()
        findNavController().navigate(R.id.action_insertUser_to_showUsersFragment)

    }
    private fun initialization()
    {

        //1-text watcher for step 2
        textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.inputLayoutUserAge.error=null
                binding.inputLayoutUsername.error=null
                binding.inputEditTextJobTitle.error=null


            }
        }
        //2-setup text fields with text watcher in step (1)
        binding.inputEditTextUsername.requestFocus()
        binding.inputEditTextUsername.addTextChangedListener(textWatcher)
        binding.inputEditTextUserage.addTextChangedListener(textWatcher)
        binding.inputEditTextJobTitle.addTextChangedListener(textWatcher)

        //3- handle save Button click
        binding.saveBtn.setOnClickListener {

            validateAndInsertUser()
        }

        binding.genderRadioGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { _, _ ->
            binding.maleRadioButton.error=null
            binding.femaleRadioButton.error=null
        })

    }
    private fun validateAndInsertUser()  {
        //get Values from Text Inputs
        val name = binding.inputEditTextUsername.text.toString()
        val age = binding.inputEditTextUserage.text.toString()
        val jobTitle = binding.inputEditTextJobTitle.text.toString()
        val gender = binding.genderRadioGroup.checkedRadioButtonId

        //validate empty state for all inputs
        if (TextUtils.isEmpty(name)){ binding.inputLayoutUsername.error=getString(R.string.name_empty);return }
        if (TextUtils.isEmpty(age)){ binding.inputLayoutUserAge.error=getString(R.string.age_empty);return }
        if (TextUtils.isEmpty(jobTitle)){ binding.inputEditTextJobTitle.error=getString(R.string.jobTitle_empty);return }
        if (gender==-1){binding.maleRadioButton.error=(getString(R.string.select_gender))
            binding.femaleRadioButton.error=(getString(R.string.select_gender));return}

        // Get selected radioButton
        val radioButton = binding.root.findViewById(gender) as RadioButton
        //Get Selected Value
        val selectedGender= radioButton.text.toString()
        //Create User Object
        val user=User(name = name, age = age, jobTitle = jobTitle, gender =selectedGender)
        //Insert Into Room Local DataBase
        viewModel.insertUser(user)




    }


}