package com.example.reward.reward.converter

import com.example.reward.reward.dto.PlayerResponse
import org.springframework.stereotype.Component

@Component
class PlayerConverter {
    fun convertToPlayerResponses(names: List<String>, reward: String, amount: Int): List<PlayerResponse> =
        names.map {
            PlayerResponse(it, reward, amount)
        }
}