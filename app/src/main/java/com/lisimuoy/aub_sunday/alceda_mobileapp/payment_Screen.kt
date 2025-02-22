package com.lisimuoy.aub_sunday.alceda_mobileapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.lisimuoy.aub_sunday.R

@Preview(showSystemUi = true)
@Composable
fun PreviewPaymentScreen() {
    val nc = rememberNavController()
    PaymentsScreen(nc)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentsScreen(navController: NavController) {
    val searchQuery = rememberSaveable { mutableStateOf("") } // Search state
    val isKhmer = rememberSaveable { mutableStateOf(false) } // Language state
    val isLightMode = rememberSaveable { mutableStateOf(false) } // Light mode state

    // Function to return text dynamically
    fun getText(en: String, km: String) = if (isKhmer.value) km else en

    // Dynamic background color for the Column
    val backgroundColor = if (isLightMode.value) Color(0xFFF0F4F8) else Color(0xFF0F2C59)
    // Dynamic background color for the TopAppBar
    val topAppBarColor = if (isLightMode.value) Color(0xFFF0F4F8) else Color(0xFF0F2C59)
    val textColor = if (isLightMode.value) Color(0xFF050505) else Color(0xFFF0F4F8)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor) // Dynamic background color
    ) {
        // Top App Bar with Cloned Language Icon
        TopAppBar(
            title = {
                Text(text = getText("Payments", "ការទូទាត់"), color = textColor, fontSize = 20.sp) // ✅ Translate Title
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
        // Centered Header Illustration
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = "https://lh3.googleusercontent.com/proxy/IHvwQVl2Cv6zclO7zWY8t6I_1Q0MOYshmpDBkuJDlK3tYmdhMcUbD71HwTA1-7BYcw",
                contentDescription = getText("Payments Illustration", "រូបភាពការទូទាត់"),
                modifier = Modifier.size(200.dp),
                contentScale = ContentScale.Fit
            )
        }
        // Search Bar with Dynamic Filtering
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
        ) {
            OutlinedTextField(
                value = searchQuery.value,
                onValueChange = { searchQuery.value = it },
                placeholder = { Text(text = getText("Search...", "ស្វែងរក..."), color = Color.Gray) }, // ✅ Translate Search Placeholder
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                )
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Filtered Payment Options
        val filteredOptions = paymentOptions.filter {
            it.title.contains(searchQuery.value, ignoreCase = true)
        }
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            items(filteredOptions) { option ->
                PaymentOptionItem(option, isKhmer.value) // Pass isKhmer
            }
        }
    }
}
// Payment Option Data Model
data class PaymentOption(val icon: Int, val title: String, val khmerTitle: String)
// Sample Payment Options Data
val paymentOptions = listOf(
    PaymentOption(R.drawable.auto, "Auto Payment", "ការទូទាត់ស្វ័យប្រវត្តិ"),
    PaymentOption(R.drawable.water, "Water Supply", "រដ្ឋាករទឹក"),
    PaymentOption(R.drawable.electricity, "Electricity", "អគ្គិសនី"),
    PaymentOption(R.drawable.interprice, "Enterprise", "សហគ្រាសគ្រប់គ្រងសំរាម (ស.ស.ភ)"),
    PaymentOption(R.drawable.agriculture, "Agriculture", "កសិកម្ម"),
    PaymentOption(R.drawable.auto, "B24", "B24"),
    PaymentOption(R.drawable.charity, "Charity & Donation", "ការបរិច្ចាក"),
    PaymentOption(R.drawable.ecommerce, "E-commerce", "អុីខុមមើស"),
    PaymentOption(R.drawable.financial, "Financial Bill", "វិក្កយបត្រហស្ថាប័នហិរញ្ញវត្ថុ"),
    PaymentOption(R.drawable.insurance, "Insurance", "ធានារ៉ាប់រង"),
    PaymentOption(R.drawable.internet, "Internet & TV", "អ៊ីនធឺណិត និងទូរទស្សន៍"),
    PaymentOption(R.drawable.member, "Membership", "សមាជិក"),
    PaymentOption(R.drawable.mobile, "Mobile Operator", "ប្រតិបត្តិករទូរស័ព្ទ"),
    PaymentOption(R.drawable.pending, "Pending Payment", "ការទូទាត់កំពុងរង់ចាំ"),
    PaymentOption(R.drawable.wastebill, "Solid Waste Bill", "វិក្កយបត្រចំណាយសំរាម"),
    PaymentOption(R.drawable.publicservice, "Public Services", "សេវាសាធារណៈ"),
    PaymentOption(R.drawable.realestate, "Real Estate", "អចលនទ្រព្យ"),
    PaymentOption(R.drawable.school, "School", "សាលារៀន"),
    PaymentOption(R.drawable.sharebill, "Share Bill", "ចែករំលែកវិក្កយបត្រ"),
    PaymentOption(R.drawable.trading, "Trading & Distribution", "ពាណិជ្ជកម្ម និងការចែកចាយ"),
)
// Payment Option UI Component
@Composable
fun PaymentOptionItem(option: PaymentOption, isKhmer: Boolean) {
    //  Function to return text dynamically
    fun getText(en: String, km: String) = if (isKhmer) km else en
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .clickable { /* Handle click */ }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = option.icon),
            contentDescription = option.title,
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(8.dp)),
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = getText(option.title, option.khmerTitle), fontSize = 16.sp, color = Color(0xFF0F2C59)) // ✅ Dynamic text
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Default.PlayArrow,
            contentDescription = getText("Go", "ទៅ"),
            tint = Color(0xFFC9A33A)
        )
    }
}


