package com.devopsolution.code9.data.network.model

import okhttp3.Headers
import okhttp3.ResponseBody


/**
 * Common class used by API responses.
 * @param <T> the type of the responseBody
 **/
class ApiResponse<T> {

    /** if request throws exception and can't be send  */
    var exception: Throwable? = null
    var isCanceled = false

    /** if request completed and receive a response  */
    var isResponseSuccessful = false // state of response

    var responseCode = 0 // response code

    // response headers
    var responseHeader: Headers? = null

    // response body in case of response succeeded (T: the POJO class of the response)
    var responseBody: GeneralResponse<T>? = null

    // error body in case of response failed
    var errorBody: ResponseBody? = null
}