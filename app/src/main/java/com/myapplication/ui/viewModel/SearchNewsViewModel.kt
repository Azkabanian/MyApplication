package com.myapplication.ui.viewModel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapplication.domain.models.NewsResponse
import com.myapplication.domain.usecase.SearchNewsUseCase
import com.myapplication.ui.util.Resource
import com.myapplication.util.Constants.Companion.SEARCH_NEWS_PAGE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class SearchNewsViewModel @Inject constructor(
    private val searchNewsUseCase: SearchNewsUseCase,
) : ViewModel() {

    private val _searchedNewsLiveData: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val searchedNewsLiveData: LiveData<Resource<NewsResponse>> = _searchedNewsLiveData
    var searchNewsPage = SEARCH_NEWS_PAGE

    fun searchNews(context: Context, searchQuery: String) = viewModelScope.launch(Dispatchers.IO) {
        safeSearchNewsCall(context, searchQuery)
    }

    private suspend fun safeSearchNewsCall(context: Context, searchQuery: String) {
        _searchedNewsLiveData.postValue(Resource.Loading())
        try {
            if (hasInternetConnection(context)) {
                _searchedNewsLiveData.postValue(searchNewsUseCase.invoke(searchQuery))
            } else {
                _searchedNewsLiveData.postValue(Resource.Error("Нет интернет соединения"))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> _searchedNewsLiveData.postValue(Resource.Error("Сбой в сети"))
                else -> _searchedNewsLiveData.postValue(Resource.Error("Ошибка преобразования"))
            }
        }
    }

    private fun hasInternetConnection(context: Context): Boolean {
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }
        return false
    }
}