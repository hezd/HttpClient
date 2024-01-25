package com.hezd.httpclient.model

/**
 * @author hezd
 * @date 2024/1/24 18:19
 * @description
 */
data class RepoInfo(
    val id: Double,
    val name: String,
    val description: String,
    val defaultBranch: String,
)