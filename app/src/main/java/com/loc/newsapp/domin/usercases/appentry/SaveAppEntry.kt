package com.loc.newsapp.domin.usercases.appentry

import com.loc.newsapp.domin.manger.LocalUserManger

class SaveAppEntry(
    private val localUserManger: LocalUserManger
) {
 suspend operator fun  invoke(){
     localUserManger.saveAppEntry()
 }
}