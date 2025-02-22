package com.lisimuoy.aub_sunday.alceda_mobileapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.lisimuoy.aub_sunday.R


@Preview(showSystemUi = true)
@Composable
fun PreviewTransferScreen() {
    val nc = rememberNavController()
    TransferScreen(nc)
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferScreen(navController: NavController) {
    val isKhmer = rememberSaveable { mutableStateOf(false) } // Language state
    val isLightMode = rememberSaveable { mutableStateOf(false) } // Light mode state

    // Function to return text dynamically
    fun getText(en: String, km: String) = if (isKhmer.value) km else en

    // Dynamic background color for the Column
    val backgroundColor = if (isLightMode.value) Color(0xFFF0F4F8) else Color(0xFF0F2C59)
    // Dynamic background color for the TopAppBar
    val topAppBarColor = if (isLightMode.value) Color(0xFFF0F4F8) else Color(0xFF0F2C59)
    val textColor = if (isLightMode.value) Color(0xFF050505) else Color(0xFFF0F4F8)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = getText("Transfers", "ផ្ទេរប្រាក់"), color = textColor, fontSize = 20.sp) // ✅ Translate Title
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = getText("Back", "ថយក្រោយ"),
                            tint = textColor
                        )
                    }
                },
                actions = {
                    // First Language Toggle Icon (Left)
                    IconButton(onClick = { isKhmer.value = !isKhmer.value }) {
                        Icon(
                            imageVector = Icons.Default.Language,
                            contentDescription = getText("Switch Language", "ប្តូរភាសា"),
                            tint = Color(0xFFC9A33A),
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    // Dark Mode / Light Mode Toggle Icon
                    IconButton(onClick = { isLightMode.value = !isLightMode.value }) {
                        Icon(
                            imageVector = if (isLightMode.value) Icons.Default.DarkMode else Icons.Default.LightMode,
                            contentDescription = if (isLightMode.value) getText("Light Mode", "របៀបពន្លឺ") else getText("Dark Mode", "របៀបងងឹត"),
                            tint = Color(0xFFC9A33A),
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    // Logo
                    AsyncImage(
                        model = "https://companieslogo.com/img/orig/ABC.KH.D-41331f8a.png?t=1720244490",
                        contentDescription = "Logo",
                        modifier = Modifier
                            .size(50.dp)
                            .padding(end = 16.dp),
                        contentScale = ContentScale.Fit
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = topAppBarColor // Dynamic TopAppBar background color
                )
            )
        },
        bottomBar = { BottomButton() },
        containerColor = Color(0xFFF0F4F8)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            HeaderSection()
            TransferForm()
        }
    }
}

@Composable
fun HeaderSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF0F2C59))
            .padding(vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ac),
            contentDescription = "Transfer Icon",
            modifier = Modifier.size(120.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "ACLEDA Accounts | Phone Numbers",
            fontSize = 18.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun TransferForm() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        AccountSelectionField()
        InputField("To Account", R.drawable.electricity, KeyboardType.Phone)
        InputField("Amount", R.drawable.electricity, KeyboardType.Number)
        ToggleOption("" +
                "Purpose")
        ToggleOption("Save to favorites")
    }
}

@Composable
fun AccountSelectionField() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "From Account", fontSize = 12.sp, color = Color.Gray)
                Text(text = "015 243 342", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            Text(text = "KHR", color = Color.Gray, fontSize = 14.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "168168.00", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "Dropdown",
                modifier = Modifier
                    .size(24.dp)
                    .rotate(-30f)
            )
        }
    }
}

@Composable
fun InputField(label: String, icon: Int, keyboardType: KeyboardType = KeyboardType.Text) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp)
    )
}

@Composable
fun ToggleOption(label: String) {
    var checked by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, fontSize = 16.sp, modifier = Modifier.weight(1f))
        Switch(checked = checked, onCheckedChange = { checked = it })
    }
}

@Composable
fun BottomButton() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF0F2C59))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "OK",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFFD700)
        )
    }
}