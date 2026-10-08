package com.example.formfit.ui.profile

import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.formfit.data.WorkoutRepository
import com.example.formfit.theme.BorderActive
import com.example.formfit.theme.BorderHairline
import com.example.formfit.theme.CanvasBackground
import com.example.formfit.theme.DarkGreyTile
import com.example.formfit.theme.ElectricVolt
import com.example.formfit.theme.ElectricVoltDim
import com.example.formfit.theme.SurfaceElevated
import com.example.formfit.theme.SurfaceSubtle
import com.example.formfit.theme.TagGreenBg
import com.example.formfit.theme.TagGreenBorder
import com.example.formfit.theme.TextPrimary
import com.example.formfit.theme.TextSecondary
import com.example.formfit.theme.TextTertiary
import com.example.formfit.ui.components.FormFitIcon
import com.example.formfit.ui.components.FormFitIconType

@Composable
fun ProfileScreen(
    repository: WorkoutRepository,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val profile = repository.userProfile
    var showEditWeightDialog by remember { mutableStateOf(false) }
    var weightInput by remember { mutableStateOf("${profile.bodyWeightKg}") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(CanvasBackground)
            .verticalScroll(scrollState)
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        // Top Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Profile",
                color = TextPrimary,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            IconButton(onClick = {}) {
                FormFitIcon(
                    icon = FormFitIconType.SETTINGS,
                    tint = TextSecondary,
                    size = 22.dp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // User Profile Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(SurfaceElevated)
                .border(1.dp, BorderHairline, RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(54.dp)
                            .clip(CircleShape)
                            .background(SurfaceSubtle)
                            .border(2.dp, ElectricVolt, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        FormFitIcon(
                            icon = FormFitIconType.PROFILE,
                            tint = ElectricVolt,
                            size = 30.dp
                        )
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .size(12.dp)
                                .clip(CircleShape)
                                .background(ElectricVolt)
                                .border(2.dp, CanvasBackground, CircleShape)
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = profile.name,
                                color = TextPrimary,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(ElectricVoltDim)
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "PRO",
                                    color = ElectricVolt,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Member since ${profile.memberSince}",
                            color = TextSecondary,
                            fontSize = 12.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "CURRENT TARGET",
                        color = TextSecondary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(999.dp))
                            .background(TagGreenBg)
                            .border(1.dp, TagGreenBorder.copy(alpha = 0.4f), RoundedCornerShape(999.dp))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "● ${profile.target}",
                            color = ElectricVolt,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Body Metrics Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(SurfaceElevated)
                .border(1.dp, BorderHairline, RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "BODY METRICS",
                        color = TextSecondary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp
                    )
                    Text(
                        text = "🔄 Auto-synced",
                        color = ElectricVolt,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "BODY WEIGHT",
                            color = TextSecondary,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Row(verticalAlignment = Alignment.Bottom) {
                            Text(
                                text = "${profile.bodyWeightKg}",
                                color = TextPrimary,
                                fontSize = 28.sp,
                                fontWeight = FontWeight.ExtraBold
                            )
                            Text(
                                text = " kg",
                                color = TextSecondary,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                        }
                    }

                    // Edit Weight Button
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(ElectricVolt)
                            .clickable { showEditWeightDialog = true }
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            FormFitIcon(
                                icon = FormFitIconType.EDIT,
                                tint = CanvasBackground,
                                size = 14.dp
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Edit Weight",
                                color = CanvasBackground,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Trend Curve Box
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(SurfaceSubtle)
                        .padding(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "📉", fontSize = 12.sp)
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "-1.2 kg this month",
                                    color = ElectricVolt,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Text(
                                text = "Consistent surplus curve",
                                color = TextSecondary,
                                fontSize = 10.sp
                            )
                        }

                        // Sparkline graph
                        Canvas(modifier = Modifier.size(width = 90.dp, height = 28.dp)) {
                            val path = Path().apply {
                                moveTo(0f, size.height * 0.7f)
                                quadraticTo(
                                    size.width * 0.4f, size.height * 0.8f,
                                    size.width * 0.7f, size.height * 0.35f
                                )
                                lineTo(size.width, size.height * 0.15f)
                            }
                            drawPath(
                                path = path,
                                color = ElectricVolt,
                                style = Stroke(width = 2.5.dp.toPx(), cap = StrokeCap.Round)
                            )
                            drawCircle(
                                color = ElectricVolt,
                                radius = 3.dp.toPx(),
                                center = Offset(size.width, size.height * 0.15f)
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // 2-Column Metrics (Height & Volume)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(14.dp))
                    .background(SurfaceElevated)
                    .border(1.dp, BorderHairline, RoundedCornerShape(14.dp))
                    .padding(14.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "HEIGHT", color = TextSecondary, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        Text(text = "📏", fontSize = 12.sp)
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(text = "${profile.heightCm}", color = TextPrimary, fontSize = 22.sp, fontWeight = FontWeight.ExtraBold)
                        Text(text = " cm", color = TextSecondary, fontSize = 12.sp, modifier = Modifier.padding(bottom = 3.dp))
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Fixed profile metric", color = TextSecondary, fontSize = 10.sp)
                }
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(14.dp))
                    .background(SurfaceElevated)
                    .border(1.dp, BorderHairline, RoundedCornerShape(14.dp))
                    .padding(14.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "VOLUME", color = TextSecondary, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        Text(text = "🔁", fontSize = 12.sp)
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(text = "${profile.totalVolumeLogs}", color = TextPrimary, fontSize = 22.sp, fontWeight = FontWeight.ExtraBold)
                        Text(text = " Logs", color = TextSecondary, fontSize = 12.sp, modifier = Modifier.padding(bottom = 3.dp))
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Total workouts logged", color = TextSecondary, fontSize = 10.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // 8 Days Streak Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(SurfaceElevated)
                .border(1.dp, BorderHairline, RoundedCornerShape(14.dp))
                .padding(16.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "🔥", fontSize = 14.sp)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = "8 Days Streak", color = TextPrimary, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    }
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(ElectricVoltDim)
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(text = "ACTIVE", color = ElectricVolt, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Personal best this season", color = TextSecondary, fontSize = 11.sp)
                Spacer(modifier = Modifier.height(12.dp))

                // 7 Green Streak Bars
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    listOf("M", "T", "W", "T", "F", "S", "S").forEach { day ->
                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = day, color = TextSecondary, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(6.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(24.dp)
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(ElectricVolt)
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Coaching & Schedule
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(SurfaceElevated)
                .border(1.dp, BorderHairline, RoundedCornerShape(14.dp))
                .padding(16.dp)
        ) {
            Column {
                Text(text = "COACHING & SCHEDULE", color = TextSecondary, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.5.sp)
                Spacer(modifier = Modifier.height(12.dp))

                // Current Coach
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(SurfaceSubtle),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "🏃", fontSize = 14.sp)
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(text = "Current Coach", color = TextSecondary, fontSize = 10.sp)
                            Text(text = profile.coachName, color = TextPrimary, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                        }
                    }
                    Text(text = "💬", fontSize = 14.sp)
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Current Routine
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(SurfaceSubtle),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "📅", fontSize = 14.sp)
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(text = "Current Routine", color = TextSecondary, fontSize = 10.sp)
                            Text(text = profile.routineName, color = TextPrimary, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                        }
                    }
                    Text(text = "›", color = TextSecondary, fontSize = 18.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Settings & Preferences
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(SurfaceElevated)
                .border(1.dp, BorderHairline, RoundedCornerShape(14.dp))
                .padding(16.dp)
        ) {
            Column {
                Text(text = "SETTINGS & PREFERENCES", color = TextSecondary, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.5.sp)
                Spacer(modifier = Modifier.height(12.dp))

                // Rest Timer Vibrations
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "👁", fontSize = 14.sp)
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(text = "Rest Timer Vibrations", color = TextPrimary, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                            Text(text = "Haptic alert at 00:00", color = TextSecondary, fontSize = 11.sp)
                        }
                    }
                    Switch(
                        checked = profile.hapticVibrations,
                        onCheckedChange = { repository.toggleVibrations(it) },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = CanvasBackground,
                            checkedTrackColor = ElectricVolt,
                            uncheckedThumbColor = TextSecondary,
                            uncheckedTrackColor = SurfaceSubtle
                        )
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Dark Theme
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "🌙", fontSize = 14.sp)
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(text = "Dark Theme", color = TextPrimary, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                            Text(text = "Locked Dark for gym focus", color = TextSecondary, fontSize = 11.sp)
                        }
                    }
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(SurfaceSubtle)
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(text = "🔒 Locked", color = TextSecondary, fontSize = 11.sp)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Metric Units
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "📐", fontSize = 14.sp)
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(text = "Metric Units", color = TextPrimary, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                            Text(text = "Standardized format", color = TextSecondary, fontSize = 11.sp)
                        }
                    }
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(SurfaceSubtle)
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(text = "kg / cm", color = ElectricVolt, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Weigh-in Protocol Callout
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(TagGreenBg)
                .border(1.dp, TagGreenBorder.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .width(4.dp)
                        .height(64.dp)
                        .background(ElectricVolt)
                )
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "💡", fontSize = 16.sp)
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(text = "Weigh-in Protocol", color = TextPrimary, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "For optimal accuracy, log your weight first thing in the morning after waking up and hydrating.",
                            color = TextSecondary,
                            fontSize = 11.sp,
                            lineHeight = 15.sp
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(80.dp))
    }

    // Edit Weight Dialog
    if (showEditWeightDialog) {
        AlertDialog(
            onDismissRequest = { showEditWeightDialog = false },
            title = { Text(text = "Update Body Weight", color = TextPrimary, fontWeight = FontWeight.Bold) },
            text = {
                Column {
                    Text(text = "Enter current weight (kg):", color = TextSecondary, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = weightInput,
                        onValueChange = { weightInput = it },
                        singleLine = true
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val parsed = weightInput.toDoubleOrNull()
                        if (parsed != null) {
                            repository.updateWeight(parsed)
                        }
                        showEditWeightDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = ElectricVolt, contentColor = CanvasBackground)
                ) {
                    Text("Save", fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showEditWeightDialog = false }) {
                    Text("Cancel", color = TextSecondary)
                }
            },
            containerColor = SurfaceElevated
        )
    }
}
