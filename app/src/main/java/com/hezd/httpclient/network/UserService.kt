package com.hezd.httpclient.network

import com.hezd.httpclient.model.RepoInfo
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * @author hezd
 * @date 2024/1/24 17:14
 * @description
 */
interface UserService {
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String?): Flow<List<RepoInfo>>
}