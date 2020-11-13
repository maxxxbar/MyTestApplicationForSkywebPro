package com.worldshine.mytestapplicationforskywebpro.ui.authorization

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthorizationViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is authorization Fragment"
    }
    val text: LiveData<String> = _text
}