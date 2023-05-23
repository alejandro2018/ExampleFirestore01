package com.example.examplefirestore01.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.examplefirestore01.navigation.Routes


@SuppressLint("SuspiciousIndentation")
@Composable
fun HomeView(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = "Home",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 30.sp,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
        )
    }
    Column(
        modifier = Modifier
            .padding(top = 120.dp)
    ){

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(70.dp)
            ,
            onClick = {
                navController.navigate(Routes.TestOne.route)
            },
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                text = "TestOne",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodySmall,
                fontSize = 25.sp,
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(70.dp)
            ,
            onClick = {
                navController.navigate(Routes.SettingsScreen.route)
            },
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                text = "Setting",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 25.sp,
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(70.dp)
            ,
            onClick = {
               // navController.navigate(Routes.TestOne.route)
            },
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                text = "TestTwo",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 25.sp,
            )
        }

    }
}