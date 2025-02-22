package com.lisimuoy.aub_sunday.alceda_mobileapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage

@Preview(showSystemUi = true)
@Composable
fun PreviewAccountScreen() {
    val nc = rememberNavController()
    AccountScreen(nc)
}

@Composable
fun AccountScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFF0F2C59)).padding(top = 30.dp)
    ) {
        TopBar(title = "ACCOUNTS",) {
            navController.navigate("main")
        }
        AccountTabs()
        AccountList(accountsList)
        Spacer(modifier = Modifier.height(260.dp))
        TransactionHistory()
    }
}


@Composable
fun TopBar(title: String, onBackClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF0F2C59))
            .padding(horizontal = 4.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = "Back",
                tint = Color.White
            )
        }

        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )

        // Logo
        AsyncImage(
            model = "https://companieslogo.com/img/orig/ABC.KH.D-41331f8a.png?t=1720244490",
            contentDescription = "Logo",
            modifier = Modifier
                .size(50.dp)
                .padding(end = 16.dp),
            contentScale = ContentScale.Fit
        )
    }
}


@Composable
fun AccountTabs() {
    Row(
        modifier = Modifier.fillMaxWidth().background(Color(0xFF051C3B)),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        listOf("ACCOUNTS", "TRADING", "CARDS", "LINK").forEach { tab ->
            Text(
                text = tab,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = if (tab == "ACCOUNTS") Color.White else Color(0xFFC9A33A),
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}

@Composable
fun AccountList(accounts: List<com.lisimuoy.aub_sunday.alceda_mobileapp.Account>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
    ) {
        items(accounts) { account ->
            AccountItem(account)
        }
    }
}
@Composable
fun AccountItem(account: com.lisimuoy.aub_sunday.alceda_mobileapp.Account) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 25.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = when (account.accountType) {
                        "Wallet Account" -> Icons.Default.AccountBalanceWallet
                        "Savings Account" -> Icons.Default.Savings
                        else -> Icons.Default.AccountCircle
                    },
                    contentDescription = "Account Icon",
                    tint = when (account.accountType) {
                        "Wallet Account" -> Color(0xFFC9A33A)  // Gold color for wallet account
                        "Savings Account" -> Color(0xFFC9A33A) // Blue color for savings
                        else -> Color.Gray  // Default color
                    },
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = account.accountNumber,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = "Available balance",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = account.accountType,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Text(
                    text = "${account.balance} ${account.currency}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (account.currency == "KHR") Color(0xFFC9A33A) else Color(0xFF1C1C1C)
                )
                if (account.workingBalance != null) {
                    Text(
                        text = "${account.workingBalance} ${account.currency}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFC9A33A)
                    )
                }
            }
        }
    }
}


@Composable
fun TransactionHistory() {
    Column(
        modifier = Modifier
            .fillMaxWidth()  // Make the column take the full width
            .height(60.dp)   // Set the desired height
            .background(Color(0xFF051C3B)), // Add bottom padding
        verticalArrangement = Arrangement.Center // Center text vertically within the column
    ) {
        Text(
            text = "TRANSACTION HISTORY",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFC9A33A),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF051C3B))
                .padding(12.dp),
            textAlign = TextAlign.Center
        )
    }
}

