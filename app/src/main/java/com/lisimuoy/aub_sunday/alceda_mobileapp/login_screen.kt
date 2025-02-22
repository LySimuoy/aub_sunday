package com.lisimuoy.aub_sunday.alceda_mobileapp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PINEntryScreen(navController: NavController) {
    var pinInput by remember { mutableStateOf("") }
    val correctPin = "123456" // Set the correct PIN

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Back", color = Color.White, fontSize = 20.sp) }, // ✅ Fixed Title
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) { // ✅ Fix back navigation
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0F2C59)
                )
            )
        },
        containerColor = Color(0xFF0F2C59) // Set background color
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Profile Picture & Username
                ProfileSection(username = "John Doe")

                Spacer(modifier = Modifier.height(20.dp))

                // "Please Enter PIN" text
                Text(
                    text = "Please Enter PIN",
                    color = Color.White,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                // PIN Indicator Dots
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth(0.6f)
                ) {
                    repeat(6) { index ->
                        val isFilled = index < pinInput.length
                        Box(
                            modifier = Modifier
                                .size(14.dp)
                                .clip(CircleShape)
                                .background(if (isFilled) Color.White else Color(0xFF0F2C59))
                                .border(1.dp, Color.White, CircleShape)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Number Pad
                NumberPad(
                    onNumberClick = { number ->
                        if (pinInput.length < 6) {
                            pinInput += number

                            // Check PIN when the user enters 6 digits
                            if (pinInput.length == 6) {
                                if (pinInput == correctPin) {
                                    navController.navigate("account") {
                                        popUpTo("login") { inclusive = true } // Remove PIN screen from back stack
                                    }
                                } else {
                                    // ❌ Incorrect PIN → Reset the input
                                    pinInput = ""
                                }
                            }
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Forgot PIN and Cancel Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = "Forgot PIN",
                        color = Color.White,
                        fontSize = 14.sp,
                        modifier = Modifier.clickable { /* Handle Forgot PIN */ }
                    )

                    Text(
                        text = "Cancel",
                        color = Color.White,
                        fontSize = 14.sp,
                        modifier = Modifier.clickable {
                            navController.popBackStack() // Navigate back when "Cancel" is clicked
                        }
                    )
                }
            }
        }
    }
}



@Composable
fun ProfileSection(username: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profile Picture (Placeholder)
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color(0xFF0F2C59)),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape),
                model = "https://i.redd.it/zhou-ye-is-so-pretty-i-cant-wait-for-her-next-costume-drama-v0-7lpawdyzy3gc1.jpg?width=1290&format=pjpg&auto=webp&s=b126c98b0d9a35678bf1d126c47393b9ae23b263",
                contentDescription = null,
                contentScale = ContentScale.Crop,

                )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Username
        Text(
            text = username,
            color = Color.White,
            fontSize = 18.sp
        )
    }
}

@Composable
fun NumberPad(onNumberClick: (String) -> Unit) {
    val numbers = listOf(
        listOf("1", "2", "3"),
        listOf("4", "5", "6"),
        listOf("7", "8", "9"),
        listOf("0") // No delete button
    )

    Column {
        numbers.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                row.forEach { number ->
                    NumberButton(number = number, onClick = { onNumberClick(number) })
                }
            }
        }
    }
}


@Composable
fun NumberButton(number: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(80.dp)
            .clip(CircleShape)
            .background(Color(0xFF0F2C59)) // Ensure background stays consistent
            .border(1.dp, Color.White, CircleShape)
            .clickable { onClick() }, // ✅ Add click event
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = number,
            color = Color.White,
            fontSize = 24.sp
        )
    }
}


