package com.devopsolution.code9.common

object Enums {

    enum class UserStatusEnum(val value: Int) {
        Normal(1),
        Suspected(2),
        Infected(3)
    }


    enum class UserTypeEnum(val value: Int) {
        Citizen(1),
        Shop(2),
    }


    enum class Gender(val value: Int) {
        Male(1),
        Female(2),
    }
}