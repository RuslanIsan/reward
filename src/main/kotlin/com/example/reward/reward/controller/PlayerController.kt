package com.example.reward.reward.controller

import com.example.reward.reward.dto.PlayerRequest
import com.example.reward.reward.dto.PlayerResponse
import com.example.reward.reward.dto.TypeOfQuest
import com.example.reward.reward.dto.TypeOfReward
import com.example.reward.reward.service.PlayerRewardServiceNames
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/reward")
class PlayerController(
    private var playerRewardServiceNames: PlayerRewardServiceNames
) {
    /*
    // Выводим Награду за босса
    @GetMapping
    fun playerRewardService(
        @RequestParam typeReward: TypeOfReward,
        @RequestParam typeQuest: TypeOfQuest,
        @RequestParam namePlayer: String
    ): List<PlayerResponse> =
        playerRewardServiceNames.getQuestRewardPlayer(typeReward, typeQuest, namePlayer)
    */

    // Принимаем GET коллекцию имен и выводим в json
    @GetMapping
    fun getNamePlayers(
        @RequestParam typeReward: TypeOfReward,
        @RequestParam names: List<String>,
        @RequestParam typeQuest: TypeOfQuest
    ): List<PlayerResponse> =
        playerRewardServiceNames.getQuestRewardPlayers(typeReward, names, typeQuest)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun postQuestRewardPlayer(@RequestBody playerRequest: PlayerRequest): List<PlayerResponse> =
        playerRewardServiceNames.postQuestRewardPlayer(playerRequest)
}