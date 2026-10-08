package com.example.formfit.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.formfit.theme.BorderHairline
import com.example.formfit.theme.ElectricVolt
import com.example.formfit.theme.SurfaceElevated
import com.example.formfit.theme.TextTertiary

enum class AppTab(val label: String, val iconType: FormFitIconType) {
    HOME("Home", FormFitIconType.HOME),
    WORKOUTS("Workouts", FormFitIconType.WORKOUTS),
    HISTORY("History", FormFitIconType.HISTORY),
    PROFILE("Profile", FormFitIconType.PROFILE)
}

@Composable
fun FormFitBottomNavBar(
    selectedTab: AppTab,
    onTabSelected: (AppTab) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .drawBehind {
                drawLine(
                    color = BorderHairline,
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = 1.dp.toPx()
                )
            }
            .background(SurfaceElevated)
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppTab.values().forEach { tab ->
                val isSelected = tab == selectedTab
                val color = if (isSelected) ElectricVolt else TextTertiary

                Column(
                    modifier = Modifier
                        .clickable { onTabSelected(tab) }
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    FormFitIcon(
                        icon = tab.iconType,
                        tint = color,
                        size = 22.dp
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = tab.label,
                        color = color,
                        fontSize = 11.sp
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    if (isSelected) {
                        Box(
                            modifier = Modifier
                                .size(4.dp)
                                .background(ElectricVolt, CircleShape)
                        )
                    } else {
                        Spacer(modifier = Modifier.size(4.dp))
                    }
                }
            }
        }
    }
}
