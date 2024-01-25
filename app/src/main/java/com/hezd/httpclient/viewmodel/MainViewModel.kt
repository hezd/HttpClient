package com.hezd.httpclient.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hezd.httpclient.HttpClient
import com.hezd.httpclient.model.RepoInfo
import com.hezd.httpclient.network.UserService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * @author hezd
 * @date 2024/1/24 17:04
 * @description
 */
class MainViewModel : ViewModel() {
    private var mutableUserInfo = MutableStateFlow(MainUiState(emptyList()))
    val userInfo = mutableUserInfo

    init {
        getUserInfo()
    }

    private fun getUserInfo() {
        viewModelScope.launch {
            val response = HttpClient.create(UserService::class.java).listRepos("hezd")
            response.collect { repos ->
                mutableUserInfo.update {
                    it.copy(repos = repos)
                }
            }
        }
    }
}

data class MainUiState(val repos: List<RepoInfo>)