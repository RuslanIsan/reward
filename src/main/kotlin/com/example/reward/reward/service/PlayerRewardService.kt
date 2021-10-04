package com.example.reward.reward.service

import com.example.reward.reward.converter.PlayerConverter
import com.example.reward.reward.dto.PlayerRequest
import com.example.reward.reward.dto.PlayerResponse
import com.example.reward.reward.dto.TypeOfQuest
import com.example.reward.reward.dto.TypeOfReward
import com.example.reward.reward.model.GemRewardFactory
import com.example.reward.reward.model.GoldRewardFactory
import org.springframework.stereotype.Service

@Service
class PlayerRewardServiceNames(val playerConverter: PlayerConverter) {

    fun getQuestRewardPlayer(
        typeReward: TypeOfReward,
        typeQuest: TypeOfQuest,
        namePlayer: String
    ): List<PlayerResponse> {
        val userReward = when (typeReward) {
            TypeOfReward.GOLD -> GoldRewardFactory().getReward(typeQuest)
            TypeOfReward.GEM -> GemRewardFactory().getReward(typeQuest)
        }

        return playerConverter.convertToPlayerResponses(
            names = listOf(namePlayer),
            reward = userReward.nameReward,
            amount = userReward.reward()
        )
    }

    // Получаем строку имён, распарсиваем её и присваиваем награду
    fun getQuestRewardPlayers(
        typeReward: TypeOfReward,
        names: List<String>,
        typeQuest: TypeOfQuest
    ): List<PlayerResponse> {
        val userReward = when (typeReward) {
            TypeOfReward.GOLD -> GoldRewardFactory().getReward(typeQuest)
            TypeOfReward.GEM -> GemRewardFactory().getReward(typeQuest)
        }
        return playerConverter.convertToPlayerResponses(
            names = names,
            reward = userReward.nameReward,
            amount = userReward.reward()
        )
    }

    fun postQuestRewardPlayer(playerRequest: PlayerRequest): List<PlayerResponse> {
        val userReward = when (playerRequest.typeReward) {
            TypeOfReward.GOLD -> GoldRewardFactory().getReward(playerRequest.typeQuest)
            TypeOfReward.GEM -> GemRewardFactory().getReward(playerRequest.typeQuest)
        }

        return playerConverter.convertToPlayerResponses(
            names = listOf(playerRequest.name),
            reward = userReward.nameReward,
            amount = userReward.reward()
        )
    }
}



