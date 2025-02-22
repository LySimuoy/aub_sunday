package com.lisimuoy.aub_sunday.alceda_mobileapp

data class Account(
    val accountNumber: String,
    val accountType: String,
    val balance: String,
    val currency: String,
    val isWallet: Boolean = false,
    val isCreditCard: Boolean = false,
    val workingBalance: String? = null
)