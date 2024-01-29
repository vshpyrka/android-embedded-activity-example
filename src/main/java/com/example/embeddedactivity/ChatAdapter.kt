package com.example.embeddedactivity

import android.annotation.SuppressLint
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.embeddedactivity.databinding.ItemEmbeddedChatBinding
import com.example.embeddedactivity.model.Chat

class ChatAdapter(
    private val onClick: () -> Unit
) : RecyclerView.Adapter<ChatViewHolder>() {

    var data: List<Chat> = mutableListOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return ChatViewHolder(
            ItemEmbeddedChatBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ).apply {
                root.setOnClickListener { onClick() }
            }
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(data[position])
    }
}

class ChatViewHolder(
    private val binding: ItemEmbeddedChatBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(chat: Chat) {
        binding.image.setImageResource(chat.icon)
        binding.name.text = chat.title
        binding.message.text = chat.summary
        binding.time.text = DateUtils.getRelativeTimeSpanString(chat.time)
    }
}
