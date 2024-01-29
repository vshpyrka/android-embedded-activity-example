package com.example.embeddedactivity

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.embeddedactivity.databinding.ItemEmbeddedIncomingMessageBinding
import com.example.embeddedactivity.databinding.ItemEmbeddedOutgoingMessageBinding
import com.example.embeddedactivity.model.Message

private const val INCOMING_MESSAGE_TYPE = 1
private const val OUTGOING_MESSAGE_TYPE = 2

class MessagesAdapter(
    private val onClick: () -> Unit
) : RecyclerView.Adapter<MessageViewHolder>() {

    var data: List<Message> = mutableListOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return when (viewType) {
            INCOMING_MESSAGE_TYPE -> {
                IncomingMessageViewHolder(
                    ItemEmbeddedIncomingMessageBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
            }

            OUTGOING_MESSAGE_TYPE -> {
                OutgoingMessageViewHolder(
                    ItemEmbeddedOutgoingMessageBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    ),
                    onClick
                )
            }

            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (!data[position].isIncoming) {
            OUTGOING_MESSAGE_TYPE
        } else {
            INCOMING_MESSAGE_TYPE
        }
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        when (holder) {
            is IncomingMessageViewHolder -> {
                holder.bind(data[position])
            }
            is OutgoingMessageViewHolder -> {
                holder.bind(data[position])
            }
        }
    }
}

open class MessageViewHolder(view: View) : RecyclerView.ViewHolder(view)

class IncomingMessageViewHolder(
    private val binding: ItemEmbeddedIncomingMessageBinding
) : MessageViewHolder(binding.root) {

    fun bind(message: Message) {
        binding.image.setImageResource(message.senderIcon)
        binding.message.text = message.text
    }

}

class OutgoingMessageViewHolder(
    private val binding: ItemEmbeddedOutgoingMessageBinding,
    private val onClick: () -> Unit
) : MessageViewHolder(binding.root) {

    init {
        binding.media.setOnClickListener { onClick() }
    }

    fun bind(message: Message) {
        binding.image.setImageResource(message.senderIcon)
        binding.message.text = message.text
        if (message.media != null) {
            binding.media.setImageResource(message.media)
            binding.media.isVisible = true
        } else {
            binding.media.isVisible = false
        }
    }

}
