package com.example.reward.reward.model

import com.example.reward.reward.dto.TypeOfQuest

class GoldRewardFactory : GetRewardFactory {
    override fun getReward(typeQuest: TypeOfQuest): Reward {
        return when (typeQuest) {
            TypeOfQuest.BOSS -> QuestBoss()
            TypeOfQuest.COLLECTING_THINGS -> QuestCollectingThings()
            else -> throw IllegalArgumentException("No class $typeQuest")
        }
    }

    class QuestBoss : Reward {
        override val nameReward: String = "Gold is Quest Boss"
        override fun reward(): Int =
            (50..200).random()
    }

    class QuestCollectingThings : Reward {
        override val nameReward: String = "Gold for collecting things"
        override fun reward(): Int =
            (10..70).random()
    }
}