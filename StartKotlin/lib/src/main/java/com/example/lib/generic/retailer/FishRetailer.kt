package com.example.lib.generic.retailer

import com.example.lib.generic.pet.Fish

/**
 * Created by Cai Huijian on 2021/8/29.
 */
class FishRetailer : Retailer<Fish> {
    override fun sell(): Fish {
        println("Sell Fish")
        return Fish("")
    }
}