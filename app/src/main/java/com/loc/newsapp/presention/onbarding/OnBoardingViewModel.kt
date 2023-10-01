package com.loc.newsapp.presention.onbarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loc.newsapp.domin.usercases.appentry.AppEntryUsesCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel@Inject  constructor(
    private val appEntryUsesCases: AppEntryUsesCases
):ViewModel () {
    fun onEvent(event: OnBoardingEvent){
        when(event){
            is OnBoardingEvent.saveAppEntry ->{
                saveAppEntry()
            }
        }
    }
    private fun saveAppEntry(){
        viewModelScope.launch {
            appEntryUsesCases.saveAppEntry()

        }
    }
}
