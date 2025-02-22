package com.lisimuoy.aub_sunday.alceda_mobileapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.lisimuoy.aub_sunday.R

@Preview(showSystemUi = true)
@Composable
fun PreviewTransferMenu() {
    val nc = rememberNavController()
    TransferMenu(nc)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferMenu(navController: NavController) {
    val isKhmer = rememberSaveable { mutableStateOf(false) } // Language state
    val isLightMode = rememberSaveable { mutableStateOf(false) } // Dark mode state

    // Function to return text dynamically
    fun getText(en: String, km: String) = if (isKhmer.value) km else en

    // Dynamic background color for the Column
    val backgroundColor = if (isLightMode.value) Color(0xFFF0F4F8) else Color(0xFF0F2C59)
    // Dynamic background color for the TopAppBar
    val topAppBarColor = if (isLightMode.value) Color(0xFFF0F4F8) else Color(0xFF0F2C59)
    // Dynamic background color for the TopAppBar
    val BannerSection = if (isLightMode.value) Color(0xFF050505) else Color(0xFFF0F4F8)
    val textColor = if (isLightMode.value) Color(0xFF050505) else Color(0xFFF0F4F8)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = getText("Transfers", "ផ្ទេរប្រាក់"), // Dynamic title based on language
                        color = textColor,
                        fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = getText("Back", "ថយក្រោយ"), // Dynamic description
                            tint = textColor
                        )
                    }
                },
                actions = {
                    // First Language Toggle Icon (Left)
                    IconButton(onClick = { isKhmer.value = !isKhmer.value }) {
                        Icon(
                            imageVector = Icons.Default.Language,
                            contentDescription = getText("Switch Language", "ប្តូរភាសា"), // Dynamic description
                            tint = Color(0xFFC9A33A),
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    // Dark Mode / Light Mode Toggle Icon
                    IconButton(onClick = { isLightMode.value = !isLightMode.value }) {
                        Icon(
                            imageVector = if (isLightMode.value) Icons.Default.DarkMode else Icons.Default.LightMode,
                            contentDescription = if (isLightMode.value) getText("Light Mode", "របៀបពន្លឺ") else getText("Dark Mode", "របៀបងងឹត"), // Dynamic description
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
        containerColor = backgroundColor // Dynamic background color for the Scaffold
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(backgroundColor) // Dynamic background color for the Column
        ) {
            BannerSection()
            TransactionList(transactions = transactionItems, navController = navController, isKhmer = isKhmer.value) // Pass the list of transactions, NavController, and language state
        }
    }
}

@Composable
fun BannerSection() {
    val isLightMode = rememberSaveable { mutableStateOf(false) } // Dark mode state
    val bannercolor = if (isLightMode.value) Color(0xFFF0F4F8) else Color(0xFF0F2C59)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(bannercolor),
//            .background(Color(0xFF0F2C59)),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = "https://png.pngtree.com/png-vector/20221022/ourmid/pngtree-mobile-bank-users-transferring-money-png-image_6339062.png",
            contentDescription = "Banner Image",
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(width = 300.dp, height = 200.dp)
        )
    }
}

data class TransactionItem(
    val icon: Int, // Resource ID for the icon
    val titleEn: String, // Title of the transaction in English
    val titleKm: String, // Title of the transaction in Khmer
    val descriptionEn: String, // Description of the transaction in English
    val descriptionKm: String // Description of the transaction in Khmer
)
val transactionItems = listOf(
    TransactionItem(R.drawable.owntransfer, "Own Accounts", "គណនីផ្ទាល់ខ្លួន", "Own account transfers", "ការផ្ទេរគណនីផ្ទាល់ខ្លួន"),
    TransactionItem(R.drawable.acleda, "ACLEDA Accounts", "គណនីអេសុីលីដា | លេខទូរស័ព្ទ", "Transfers to ACLEDA accounts", "ផ្ទេរទៅគណនីអេសុីលីដា | លេខទូរស័ព្ទ"),
    TransactionItem(R.drawable.localtransfer, "Local Transfers", "ផ្ទេរក្នុងប្រទេស", "Transfer to banks|MFIs|wallets", "ផ្ទេរទៅធនាគារ|MFIs|វ៉លឡេត"),
    TransactionItem(R.drawable.international, "International Transfers", "ផ្ទេរប្រាក់ទៅក្រៅប្រទេស", "Transfer worldwide to banks|agents", "ផ្ទេរទៅធនាគារ|ភ្នាក់ងារទូទាំងពិភពលោក"),
    TransactionItem(R.drawable.card, "Card Transfers", "ផ្ទេរប្រាក់ទៅប័ណ្ណ", "Transfer to other bank cards", "ផ្ទេរទៅប័ណ្ណរបស់ធនាគារផ្សេងទៀត"),
    TransactionItem(R.drawable.trading, "Trading Accounts", "គណនីជួញដូរ", "ACLEDA-trading account transfers", "ផ្ទេរប្រាក់រវាងគណនីអេសុីលីដា និង គណនីជួញដូរ"),
)
@Composable
fun TransactionList(transactions: List<TransactionItem>, navController: NavController, isKhmer: Boolean) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp) // Add padding for better spacing
    ) {
        items(transactions) { transaction ->
            TransactionItemView(
                item = transaction,
                navController = navController, // Pass the NavController
                isKhmer = isKhmer, // Pass the language state
                onClick = {
                    // Handle click for this transaction item
                    println("Clicked on ${transaction.titleEn}")
                }
            )
        }
    }
}

@Composable
fun TransactionItemView(item: TransactionItem, navController: NavController, onClick: () -> Unit, isKhmer: Boolean) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onClick() }, // Handle click using the provided lambda
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // Add elevation for better visual feedback
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon
            Image(
                painter = painterResource(id = item.icon),
                contentDescription = null, // Provide a meaningful description if applicable
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 12.dp),
                contentScale = ContentScale.Fit // Ensure the icon scales properly
            )
            // Title and Description
            Column(
                modifier = Modifier.weight(1f) // Allow this column to take remaining space
            ) {
                Text(
                    text = if (isKhmer) item.titleKm else item.titleEn,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black // Ensure text is visible
                )
                Text(
                    text = if (isKhmer) item.descriptionKm else item.descriptionEn,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
            // Arrow Icon
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "Go", // Provide a meaningful description
                tint = Color(0xFFC9A33A),
                modifier = Modifier
                    .size(24.dp) // Adjust size for consistency
                    .clickable {
                        // Navigate to transferscreen when the arrow is clicked
                        if (item.titleEn == "ACLEDA Accounts") {
                            navController.navigate("transferscreen")
                        }
                    }
            )
        }
    }
}