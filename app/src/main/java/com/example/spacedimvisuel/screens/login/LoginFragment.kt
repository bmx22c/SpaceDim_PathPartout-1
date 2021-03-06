/*
 * Copyright (C) 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.spacedimvisuel.screens.login

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.spacedimvisuel.R
import com.example.spacedimvisuel.api.User
import com.example.spacedimvisuel.databinding.LoginFragmentBinding
import kotlinx.android.synthetic.main.alert_dialog_edittext.*

/**
 * Fragment where the game is played
 */
class LoginFragment : Fragment() {


    private lateinit var binding: LoginFragmentBinding
    private val TAG = "LoginFragment"
    private lateinit var viewModel: LoginViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.login_fragment,
                container,
                false
        )
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        // Specify the current activity as the lifecycle owner.
        binding.lifecycleOwner = this

        binding.rocketButton.setOnClickListener {
            viewModel.findUser(binding.editText.getText().toString())

            val builder = AlertDialog.Builder(this.requireContext())
            val inflater = layoutInflater
            builder.setTitle("Please enter room name")
            val dialogLayout = inflater.inflate(R.layout.alert_dialog_edittext, null)
            val editText = dialogLayout.findViewById<EditText>(R.id.roomNameEditText)
            builder.setView(dialogLayout)
            var roomName = ""
            builder.setPositiveButton("OK") { dialog, which ->
                roomName = editText.text.toString()
                println(roomName)
            }

            builder.show()
            viewModel.findUser(binding.editText.getText().toString())
            viewModel.joinRoom("FuckThisOkHttpThingyEatMyShit")
//            goToLobby()
        }



        return binding.root
    }

    private fun goToLobby() {
        val action = LoginFragmentDirections.actionLoginDestinationToLobbyDestination()
        NavHostFragment.findNavController(this).navigate(action)
    }

}
