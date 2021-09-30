package com.example.reward.reward.service

import org.springframework.stereotype.Service

interface Reward {
    val nameReward : String
    fun reward(): Int
}

interface RewardFactory {
    fun getGoldReward(typeQuest : String) : Reward
    fun getGemReward(typeQuest : String) : Reward
}

class GoldRewardFactory : RewardFactory {
    override fun getGoldReward(typeQuest: String): Reward {
        return when (typeQuest) {
            "QuestBoss" -> QuestBoss()
            "QuestCollectingThings" -> QuestCollectingThings()
            else -> throw IllegalArgumentException("No class $typeQuest")
        }
    }

    override fun getGemReward(typeQuest: String): Reward {
        TODO("Not yet implemented")
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

class GemRewardFactory : RewardFactory {


    override fun getGemReward(typeQuest: String): Reward {
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

    override fun getGoldReward(typeQuest: String): Reward {
        TODO("Not yet implemented")
    }

}

@Service
class PlayerRewardServiceNames {
    private val goldFactory = GoldRewardFactory()
    private val gemFactory = GemRewardFactory()

    fun getQuestReward(typeQuest: String, namePlayer: String, typeReward: String): List<PlayerResponse> {
        val userReward = when (typeReward){
            "Gold" -> createReward(goldFactory, typeQuest)
            "Gem" -> createReward(gemFactory, typeQuest)
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
    fun getPlayers(names: List<String>): List<PlayerResponse> =
        names.map {
            val userReward = createReward(goldFactory, "QuestBoss") // Генерируем награду
            PlayerResponse (
                name = it,
                reward = "Gold",
                amount = userReward.reward()
            )
        }

    private fun createReward(factory: RewardFactory, name : String) : Reward {
        return factory.getGoldReward(name)
    }
}

data class PlayerResponse (
    val name: String,
    val reward: String,
    val amount: Int
)