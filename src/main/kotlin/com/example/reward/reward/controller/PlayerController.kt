package com.example.reward.reward.controller

import com.example.reward.reward.dto.TypeOfQuest
import com.example.reward.reward.service.PlayerRequest
import com.example.reward.reward.service.PlayerResponse
import com.example.reward.reward.service.PlayerRewardServiceNames
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api")
class PlayerController(
    private var playerRewardServiceNames: PlayerRewardServiceNames
) {
    // Выводим Награду за босса
    @GetMapping("{typeReward}/{typeQuest}/{namePlayer}")
    @ResponseStatus(HttpStatus.OK)
    fun playerRewardService(
        @PathVariable typeReward: String,
        @PathVariable typeQuest: TypeOfQuest,
        @PathVariable namePlayer: String
    ): List<PlayerResponse> =
        playerRewardServiceNames.getQuestRewardPlayer(typeReward, typeQuest, namePlayer)

    // Принимаем GET коллекцию имен и выводим в json
    @GetMapping("{typeReward}/{typeQuest}/player")
    @ResponseStatus(HttpStatus.OK)
    fun getNamePlayers(
        @PathVariable typeReward: String,
        @RequestParam names: List<String>,
        @PathVariable typeQuest: TypeOfQuest
    ): List<PlayerResponse> =
        playerRewardServiceNames.getQuestRewardPlayers(typeReward, names, typeQuest)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun postQuestRewardPlayer(@RequestBody playerRequest: PlayerRequest): List<PlayerResponse> =
        playerRewardServiceNames.postQuestRewardPlayer(playerRequest)
}