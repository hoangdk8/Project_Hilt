package com.example.project_hilt_mvvm.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.project_hilt_mvvm.UserAdapter
import com.example.project_hilt_mvvm.databinding.ActivityMainBinding
import com.example.project_hilt_mvvm.model.User
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var user: ArrayList<User>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
    }

    private fun setupViews() {
        binding.btnAdd.setOnClickListener {
            val username = binding.inputUsername.text.toString()
            user = ArrayList()
            createList(username)
            val adapter = UserAdapter(user!!)
            binding.recyclerView.adapter = adapter
        }
    }

    private fun createList(username: String) {
        user!!.add(User(username = username ))
    }
}