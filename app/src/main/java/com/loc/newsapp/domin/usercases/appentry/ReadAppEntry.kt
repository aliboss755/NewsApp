package com.loc.newsapp.domin.usercases.appentry

import com.loc.newsapp.domin.manger.LocalUserManger
import kotlinx.coroutines.flow.Flow


class ReadAppEntry(
    private val localUserManger: LocalUserManger
) {
     operator fun invoke():Flow<Boolean> {
      return  localUserManger.readAppEntry()
    }
}
