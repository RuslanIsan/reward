package com.example.reward.reward.model

import com.example.reward.reward.dto.TypeOfQuest

interface Reward {
    val nameReward: String
    fun reward(): Int
}

interface GetRewardFactory {
    fun getReward(typeQuest: TypeOfQuest): Reward
}