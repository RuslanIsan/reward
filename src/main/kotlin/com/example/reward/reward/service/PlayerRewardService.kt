package com.example.reward.reward.service

import org.springframework.stereotype.Service

interface Reward {
    val nameReward : String
    fun reward(): Int
}

interface GetRewardFactory {
    fun getReward(typeQuest : String) : Reward
}


class GoldRewardFactory : GetRewardFactory {
    override fun getReward(typeQuest: String): Reward {
        return when (typeQuest) {
            "QuestBoss" -> QuestBoss()
            "QuestCollectingThings" -> QuestCollectingThings()
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

class GemRewardFactory : GetRewardFactory {
    override fun getReward(typeQuest: String): Reward {
        return when (typeQuest){
            "QuestRaidBoss" -> QuestRaidBoss()
            "QuestArena" -> QuestArena()
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

@Service
class PlayerRewardServiceNames {

    fun getQuestRewardPlayer(typeReward: String, typeQuest: String, namePlayer: String): List<PlayerResponse> {
        val userReward = when (typeReward){
            "Gold" -> GoldRewardFactory().getReward(typeQuest)
            "Gem" -> GemRewardFactory().getReward(typeQuest)
            else -> throw IllegalArgumentException("There is no type of reward $typeReward")
        }

        return listOf(
            PlayerResponse(
                name = namePlayer,
                reward = userReward.nameReward,
                amount = userReward.reward()
            )
        )
    }

    // Получаем строку имён, распарсиваем её и присваиваем награду
    fun getQuestRewardPlayers(typeReward: String, names: List<String>, typeQuest: String): List<PlayerResponse> {
        val userReward = when (typeReward) {
            "Gold" -> GoldRewardFactory().getReward(typeQuest)
            "Gem" -> GemRewardFactory().getReward(typeQuest)
            else -> throw IllegalArgumentException("There is no type of reward $typeReward")
        }
        return names.map {
            PlayerResponse (
                name = it,
                reward = userReward.nameReward,
                amount = userReward.reward()
            )
        }
    }


/*
    private fun createReward(factory: GemRewardFactory, name: String) : Reward {
        return factory.getGoldReward(name)
    }
    */
}

data class PlayerResponse (
    val name: String,
    val reward: String,
    val amount: Int
)