package com.hezd.httpclient

import android.app.Application

/**
 * @author hezd
 * @date 2024/1/24 14:44
 * @description
 */
class HttpClientApplication :Application(){
    override fun onCreate() {
        super.onCreate()
        val options = HttpClient.Options()
        options.baseUrl("https://api.github.com/")
        HttpClient.init(options)
    }
}