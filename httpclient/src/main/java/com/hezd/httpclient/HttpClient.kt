package com.hezd.httpclient

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author hezd
 * @date 2024/1/24 13:46
 * @description
 */
object HttpClient {
    private lateinit var retrofit: Retrofit

    @JvmStatic
    fun init(options: Options) {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(options.connectTimeout, TimeUnit.MILLISECONDS)
            .readTimeout(options.readTimeout, TimeUnit.MILLISECONDS)
            .writeTimeout(options.writeTimeout, TimeUnit.MILLISECONDS)
            .apply {
                for (interceptor in options.interceptors) {
                    addInterceptor(interceptor)
                }
                for (networkInterceptor in options.networkInterceptors) {
                    addNetworkInterceptor(networkInterceptor)
                }
            }
            .build()
        retrofit = Retrofit.Builder()
            .baseUrl(options.baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(FlowCallAdapterFactory.create())
            .build()
    }

    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }

    class Options {
        internal lateinit var baseUrl: String
        internal var connectTimeout = 10_000L
        internal var readTimeout = 10_000L
        internal var writeTimeout = 10_000L
        internal val interceptors = mutableListOf<Interceptor>()
        internal val networkInterceptors = mutableListOf<Interceptor>()

        fun baseUrl(baseUrl: String): Options {
            this.baseUrl = baseUrl
            return this
        }

        fun connectTimeout(connectTimeout: Long): Options {
            this.connectTimeout = connectTimeout
            return this
        }

        fun readTimeout(readTimeout: Long): Options {
            this.readTimeout = readTimeout
            return this
        }

        fun addInterceptor(interceptor: Interceptor): Options {
            interceptors += interceptor
            return this
        }

        fun addNetworkInterceptor(interceptor: Interceptor): Options {
            networkInterceptors += interceptor
            return this
        }

    }
}