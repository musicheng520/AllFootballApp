package org.wit.allfootballapp.presentation.news.common

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import org.wit.allfootballapp.R
import org.wit.allfootballapp.domain.model.news.Article
import org.wit.allfootballapp.presentation.news.Dimensions.ArticleCardSize
import org.wit.allfootballapp.presentation.news.Dimensions.ExtraSmallPadding
import org.wit.allfootballapp.presentation.news.Dimensions.ExtraSmallPadding2
import org.wit.allfootballapp.presentation.news.Dimensions.smallIconSize



@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article,
    onClick:()-> Unit)
{
    val context = LocalContext.current


    Row(modifier=modifier.clickable{onClick()}) {
        Log.d("Test", article.urlToImage)
        Log.d("Test", article.url)
        Log.d("ContextTest", "Context: $context")

        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(article.urlToImage)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(ArticleCardSize)
                .clip(MaterialTheme.shapes.medium)
        )


        Column(verticalArrangement = Arrangement.SpaceAround, modifier = Modifier
            .padding(ExtraSmallPadding)
            .height(ArticleCardSize))
        {

            Text(
                text = article.title,

                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(
                    id = R.color.text_title
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = article.source.name,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(
                        id = R.color.body
                    )
                )

                Spacer(modifier= Modifier.width(ExtraSmallPadding2))

                Icon(painter = painterResource(id=R.drawable.ic_time),contentDescription = null,
                    modifier= Modifier.size(smallIconSize),
                    tint = colorResource(id = R.color.body)
                    )

                Spacer(modifier= Modifier.width(ExtraSmallPadding2))

                Text(
                    text = article.publishedAt,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(
                        id = R.color.body
                    )
                )

            }

        }

    }
}

