package com.lisimuoy.aub_sunday.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lisimuoy.aub_sunday.alceda_mobileapp.AccountScreen
import com.lisimuoy.aub_sunday.alceda_mobileapp.MainScreen
import com.lisimuoy.aub_sunday.alceda_mobileapp.PINEntryScreen
import com.lisimuoy.aub_sunday.alceda_mobileapp.PaymentsScreen
import com.lisimuoy.aub_sunday.alceda_mobileapp.TransferMenu
import com.lisimuoy.aub_sunday.alceda_mobileapp.TransferScreen
import com.lisimuoy.aub_sunday.alceda_mobileapp.WelcomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") { WelcomeScreen(navController) }
        composable("main") { MainScreen(navController) }
        composable("login") { PINEntryScreen(navController) }
        composable("payments") { PaymentsScreen(navController) }
        composable("account") { AccountScreen(navController) }
        composable("transfermenu") { TransferMenu(navController) }
        composable("transferscreen") { TransferScreen(navController) }
    }
}
