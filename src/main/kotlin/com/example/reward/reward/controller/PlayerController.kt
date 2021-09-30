package com.example.reward.reward.controller

import com.example.reward.reward.service.PlayerResponse
import com.example.reward.reward.service.PlayerRewardServiceNames
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api")
class PlayerController(
    private var playerRewardServiceNames: PlayerRewardServiceNames
) {
    // Выводим Награду за босса
    @GetMapping("{typeReward}/{typeQuest}/{namePlayer}")
    fun playerRewardService(@PathVariable typeReward: String, @PathVariable typeQuest: String, @PathVariable namePlayer: String): List<PlayerResponse> =
        playerRewardServiceNames.getQuestRewardPlayer(typeReward, typeQuest, namePlayer)

    // Принимаем GET коллекцию имен и выводим в json

    @GetMapping("{typeReward}/{typeQuest}/player")
    fun getNamePlayers(@PathVariable typeReward: String, @RequestParam names: List<String>, @PathVariable typeQuest: String): List<PlayerResponse> =
        playerRewardServiceNames.getQuestRewardPlayers(typeReward, names, typeQuest)

}