package com.example.superheroes

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroes.model.Hero
import com.example.superheroes.model.HeroesRepository.heroes
import com.example.superheroes.ui.theme.SuperheroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SuperheroesApp()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SuperheroesApp(){
    Scaffold(
        topBar = {
            HeroesAppBar(modifier = Modifier)
        }
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()){
            items(heroes){
                HeroesCard(hero = it, modifier = Modifier)
            }
        }
    }
}

@Composable
fun HeroesAppBar(modifier: Modifier){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.background),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.h1,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun HeroesCard(hero: Hero, modifier: Modifier){
    Card(modifier = Modifier.padding(8.dp), elevation = 2.dp){
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ){
                Column(modifier = modifier.weight(0.7f)){
                    HeroesID(hero.nameRes)
                    HeroesInfo(hero.descriptionRes)
                }
                HeroesDP(hero.imageRes)
            }
        }
    }
}

@Composable
fun HeroesID(@StringRes nameRes: Int){
    Text(
        text = stringResource(id = nameRes),
        style = MaterialTheme.typography.h3
    )
}

@Composable
fun HeroesInfo(@StringRes descriptionRes: Int){
    Text(
        text = stringResource(id = descriptionRes),
        style = MaterialTheme.typography.body1
    )
}

@Composable
fun HeroesDP(@DrawableRes imageRes: Int) {
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(64.dp)
            .clip(RoundedCornerShape(8.dp))
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SuperheroesTheme (darkTheme = false){
        SuperheroesApp()
    }
}

@Preview
@Composable
fun DarkThemePreview(){
    SuperheroesTheme(darkTheme = true) {
        SuperheroesApp()
    }
}