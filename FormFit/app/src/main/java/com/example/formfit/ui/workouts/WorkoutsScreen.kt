package com.example.formfit.ui.workouts

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.formfit.data.WorkoutRepository
import com.example.formfit.theme.BorderActive
import com.example.formfit.theme.BorderHairline
import com.example.formfit.theme.CanvasBackground
import com.example.formfit.theme.DarkGreyTile
import com.example.formfit.theme.ElectricVolt
import com.example.formfit.theme.KineticCyan
import com.example.formfit.theme.SurfaceElevated
import com.example.formfit.theme.SurfaceSubtle
import com.example.formfit.theme.TagGreenBg
import com.example.formfit.theme.TextPrimary
import com.example.formfit.theme.TextSecondary
import com.example.formfit.theme.TextTertiary
import com.example.formfit.ui.components.FormFitIcon
import com.example.formfit.ui.components.FormFitIconType

@Composable
fun WorkoutsScreen(
    repository: WorkoutRepository,
    onStartWorkout: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    var selectedTopTab by remember { mutableIntStateOf(0) } // 0: Weekly Plan, 1: Exercise Library
    var selectedDayIndex by remember { mutableIntStateOf(1) } // TUE 25

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
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(SurfaceSubtle)
                        .border(1.5.dp, ElectricVolt, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "JM",
                        color = ElectricVolt,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = "PROGRAM",
                        color = TextSecondary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp
                    )
                    Text(
                        text = "Workouts",
                        color = TextPrimary,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(999.dp))
                    .background(SurfaceSubtle)
                    .border(1.dp, BorderActive, RoundedCornerShape(999.dp))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "🔥", fontSize = 13.sp)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "5 Day Streak",
                        color = TextPrimary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Segmented Control (Weekly Plan | Exercise Library)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(SurfaceElevated)
                .border(1.dp, BorderHairline, RoundedCornerShape(12.dp))
                .padding(4.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                val tab1Bg = if (selectedTopTab == 0) SurfaceSubtle else Color.Transparent
                val tab1Text = if (selectedTopTab == 0) ElectricVolt else TextSecondary
                val tab2Bg = if (selectedTopTab == 1) SurfaceSubtle else Color.Transparent
                val tab2Text = if (selectedTopTab == 1) ElectricVolt else TextSecondary

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(8.dp))
                        .background(tab1Bg)
                        .clickable { selectedTopTab = 0 }
                        .padding(vertical = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "📋", fontSize = 13.sp)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Weekly Plan",
                            color = tab1Text,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(8.dp))
                        .background(tab2Bg)
                        .clickable { selectedTopTab = 1 }
                        .padding(vertical = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "🏋️", fontSize = 13.sp)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Exercise Library",
                            color = tab2Text,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Coach Protocol Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(SurfaceElevated)
                .border(1.dp, BorderHairline, RoundedCornerShape(14.dp))
                .padding(16.dp)
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "🚩", fontSize = 13.sp)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Assigned by Coach Marcus • 2d ago",
                        color = TextSecondary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Custom strength protocol focused on chest & back hypertrophy",
                    color = TextPrimary,
                    fontSize = 13.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Week 3 of 8 • 4 Workouts",
                        color = TextSecondary,
                        fontSize = 12.sp
                    )

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(SurfaceSubtle)
                            .border(1.dp, BorderActive, RoundedCornerShape(8.dp))
                            .clickable { }
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "Edit / Create Workout +",
                            color = ElectricVolt,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Day Picker Row (MON 24 -> SUN 30)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val daysList = listOf(
                Triple("MON", "24", true), // completed
                Triple("TUE", "25", false), // today
                Triple("WED", "26", false),
                Triple("THU", "27", false),
                Triple("FRI", "28", false),
                Triple("SAT", "29", false),
                Triple("SUN", "30", false)
            )

            daysList.forEachIndexed { index, (day, num, completed) ->
                val isSelected = index == selectedDayIndex
                val isToday = index == 1
                val bg = when {
                    isToday -> ElectricVolt
                    isSelected -> SurfaceSubtle
                    else -> DarkGreyTile
                }
                val textColor = if (isToday) CanvasBackground else TextPrimary

                Box(
                    modifier = Modifier
                        .width(48.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(bg)
                        .border(
                            1.dp,
                            if (isToday) ElectricVolt else if (isSelected) BorderActive else BorderHairline,
                            RoundedCornerShape(10.dp)
                        )
                        .clickable { selectedDayIndex = index }
                        .padding(vertical = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = day, color = if (isToday) CanvasBackground else TextSecondary, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = num, color = textColor, fontSize = 14.sp, fontWeight = FontWeight.ExtraBold)
                        Spacer(modifier = Modifier.height(4.dp))
                        if (completed) {
                            Text(text = "✓", color = ElectricVolt, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        } else if (isToday) {
                            Box(modifier = Modifier.size(4.dp).clip(CircleShape).background(CanvasBackground))
                        } else {
                            Text(text = "•", color = TextTertiary, fontSize = 11.sp)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Routine Schedule List
        // 1. MONDAY (Completed)
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
                    Text(
                        text = "MONDAY • 5 exercises",
                        color = TextSecondary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(999.dp))
                            .background(TagGreenBg)
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = "✓ Completed",
                            color = ElectricVolt,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Chest + Triceps",
                    color = TextPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Bench Press, Incline DB, Tricep Dips +2",
                        color = TextSecondary,
                        fontSize = 12.sp
                    )
                    Text(
                        text = "48m logged",
                        color = TextSecondary,
                        fontSize = 12.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // 2. TUESDAY (TODAY - Active Workout)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(SurfaceElevated)
                .border(1.5.dp, ElectricVolt.copy(alpha = 0.8f), RoundedCornerShape(14.dp))
                .padding(16.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "TUESDAY • 5 exercises",
                            color = ElectricVolt,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(ElectricVolt)
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "TODAY",
                                color = CanvasBackground,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(999.dp))
                            .background(SurfaceSubtle)
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = "● Scheduled",
                            color = ElectricVolt,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Back + Biceps",
                    color = TextPrimary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Exercise sub-rows with drag/order icons
                listOf(
                    Pair("1 Lat Pulldown", "4 sets x 10–12 reps"),
                    Pair("2 Cable Row", "3 sets x 12 reps"),
                    Pair("3 Barbell Curl", "3 sets x 10 reps")
                ).forEach { (name, repInfo) ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(SurfaceSubtle)
                            .padding(horizontal = 12.dp, vertical = 10.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(text = name, color = TextPrimary, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                                Text(text = repInfo, color = TextSecondary, fontSize = 11.sp)
                            }
                            Text(text = "⠿", color = TextTertiary, fontSize = 16.sp)
                        }
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Start Tuesday Workout Button
                Button(
                    onClick = onStartWorkout,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ElectricVolt,
                        contentColor = CanvasBackground
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        FormFitIcon(
                            icon = FormFitIconType.PLAY,
                            tint = CanvasBackground,
                            size = 16.dp
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Start Tuesday Workout",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = CanvasBackground
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // 3. WEDNESDAY
        SimpleScheduledDayCard(
            dayHeader = "WEDNESDAY • 6 exercises",
            title = "Legs + Shoulders",
            tags = "Squats  •  Leg Press  •  Lateral Raise  •  +3 more"
        )

        Spacer(modifier = Modifier.height(14.dp))

        // 4. THURSDAY (Recovery)
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
                    Text(
                        text = "THURSDAY • Recovery",
                        color = KineticCyan,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(999.dp))
                            .background(KineticCyan.copy(alpha = 0.15f))
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = "▲ Rest Day",
                            color = KineticCyan,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Rest & Active Recovery",
                    color = TextPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(SurfaceSubtle)
                        .padding(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "🧘", fontSize = 14.sp)
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(text = "15-Min Light Mobility Protocol", color = TextPrimary, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                Text(text = "Hamstrings, thoracic spine & hips", color = TextSecondary, fontSize = 10.sp)
                            }
                        }
                        Text(text = "Guide >", color = KineticCyan, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // 5. FRIDAY
        SimpleScheduledDayCard(
            dayHeader = "FRIDAY • 5 exercises",
            title = "Chest + Triceps",
            tags = "Incline Dumbbell Press  •  Cable Flyes  •  +3 more"
        )

        Spacer(modifier = Modifier.height(14.dp))

        // 6. SAT & SUN (Regeneration)
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
                    Text(
                        text = "SAT & SUN • Regeneration",
                        color = TextSecondary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(999.dp))
                            .background(SurfaceSubtle)
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = "Optional Zone 2",
                            color = TextSecondary,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Rest & Cardio",
                    color = TextPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Outdoor walk or 30m light cycling recommended",
                        color = TextSecondary,
                        fontSize = 11.sp,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "10k Steps Goal",
                        color = ElectricVolt,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(80.dp))
    }
}

@Composable
fun SimpleScheduledDayCard(
    dayHeader: String,
    title: String,
    tags: String
) {
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
                Text(text = dayHeader, color = TextSecondary, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(999.dp))
                        .background(SurfaceSubtle)
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(text = "● Scheduled", color = TextSecondary, fontSize = 11.sp)
                }
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = title, color = TextPrimary, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = tags, color = TextSecondary, fontSize = 12.sp)
        }
    }
}
