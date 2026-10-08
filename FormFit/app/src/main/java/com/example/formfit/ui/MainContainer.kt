package com.example.formfit.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.formfit.data.WorkoutRepository
import com.example.formfit.theme.CanvasBackground
import com.example.formfit.ui.components.AppTab
import com.example.formfit.ui.components.FormFitBottomNavBar
import com.example.formfit.ui.home.HomeScreen
import com.example.formfit.ui.player.WorkoutPlayerScreen
import com.example.formfit.ui.profile.ProfileScreen
import com.example.formfit.ui.summary.WorkoutSummaryScreen
import com.example.formfit.ui.workouts.WorkoutsScreen

enum class AppDestination {
    HOME,
    WORKOUTS,
    ACTIVE_PLAYER,
    SUMMARY,
    PROFILE
}

@Composable
fun MainContainer(
    repository: WorkoutRepository = remember { WorkoutRepository() }
) {
    var currentDestination by remember { mutableStateOf(AppDestination.HOME) }
    var currentTab by remember { mutableStateOf(AppTab.HOME) }

    val showBottomBar = currentDestination != AppDestination.ACTIVE_PLAYER &&
                        currentDestination != AppDestination.SUMMARY

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                FormFitBottomNavBar(
                    selectedTab = currentTab,
                    onTabSelected = { tab ->
                        currentTab = tab
                        currentDestination = when (tab) {
                            AppTab.HOME -> AppDestination.HOME
                            AppTab.WORKOUTS -> AppDestination.WORKOUTS
                            AppTab.HISTORY -> AppDestination.SUMMARY
                            AppTab.PROFILE -> AppDestination.PROFILE
                        }
                    }
                )
            }
        },
        containerColor = CanvasBackground
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(CanvasBackground)
        ) {
            when (currentDestination) {
                AppDestination.HOME -> {
                    HomeScreen(
                        repository = repository,
                        onStartWorkout = {
                            currentDestination = AppDestination.ACTIVE_PLAYER
                        }
                    )
                }
                AppDestination.WORKOUTS -> {
                    WorkoutsScreen(
                        repository = repository,
                        onStartWorkout = {
                            currentDestination = AppDestination.ACTIVE_PLAYER
                        }
                    )
                }
                AppDestination.ACTIVE_PLAYER -> {
                    WorkoutPlayerScreen(
                        repository = repository,
                        onExitWorkout = {
                            currentDestination = AppDestination.HOME
                            currentTab = AppTab.HOME
                        },
                        onWorkoutFinished = {
                            currentDestination = AppDestination.SUMMARY
                        }
                    )
                }
                AppDestination.SUMMARY -> {
                    WorkoutSummaryScreen(
                        repository = repository,
                        onReturnHome = {
                            currentDestination = AppDestination.HOME
                            currentTab = AppTab.HOME
                        }
                    )
                }
                AppDestination.PROFILE -> {
                    ProfileScreen(
                        repository = repository
                    )
                }
            }
        }
    }
}
