package com.csmain.feature_details.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DetailsScreen(
    id: String,
    onBackClick: () -> Unit,
    viewModel: DetailsViewModel = hiltViewModel(),
) {

}
