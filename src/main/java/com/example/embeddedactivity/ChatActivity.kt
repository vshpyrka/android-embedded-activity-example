package com.example.embeddedactivity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.window.embedding.SplitController
import com.example.embeddedactivity.databinding.ActivityEmbeddedChatBinding
import com.example.embeddedactivity.model.Message
import kotlinx.coroutines.launch

class ChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEmbeddedChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle("Chat")

        val adapter = MessagesAdapter {
            startActivity(Intent(this, ImagePreviewActivity::class.java))
        }
        adapter.data = listOf(
            Message(
                senderIcon = R.drawable.ic_cat,
                text = "Maybe pizza?",
                isIncoming = true,
                media = null,
            ),
            Message(
                senderIcon = R.drawable.ic_sheep,
                text = "Looks funny",
                isIncoming = false,
                media = R.drawable.ic_sheep_full,
            ),
            Message(
                senderIcon = R.drawable.ic_cat,
                text = "That looks so good!",
                isIncoming = true,
                media = null,
            ),
        )
        binding.messages.adapter = adapter
        binding.messages.layoutManager = LinearLayoutManager(this)

        binding.photo.setOnClickListener {
            startActivity(
                Intent(
                    Intent(Intent.ACTION_VIEW, Uri.parse(
                        "https://stackoverflow.com/users/1926763/victor-shpyrka")
                    )
                )
            )
        }
        binding.attach.setOnClickListener {
            // Empty
        }
        binding.create.setOnClickListener {
            // Empty
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                SplitController.getInstance(this@ChatActivity)
                    .splitInfoList(this@ChatActivity)
                    .collect { splitInfoList ->
                        println("AAA splitInfoList $splitInfoList")
                    }
            }
        }
    }
}
