package com.example.lib.generic.retailer

import com.example.lib.generic.pet.Cat

/**
 * Created by Cai Huijian on 2021/8/29.
 */
class CatRetailer : Retailer<Cat> {
    override fun sell(): Cat {
        println("Sell Cat")
        return Cat("")
    }
}