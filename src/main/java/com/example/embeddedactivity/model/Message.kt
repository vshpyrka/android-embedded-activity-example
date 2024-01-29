package com.example.embeddedactivity.model

data class Message(
    val senderIcon: Int,
    val text: String,
    val isIncoming: Boolean,
    val media: Int?,
)
