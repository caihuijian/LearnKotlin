package com.example.lib.generic.contest

import com.example.lib.generic.pet.Pet
import com.example.lib.generic.vet.Vet

/**
 * Created by Cai Huijian on 2021/8/29.
 * 宠物竞赛
 */
class Contest<T : Pet>(var vet: Vet<in T>) {
    // 定义泛型 且只能是Pet或者Pet的子类型
    // 得分属性 以Pet或者Pet的子类为key 以得分为value的Map
    val scores: MutableMap<T, Int> = mutableMapOf()

    // 给宠物判分数
    fun addScore(t: T, score: Int) {
        if (score > 0) {
            scores.put(t, score)
        }
    }

    // 计算赢家 且若同分 这几个都算赢家
    fun getWinner(): MutableSet<T> {
        var highScore = 0;
        // 遍历 取得最高分的Map中的value
        for (item in scores.values) {
            if (highScore < item) {
                highScore = item
            }
        }
        val winners: MutableSet<T> = mutableSetOf()
        // 遍历 取得最高分的Map中的key
        for ((t, score) in scores) {
            if (score == highScore) {
                winners.add(t)
            }
        }
        return winners
    }

}