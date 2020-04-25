package com.devopsolution.code9.common

object Constants {



    //numbers
    const val CACHE_SIZE = 10 * 1024 * 1024 //10MB
    const val CACHE_MAX_AGE = 1 //1 second
    const val CACHE_MAX_STALE = 60 * 60 * 24 * 7 //1 week

    const val ARABIC = 1
    const val ENGLISH = 0
    const val NOT_DEFIEND_LANG = -1

    //time
    const val SNAK_BAR_DURATION: Int = 3 * 1000

    //dialog fragment size
    const val SIZE_NORMAL = 0
    const val SIZE_FULL_SCREEN = 1
    const val SIZE_FULL_SCREEN_WITH_BACKGROUND = 2

    // paging
    const val ROWS_NUM = 10
    const val INITIAL_LOAD_SIZE_HINT = 13


    //strings
    //shared preference keys
    const val PREFERENCE_NAME = "SHARED_PREFERENCE"

    const val USER_ID = "USER_ID"
    const val USER_TYPE = "USER_TYPE"
    const val USER_FCM_TOKEN = "USER_FCM_TOKEN"


    const val USER_LANGUAGE = "USER_LANGUAGE"

    const val GLOBAL_NOTIFICATIONS_CHANNEL = "GLOBAL"

    const val REFRESH_ACTION = "refresh"


}