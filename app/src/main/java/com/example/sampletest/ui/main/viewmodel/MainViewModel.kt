package com.example.sampletest.ui.main.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sampletest.data.dto.DeptDTO
import com.example.sampletest.data.repository.MainRepository

import com.example.sampletest.utils.NetworkHelper
import com.example.sampletest.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    val _users = MutableLiveData<Resource<List<DeptDTO>>>()

    val compositeDisposable = CompositeDisposable()

    init {
        if (networkHelper.isNetworkConnected()) {
            fetchUsers()
        } else {
            fetchUsersFromLocal()
        }
    }

    private fun fetchUsers() {
        compositeDisposable.add(
            mainRepository.getUsers().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).
                subscribe({
                    _users.postValue(Resource.success(it))
                    mainRepository.saveUser(it)
                },{
                    _users.postValue(Resource.error(it.localizedMessage, null))
                })
        )
    }

    private fun fetchUsersFromLocal() {
        compositeDisposable.add(
                mainRepository.getUsersFromLocal().subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()).
                        subscribe({
                            _users.postValue(Resource.success(it))
                        },{
                            _users.postValue(Resource.error(it.localizedMessage, null))
                        })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}