package com.example.reward.reward.service

import com.example.reward.reward.dto.TypeOfQuest
import com.example.reward.reward.model.GemRewardFactory
import com.example.reward.reward.model.GoldRewardFactory
import org.springframework.stereotype.Service

@Service
class PlayerRewardServiceNames {

    fun getQuestRewardPlayer(typeReward: String, typeQuest: TypeOfQuest, namePlayer: String): List<PlayerResponse> {
        val userReward = when (typeReward) {
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
    fun getQuestRewardPlayers(typeReward: String, names: List<String>, typeQuest: TypeOfQuest): List<PlayerResponse> {
        val userReward = when (typeReward) {
            "Gold" -> GoldRewardFactory().getReward(typeQuest)
            "Gem" -> GemRewardFactory().getReward(typeQuest)
            else -> throw IllegalArgumentException("There is no type of reward $typeReward")
        }
        return names.map {
            PlayerResponse(
                name = it,
                reward = userReward.nameReward,
                amount = userReward.reward()
            )
        }
    }

    fun postQuestRewardPlayer(playerRequest: PlayerRequest): List<PlayerResponse> {
        val userReward = when (playerRequest.typeReward) {
            "Gold" -> GoldRewardFactory().getReward(playerRequest.typeQuest)
            "Gem" -> GemRewardFactory().getReward(playerRequest.typeQuest)
            else -> throw IllegalArgumentException("There is no type of reward ${playerRequest.typeReward}")
        }

        return listOf(
            PlayerResponse(
                name = playerRequest.name,
                reward = userReward.nameReward,
                amount = userReward.reward()
            )
        )
    }
}

data class PlayerResponse(
    val name: String,
    val reward: String,
    val amount: Int
)

data class PlayerRequest(
    val name: String,
    val typeReward: String,
    val typeQuest: TypeOfQuest
)