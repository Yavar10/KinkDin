package com.mkj.kinkdin.presentation.screens.settings


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkj.kinkdin.util.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    fun updateTheme(theme: String) {
        _uiState.value = _uiState.value.copy(selectedTheme = theme)
        // Save to preferences
    }

    fun toggleNotifications(enabled: Boolean) {
        _uiState.value = _uiState.value.copy(notificationsEnabled = enabled)
        // Save to preferences
    }

    fun logout() {
        viewModelScope.launch {
            preferencesManager.clearAll()
        }
    }

    fun deleteAccount() {
        viewModelScope.launch {
            // TODO: Implement account deletion
            preferencesManager.clearAll()
        }
    }
}