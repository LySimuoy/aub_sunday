package com.lisimuoy.aub_sunday.alceda_mobileapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreen(navController: NavController) {

    LaunchedEffect(Unit) {
        delay(4000) // Delay for 5 seconds
        navController.navigate("main") {
            popUpTo("welcome") { inclusive = true } // Remove WelcomeScreen from back stack
        }
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { /* Empty lambda, no title */ },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0F2C59),
                ),
                modifier = Modifier
                    .height(200.dp)
            )
        }
    ) {
        Surface(
            modifier = Modifier.padding(it)
        ) {
            BodyScreen()
        }
    }
}

@Composable
fun BodyScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F2C59)),
    ) {
        Surface(
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.TopCenter)
            ,
            color = Color.Transparent
        ) {
            AsyncImage(
                model = "https://companieslogo.com/img/orig/ABC.KH.D-41331f8a.png?t=1720244490",
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )

        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        ) {
            // Content for text remains centered
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "ACLEDA Bank PLC.-Credentials",
                fontSize = 17.sp,
                color = Color(128, 128, 128)
            )

            // Spacer to create spacing between text and rows
            Spacer(modifier = Modifier.height(24.dp))

            // One row with three columns (images)
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp), // Space between columns
                verticalAlignment = Alignment.CenterVertically, // Vertically center the content
            ) {
                // First Image Column
                Surface(
                    modifier = Modifier
                        .size(width = 50.dp, height = 35.dp),
                    shape = RoundedCornerShape(size=2.dp),
                    color = Color.Transparent
                ) {
                    AsyncImage(
                        model = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ4Y30v5pvq8JfXBKAyPzKz46bPqdC-qPyOlpBQ2q_TIhcKRjRCmDRkp_MASLHeJWjpHZY&usqp=CAU",
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                }

                // Second Image Column
                Surface(
                    modifier = Modifier.size(width = 50.dp, height = 35.dp),
                    shape = RoundedCornerShape(size=2.dp),
                    color = Color.Transparent
                ) {
                    AsyncImage(
                        model = "https://www.acledabank.com.kh/kh/assets/image/IRQS.jpg",
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                }

                // Third Image Column
                Surface(
                    modifier = Modifier.size(width = 50.dp, height = 35.dp),
                    shape = RoundedCornerShape(size=2.dp),
                    color = Color.Transparent
                ) {
                    AsyncImage(
                        model = "https://www.acledabank.com.kh/kh/assets/image/IRQS-2.jpg",
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "© 2025 ACLEDA BANK PLC.",
                fontSize = 20.sp,
                color = Color.White
            )
        }
    }
}
