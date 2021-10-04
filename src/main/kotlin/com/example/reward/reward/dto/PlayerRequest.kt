package com.example.reward.reward.dto

data class PlayerRequest(
    val name: String,
    val typeReward: TypeOfReward,
    val typeQuest: TypeOfQuest
)