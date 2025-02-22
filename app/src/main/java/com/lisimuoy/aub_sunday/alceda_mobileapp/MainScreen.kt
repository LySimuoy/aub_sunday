package com.lisimuoy.aub_sunday.alceda_mobileapp
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MobileFriendly
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Moving
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
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
fun PreviewMainScreen() {
    val nc = rememberNavController()
    MainScreen(nc)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    val isDarkMode = rememberSaveable { mutableStateOf(false) }

    val backgroundColor = if (isDarkMode.value) Color(0xFF10294B) else Color(0xFF0F2C59)
    val topBarColor = backgroundColor
    val textColor = if (isDarkMode.value) Color.White else Color.LightGray
    val iconColor = Color(0xFFC9A33A)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = backgroundColor
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = topBarColor),
                    title = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth() // Make sure it takes the full width
                                .padding(start = 15.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start // Align items to the start
                        ) {
                            AsyncImage(
                                modifier = Modifier
                                    .size(170.dp, 50.dp), // Adjust size if needed
                                model = "https://www.acledabank.com.kh/kh/assets/layout/logo3.png",
                                contentDescription = null,
                                contentScale = ContentScale.Fit
                            )
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = {}
                        ) {
                            Icon(
                                imageVector = Icons.Default.SupportAgent,
                                contentDescription = null,
                                modifier = Modifier.size(30.dp),
                                tint = Color.White
                            )
                        }
                        IconButton(
                            onClick = {}
                        ) {
                            Box(
                                modifier = Modifier
                                    .background(Color.Red)
                                    .size(25.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.QrCode,
                                    contentDescription = null,
                                    modifier = Modifier.align(Alignment.Center),
                                    tint = Color.White
                                )
                            }
                        }
                    }

                )
            },
            bottomBar = {
                BottomAppBar(containerColor = Color(0xFF10294B), contentColor = textColor) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BottomNavItem(
                            icon = Icons.Default.Home,
                            label = ("Home"),
                            onClick = {},
                            textColor = textColor,
                            iconColor = iconColor
                        )
                        BottomNavItem(
                            icon = Icons.Default.FavoriteBorder,
                            label = ("Favorites"),
                            onClick = {},
                            textColor = textColor,
                            iconColor = iconColor
                        )
                        BottomNavItem(
                            icon = Icons.Default.NotificationsNone,
                            label = ("Notifications"),
                            onClick = {},
                            textColor = textColor,
                            iconColor = iconColor
                        )
                        BottomNavItem(
                            icon = Icons.Default.Menu,
                            label = ("Menu"),
                            onClick = {},
                            textColor = textColor,
                            iconColor = iconColor
                        )
                    }
                }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(backgroundColor)
            ) {
                myMainBodyScreen(isDarkMode.value, textColor)
                TestRow(navController, isDarkMode.value, textColor, iconColor)
                Text(
                    text = ("Welcome!"),
                    color = textColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}



@Composable
fun BottomNavItem(icon: ImageVector, label: String, onClick: () -> Unit, textColor: Color, iconColor: Color) {
    val customBodyStyle = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        color = textColor
    )

    Surface(
        modifier = Modifier.size(96.dp),
        shape = RoundedCornerShape(size = 2.dp),
        color = Color.Transparent
    ) {
        IconButton(onClick = onClick) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(32.dp),
                    tint = iconColor
                )
                Text(
                    text = label,
                    style = customBodyStyle,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}




@Composable
fun myMainBodyScreen(isDarkMode: Boolean, textColor: Color) {
    // Define background color based on theme mode
    val backgroundColor = if (isDarkMode) Color(0xFF10294B) else Color(0xFF0F2C59)

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(top = 20.dp),
        color = backgroundColor // Ensure Surface takes the dynamic color
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor), // Apply the same dynamic color to Row
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Surface(
                modifier = Modifier
                    .size(width = 75.dp, height = 60.dp)
                    .padding(start = 15.dp),
                color = Color.Transparent,
            ) {
                AsyncImage(
                    modifier = Modifier
                        .padding(6.dp)
                        .fillMaxSize()
                        .clip(CircleShape),
                    model = "https://i.redd.it/zhou-ye-is-so-pretty-i-cant-wait-for-her-next-costume-drama-v0-7lpawdyzy3gc1.jpg?width=1290&format=pjpg&auto=webp&s=b126c98b0d9a35678bf1d126c47393b9ae23b263",
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
            }

            Surface(
                modifier = Modifier
                    .size(width = 200.dp, height = 60.dp)
                    .padding(start = 5.dp, top = 5.dp, bottom = 5.dp),
                color = Color.Transparent,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(2.dp)
                ) {
                    Text(
                        text = "Hello, Bopha",
                        color = textColor,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start,
                        fontSize = 16.sp,
                    )

                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Surface(
                            modifier = Modifier.size(width = 40.dp, height = 18.dp),
                            color = Color.Transparent,
                        ) {
                            Text(
                                text = "Profile",
                                color = textColor,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 2.dp),
                                textAlign = TextAlign.Start,
                                fontSize = 12.sp,
                            )
                        }
                        Surface(
                            modifier = Modifier.size(width = 18.dp, height = 18.dp),
                            color = Color.Transparent,
                        ) {
                            IconButton(
                                onClick = {},
                            ) {
                                Icon(
                                    Icons.Default.ArrowBackIosNew,
                                    contentDescription = null,
                                    tint = textColor,
                                    modifier = Modifier
                                        .rotate(-180f)
                                        .size(13.dp),
                                )
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}


@Composable
fun TestRow(navController: NavController, value: Boolean, textColor: Color, iconColor: Color) { // Add navController parameter
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F2C59))
            .padding(16.dp)
    ) {
        BalanceCard(navController, accountsList)

        Spacer(modifier = Modifier.height(8.dp))
        TopActionGrid(navController)

        MiddleActionGrid(navController) // Pass it correctly

        BottomActionGrid()
        Spacer(modifier = Modifier.height(8.dp))
        Services()
    }
}

