package com.example.reward.reward.model

import com.example.reward.reward.dto.TypeOfQuest

class GemRewardFactory : GetRewardFactory {
    override fun getReward(typeQuest: TypeOfQuest): Reward {
        return when (typeQuest) {
            TypeOfQuest.RAID_BOSS -> QuestRaidBoss()
            TypeOfQuest.ARENA -> QuestArena()
            else -> throw IllegalArgumentException("No class $typeQuest")
        }
    }

    class QuestRaidBoss : Reward {
        override val nameReward: String = "Gem is Quest Boss"
        override fun reward(): Int =
            (10..20).random()
    }

    class QuestArena : Reward {
        override val nameReward: String = "Gem for the quest in the arena"
        override fun reward(): Int =
            (3..10).random()
    }
}
