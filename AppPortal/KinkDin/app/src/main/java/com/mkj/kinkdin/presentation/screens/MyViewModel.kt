package com.mkj.kinkdin.presentation.screens
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val greeting: String
) : ViewModel() {

    fun getGreeting() = greeting
}