@Composable
fun BottomNavItem(icon: ImageVector, label: String, onClick: () -> Unit) {
    val customBodyStyle = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        color = Color.White
    )
    val isKhmer = rememberSaveable { mutableStateOf(false) } // Language state

    fun getText(en: String, km: String) = if (isKhmer.value) km else en


    Surface(
        modifier = Modifier.size(96.dp),
        shape = RoundedCornerShape(size = 2.dp),
        color = Color.Transparent
    ) {
        IconButton(onClick = onClick) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(32.dp),
                    tint = Color(0xFFC9A33A)
                )
                Text(
                    text = getText("Accounts", "ក"),
                    style = customBodyStyle,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

@Composable
fun BalanceCard(navController: NavController, accounts: List<Account>) {
    val isKhmer = rememberSaveable { mutableStateOf(false) } // Language state

    fun getText(en: String, km: String) = if (isKhmer.value) km else en

    // Calculate total balance for each currency
    val totalKHR = accounts.filter { it.currency == "KHR" }
        .sumOf { it.balance.toDoubleOrNull() ?: 0.0 }

    val totalUSD = accounts.filter { it.currency == "USD" }
        .sumOf { it.balance.toDoubleOrNull() ?: 0.0 }

    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(Color(0xFF10294B)),
        modifier = Modifier
            .fillMaxWidth()
            .border(BorderStroke(1.dp, Color(0xFF066D9B)), RoundedCornerShape(12.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween, // Ensures space between the two columns
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left column: Icon with circular progress indicator
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Surface(
                    modifier = Modifier
                        .size(96.dp)
                        .border(BorderStroke(4.dp, Color(0xFFC9A33A)), androidx.compose.foundation.shape.CircleShape),
                    shape = RoundedCornerShape(size = 2.dp),
                    color = Color.Transparent
                ) {
                    IconButton(
                        onClick = { navController.navigate("login") } // Navigate to Account Screen
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Icon(
                                imageVector = Icons.Default.AccountBalanceWallet,
                                contentDescription = null,
                                modifier = Modifier.size(42.dp),
                                tint = Color(0xFFC9A33A)
                            )

                            Text(
                                text = getText("Accounts", "គ"),
                                color = Color.White,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }
                }
            }

            // Right column: Text details (Total Balances)
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {
                Text(getText("Total Balances", "សមតុល្យសរុប"), color = Color.White, fontSize = 14.sp)

                if (totalKHR > 0) {
                    Text(
                        text = String.format("%,.2f ៛", totalKHR), // Format with comma separators
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                if (totalUSD > 0) {
                    Text(
                        text = String.format("%,.2f $", totalUSD),
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}


@Composable
fun TopActionGrid(navController: NavController) {
    val actions = listOf(
        "Payments", "Mobile Top-up",
    )

    // Icons for each action
    val icons = listOf(
        Icons.Default.Payment,
        Icons.Default.MobileFriendly  // You can add more icons if needed
    )

    Column {
        // Display actions in rows
        for (i in actions.chunked(2)) { // Create rows of 2 items
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                for ((index, action) in i.withIndex()) {
                    val icon = icons.getOrElse(index) { Icons.Default.FavoriteBorder }
                    TopActionCard(
                        icon = icon,
                        title = action,
                        onClick = {
                            when (action) {
                                "Payments" -> navController.navigate("payments")
                                // Add other actions here if needed
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun TopActionCard(icon: ImageVector, title: String, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(Color(0xFF10294B)),
        modifier = Modifier
            .size(width = 180.dp, height = 65.dp)
            .clickable { onClick() }
            .border(BorderStroke(1.dp, Color(0xFF066D9B)),RoundedCornerShape(12.dp))
    ) {
        // Use Row to center the content horizontally and vertically
        Row(
            horizontalArrangement = Arrangement.Center, // Centers the content horizontally
            verticalAlignment = Alignment.CenterVertically, // Centers the content vertically
            modifier = Modifier
                .padding(15.dp)
                .fillMaxSize()
        ) {
            // Display the icon
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(32.dp),  // Adjust icon size
                tint = Color(0xFFC9A33A)  // Apply the tint to the icon
            )

            // Add a spacer between icon and title to avoid them overlapping
            Spacer(modifier = Modifier.width(8.dp))

            // Display the title
            Text(
                text = title,
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
fun MiddleActionGrid(navController: NavController) {
    val actions = listOf(
        "Pay-me", "Scan QR", "Transfers"
    )

    val icons = listOf(
        Icons.Default.Receipt,
        Icons.Default.QrCodeScanner,
        Icons.Default.Repeat
    )

    Column {
        for (i in actions.indices step 3) { // Loop in chunks of 3
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                for (j in 0 until minOf(3, actions.size - i)) {
                    val index = i + j
                    val action = actions[index]
                    val icon = icons.getOrElse(index) { Icons.Default.FavoriteBorder }

                    MiddleActionCard(
                        icon = icon,
                        title = action,
                        onClick = {
//                            if (action == "Pay-me") {
//                                navController.navigate("payments") // Navigate to PINEntryScreen
//                            }

                            if (action == "Transfers") {
                                navController.navigate("transfermenu") // Navigate to PINEntryScreen
                            }

                        }
                    )
                }
            }
        }
    }
}


@Composable
fun MiddleActionCard(icon: ImageVector, title: String, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(Color(0xFF10294B)),
        modifier = Modifier
            .size(115.dp)
            .clickable { onClick() } // Handle click event
            .border(BorderStroke(1.dp, Color(0xFF066D9B)), RoundedCornerShape(12.dp))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(25.dp)
                .fillMaxSize()
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(32.dp),
                tint = Color(0xFFC9A33A)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = title,
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
fun BottomActionGrid() {
    val actions = listOf(
        "Deposits", "Loans", "QuickCash",

        )

    // Icons for each action
    val icons = listOf(
        Icons.Default.Moving,
        Icons.Default.MonetizationOn,
        Icons.Default.CreditCard,
    )

    Column {
        // Display actions in rows
        for (i in actions.chunked(3)) { // Create rows of 3 items
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                for ((index, action) in i.withIndex()) {
                    // Safely get an icon, use a default one if necessary
                    val icon = icons.getOrElse(index) { Icons.Default.FavoriteBorder }
                    BottomActionCard(icon = icon, title = action)
                }
            }
        }
    }
}

@Composable
fun BottomActionCard(icon: ImageVector, title: String) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(Color(0xFF10294B)),
        modifier = Modifier
            .size(115.dp)
            .clickable { /* Add action */ }
            .border(BorderStroke(1.dp, Color(0xFF066D9B)), RoundedCornerShape(12.dp))
    ) {
        // Use Column to arrange the icon and text vertically
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, // Centers the content horizontally
            verticalArrangement = Arrangement.Center, // Centers the content vertically
            modifier = Modifier
                .padding(24.dp)
                .fillMaxSize()// Adjust padding for the icon-text alignment
        ) {
            // Display the icon
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(32.dp), // Adjust icon size
                tint = Color(0xFFC9A33A) // Apply the tint to the icon
            )

            // Add a spacer between icon and title to avoid overlap
            Spacer(modifier = Modifier.height(4.dp))

            // Display the title under the icon
            Text(
                text = title,
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
fun Services() {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(Color(0xFF10294B)),
        modifier = Modifier.fillMaxWidth()
    ) {
        val actions = listOf("Services", "Location", "Market", "Exchange Rate")
        val icons = listOf(
            Icons.Default. AccountBalance,      // Replace with appropriate icons
            Icons.Default.LocationOn,
            Icons.Default.Storefront,
            Icons.Default.CurrencyExchange,

        )

        Column(modifier = Modifier.padding(2.dp)) { // Add padding to the container
            for (rowItems in actions.chunked(4)) {  // 4 items per row
                Row(
                    horizontalArrangement = Arrangement.spacedBy(2.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    rowItems.forEachIndexed { index, action ->
                        val icon = icons.getOrElse(index) { Icons.Default.FavoriteBorder }
                        ServicesCard(icon = icon, title = action)
                    }
                }
            }
        }
    }
}

@Composable
fun ServicesCard(icon: ImageVector, title: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(90.dp)  // Width to control each card size
            .padding(4.dp)
    ) {
        // Card with rounded corners
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(Color(0xFF0B1E36)),
            modifier = Modifier
                .size(50.dp)  // Icon card size
                .border(BorderStroke(1.dp, Color(0xFF066D9B)), RoundedCornerShape(12.dp))
                .clickable { /* Add click action here */ }
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    modifier = Modifier.size(30.dp),  // Icon size
                    tint = Color(0xFFC9A33A)  // Gold tint
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = title,
            color = Color.White,
            fontSize = 12.sp,  // Smaller font for the label
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

