package com.loc.newsapp.presention.componts

import android.graphics.pdf.PdfDocument.Page
import android.icu.util.LocaleData.PaperSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.loc.newsapp.presention.Dimens.IndicatorSize
import com.loc.newsapp.ui.theme.BlueGray

@Composable
fun PageIndicator(
    modifier: Modifier =Modifier,
    paperSize: Int,
    selectedPage:Int,
    selectedColor:Color =MaterialTheme.colorScheme.primary,
    unSelectedColor: Color= BlueGray
) {
    Row(modifier=
    modifier,
        horizontalArrangement = Arrangement.SpaceBetween,

    ) {
        repeat(paperSize){page ->
            Box(modifier = Modifier.size(IndicatorSize).clip(CircleShape)
                .background(color = if (page==selectedPage)selectedColor else unSelectedColor))
        }
    }

}