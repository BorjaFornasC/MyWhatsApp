package com.example.mywhatsapp

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun chats() {
    Text(text = "Chats")
}

data class Coffee(
    var nombreCafeteria: String,
    var direccion : String,
    @DrawableRes var photo: Int
)

fun getCoffee(): List<Coffee> {
    return listOf(
        Coffee(
            "Antico Caffè Greco",
            "St. Italy, Rome",
            R.drawable.image1
        ),
        Coffee(
            "Coffee Room",
            "St. Germany, Berlin",
            R.drawable.image2
        ),
        Coffee(
            "Coffee Ibiza",
            "St. Colon, Madrid",
            R.drawable.image3
        ),
        Coffee(
            "Pudding Coffee Shop",
            "St. Diagonal, Barcelona",
            R.drawable.image4
        ),
        Coffee(
            "L'Express",
            "St. Piccadilly Circus, London",
            R.drawable.image5
        ),
        Coffee(
            " Coffee Corner",
            "St. Àngel Guimerà, Valencia",
            R.drawable.image6
        ),
        Coffee(
            "Sweet Cup",
            "St. Kinkerstraat, Amsterdam",
            R.drawable.image7 
        )
    )
}

@Composable
fun ItemCoffee(coffee: Coffee) {
    Card(modifier =
    Modifier
        .clickable {  }
        .padding(10.dp)
        .fillMaxWidth(), elevation = CardDefaults.cardElevation(10.dp)) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = coffee.photo),
                contentDescription = "Avatar",
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(text = coffee.nombreCafeteria, fontSize = 50.sp,
                textAlign = TextAlign.Center, color = Color.Black)

            Spacer(modifier = Modifier.size(10.dp))


            Spacer(modifier = Modifier.size(30.dp))

            Text(text = coffee.direccion, modifier = Modifier.padding(start = 10.dp, bottom = 10.dp))

            Divider()

            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "RESERVE")
            }
        }
    }
}

@SuppressLint("ResourceType")
@Composable
fun CoffeeView() {
    LazyColumn {
        items(getCoffee()) { coffee ->
            ItemCoffee(coffee = coffee)
        }
    }
}