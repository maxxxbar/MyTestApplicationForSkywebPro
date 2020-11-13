package com.worldshine.mytestapplicationforskywebpro.ui.pictures

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PicturesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Pictures fragment"
    }
    val text: LiveData<String> = _text
}