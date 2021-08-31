package com.example.lib.generic

import com.example.lib.generic.contest.Contest
import com.example.lib.generic.pet.Cat
import com.example.lib.generic.pet.Dog
import com.example.lib.generic.pet.Fish
import com.example.lib.generic.pet.Pet
import com.example.lib.generic.retailer.*
import com.example.lib.generic.vet.Vet

/**
 * Created by Cai Huijian on 2021/8/29.
 */
fun main() {
    // 创建兽医
    val petVet = Vet<Pet>()
    // 只能加入Cat的Contest
    // 如果没有逆变 尝试将Vet<Pet>类型的petVet 赋值给Contest<Cat>的catContest 将会编译报错
    // 我们的目标是希望使用Vet<Pet>代替Vet<Pet>传值给Contest<Cat> 即我们想要用父类型替换子类型 即逆变
    // Type mismatch.
    // Required: Cat
    // Found: Pet
    //val catContest = Contest<Cat>(petVet)

    // 在Vet的定义中添加in关键字后 方可编译通过
    val catContest = Contest<Cat>(petVet)
    catContest.addScore(Cat("CatA"), 50)
    catContest.addScore(Cat("CatB"), 40)
    val topCat = catContest.getWinner().first()
    println(topCat.name)

    // 可以加入任意Pet的Contest
    val petContest = Contest<Pet>(petVet)
    petContest.addScore(Cat("CatA"), 50)
    petContest.addScore(Dog("DogA"), 40)
    petContest.addScore(Fish("FishB"), 50)
    val topPet = petContest.getWinner()
    topPet.forEach { pet -> println(pet.name) }

    val catRetailer: CatRetailer = CatRetailer()
    val dogRetailer: DogRetailer = DogRetailer()
    val fishRetailer: FishRetailer = FishRetailer()
    // 猫店只卖猫 狗店只卖狗 鱼店只卖鱼
    // 通常情况下 我们无法将 Retailer<Cat> 赋值给声明类型为Retailer<Pet>的对象
    // Type mismatch.
    // Required: Retailer<Pet>
    // Found: CatRetailer
    // var petRetailer:Retailer<Pet> = CatRetailer()
    // 能不能有办法将Retailer<Pet>的引用指向CatRetailer或者DogRetailer 答案就是在Retailer的声明使用out关键字
    val petRetailer: Retailer<Pet> = CatRetailer()
    catRetailer.sell()
    dogRetailer.sell()
    fishRetailer.sell()
    petRetailer.sell()

    val dogVet = Vet<Dog>()
    val fishVet = Vet<Fish>()
    // 使用局部逆变之后这里报错
    // val catVet:Vet<Cat> = Vet<Pet>()
    // 但是下面这句话仍然可以编译通过
    val catContest1 = Contest<Cat>(petVet)
}

class Test {
}