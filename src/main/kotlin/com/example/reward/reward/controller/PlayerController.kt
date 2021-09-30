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
    @GetMapping("typeQuest/{namePlayer}/{typeQuest}/{typeReward}")
    fun playerRewardService(@PathVariable namePlayer: String, @PathVariable typeQuest: String, @PathVariable typeReward: String): List<PlayerResponse> =
        playerRewardServiceNames.getQuestReward(typeQuest, namePlayer, typeReward)

    // Принимаем GET коллекцию имен и выводим в json
    @GetMapping("player")
    fun getNamePlayers(@RequestParam names: List<String>): List<PlayerResponse> =
        playerRewardServiceNames.getPlayers(names)

}