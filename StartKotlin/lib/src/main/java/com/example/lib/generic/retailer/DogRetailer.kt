package com.example.lib.generic.retailer

import com.example.lib.generic.pet.Dog

/**
 * Created by Cai Huijian on 2021/8/29.
 */
class DogRetailer : Retailer<Dog> {
    override fun sell(): Dog {
        println("Sell Dog")
        return Dog("")
    }
}