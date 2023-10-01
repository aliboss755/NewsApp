package com.loc.newsapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loc.newsapp.domin.usercases.appentry.AppEntryUsesCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel
class MainViewModel  @Inject constructor(
    private val appEntryUsesCases: AppEntryUsesCases
):ViewModel(){
     var splashCondition by mutableStateOf(true)
         private set
    var startDistination = mutableStateOf("")
        private set

    init {
        appEntryUsesCases.readAppEntry().onEach {shouldNav ->
        if (shouldNav){

        }else{

        }
            delay(300)
            splashCondition=false
        }.launchIn(viewModelScope)

    }
}