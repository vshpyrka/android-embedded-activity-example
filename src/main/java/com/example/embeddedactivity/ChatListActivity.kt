package com.example.embeddedactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.window.embedding.SplitController
import com.example.embeddedactivity.databinding.ActivityEmbeddedChatListBinding
import com.example.embeddedactivity.model.Chat
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.temporal.ChronoUnit

class ChatListActivity : AppCompatActivity() {

    private val chats = listOf(
        Chat(
            R.drawable.ic_cat,
            "Naomi, Nate, Sarah",
            "That looks so good!",
            System.currentTimeMillis()
        ),
        Chat(
            R.drawable.ic_dog,
            "Alex Rose",
            "Hey! I'm heading over now",
            Instant.now().minus(10, ChronoUnit.MINUTES).toEpochMilli()
        ),
        Chat(
            R.drawable.ic_parrot,
            "Dav Jacobs",
            "Thanks for the heads up",
            Instant.now().minus(20, ChronoUnit.MINUTES).toEpochMilli()
        ),
        Chat(
            R.drawable.ic_sheep,
            "Beathany Qiu",
            "Love it! Will look for it now",
            Instant.now().minus(1, ChronoUnit.DAYS).toEpochMilli()
        ),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEmbeddedChatListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle("Chats")

        val chatAdapter = ChatAdapter {
            startActivity(Intent(this, ChatActivity::class.java))
        }

        binding.chats.adapter = chatAdapter
        binding.chats.layoutManager = LinearLayoutManager(this)

        chatAdapter.data = chats

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                SplitController.getInstance(this@ChatListActivity)
                    .splitInfoList(this@ChatListActivity)
                    .collect { splitInfoList ->
                        println("AAA splitInfoList2 ${splitInfoList}")
                    }
            }
        }
    }
}
