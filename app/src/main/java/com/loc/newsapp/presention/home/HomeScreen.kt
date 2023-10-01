package com.loc.newsapp.presention.home

import android.net.wifi.hotspot2.pps.HomeSp
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.paging.compose.LazyPagingItems
import com.loc.newsapp.domin.model.Article
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loc.newsapp.R
import com.loc.newsapp.presention.Dimens.MediumPadding1
import com.loc.newsapp.presention.common.ArticlesList
import com.loc.newsapp.presention.common.SearchBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalFoundationApi::class)
@Destination
@Composable
fun HomeScreen(
    navigator:DestinationsNavigator?=null,
    articles: LazyPagingItems<Article>

    ) {
    val titles by remember {
        derivedStateOf {
            if (articles.itemCount > 10) {
                articles.itemSnapshotList.items
                    .slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = "\uD3dd\uDFE5") {
                        it.title
                    }
            } else {
                ""
            }
        }
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = MediumPadding1)
        .statusBarsPadding()
    ) {
        Image(painter = painterResource(id = R.drawable.ic_logo), contentDescription =null
        , modifier = Modifier
                .width(150.dp)
                .height(30.dp)
                .padding(horizontal = MediumPadding1)
        )
        Spacer(modifier =
        Modifier.height(MediumPadding1)
        )
        SearchBar(text = "",
            modifier = Modifier.padding(horizontal = MediumPadding1),
            readOnly =true
            , onValueChange = {},
            onClick = {

            },
        onSearch = {

        })

        Spacer(modifier = Modifier.height(MediumPadding1))
        Text(
            text = titles,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = MediumPadding1)
                .basicMarquee(),
            fontSize = 12.sp,
            color= colorResource(id = R.color.placeholder)

        )

        Spacer(modifier = Modifier.height(MediumPadding1))
        ArticlesList(
            modifier = Modifier.padding(horizontal = MediumPadding1 )
            , articles =articles
            , onClick ={
//                    navigator.navigate(
//                    )
            }
        )

    }

}

