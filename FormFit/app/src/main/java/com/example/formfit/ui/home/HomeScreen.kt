package com.example.formfit.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.formfit.data.WorkoutRepository
import com.example.formfit.theme.BorderActive
import com.example.formfit.theme.BorderHairline
import com.example.formfit.theme.CanvasBackground
import com.example.formfit.theme.DarkGreyTile
import com.example.formfit.theme.ElectricVolt
import com.example.formfit.theme.PillBackground
import com.example.formfit.theme.SurfaceElevated
import com.example.formfit.theme.SurfaceSubtle
import com.example.formfit.theme.TagGreenBg
import com.example.formfit.theme.TagGreenBorder
import com.example.formfit.theme.TextPrimary
import com.example.formfit.theme.TextSecondary
import com.example.formfit.theme.TextTertiary
import com.example.formfit.ui.components.FormFitIcon
import com.example.formfit.ui.components.FormFitIconType

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    repository: WorkoutRepository,
    onStartWorkout: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val profile = repository.userProfile

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
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(SurfaceSubtle)
                        .border(1.5.dp, ElectricVolt, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    FormFitIcon(
                        icon = FormFitIconType.PROFILE,
                        tint = ElectricVolt,
                        size = 24.dp
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = "Monday, Oct 12",
                        color = TextSecondary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "Good morning,\n${profile.name}",
                        color = TextPrimary,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 22.sp
                    )
                }
            }

            // Streak Pill
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

        Spacer(modifier = Modifier.height(20.dp))

        // October 2026 Calendar / Streak Card
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
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        FormFitIcon(
                            icon = FormFitIconType.HISTORY,
                            tint = ElectricVolt,
                            size = 18.dp
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "October 2026",
                            color = TextPrimary,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(999.dp))
                            .background(SurfaceSubtle)
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = "🔥 8 Day Streak • Best: 14",
                            color = ElectricVolt,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Days Header (M T W T F S S)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    listOf("M", "T", "W", "T", "F", "S", "S").forEach { day ->
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = day,
                                color = TextSecondary,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Calendar Grid rows
                val row1 = listOf(
                    CalendarDay(29, isCurrentMonth = false, isWorkout = false),
                    CalendarDay(30, isCurrentMonth = false, isWorkout = false),
                    CalendarDay(1, isCurrentMonth = true, isWorkout = true),
                    CalendarDay(2, isCurrentMonth = true, isWorkout = true),
                    CalendarDay(3, isCurrentMonth = true, isWorkout = false),
                    CalendarDay(4, isCurrentMonth = true, isWorkout = true)
                )
                val row2 = listOf(
                    CalendarDay(5, isCurrentMonth = true, isWorkout = true),
                    CalendarDay(6, isCurrentMonth = true, isWorkout = true),
                    CalendarDay(7, isCurrentMonth = true, isWorkout = true),
                    CalendarDay(8, isCurrentMonth = true, isWorkout = true),
                    CalendarDay(9, isCurrentMonth = true, isWorkout = true),
                    CalendarDay(10, isCurrentMonth = true, isWorkout = false),
                    CalendarDay(11, isCurrentMonth = true, isWorkout = true)
                )
                val row3 = listOf(
                    CalendarDay(12, isCurrentMonth = true, isWorkout = false, isToday = true),
                    CalendarDay(13, isCurrentMonth = true, isWorkout = false),
                    CalendarDay(14, isCurrentMonth = true, isWorkout = false),
                    CalendarDay(15, isCurrentMonth = true, isWorkout = false),
                    CalendarDay(16, isCurrentMonth = true, isWorkout = false),
                    CalendarDay(17, isCurrentMonth = true, isWorkout = false),
                    CalendarDay(18, isCurrentMonth = true, isWorkout = false)
                )

                CalendarRow(row = (listOf(CalendarDay(28, isCurrentMonth = false, isWorkout = false)) + row1).take(7))
                Spacer(modifier = Modifier.height(6.dp))
                CalendarRow(row = row2)
                Spacer(modifier = Modifier.height(6.dp))
                CalendarRow(row = row3)

                Spacer(modifier = Modifier.height(12.dp))

                // Legend
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Active consistency",
                        color = TextSecondary,
                        fontSize = 11.sp
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "Rest", color = TextSecondary, fontSize = 11.sp)
                        Spacer(modifier = Modifier.width(4.dp))
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(DarkGreyTile)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = "Workout", color = TextSecondary, fontSize = 11.sp)
                        Spacer(modifier = Modifier.width(4.dp))
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(ElectricVolt)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Today's Workout Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(SurfaceElevated)
                .border(1.dp, BorderHairline, RoundedCornerShape(16.dp))
                .padding(18.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(999.dp))
                            .background(TagGreenBg)
                            .border(1.dp, TagGreenBorder.copy(alpha = 0.5f), RoundedCornerShape(999.dp))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "CHEST + TRICEPS",
                            color = ElectricVolt,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.5.sp
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .clip(CircleShape)
                                .background(ElectricVolt)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "Scheduled",
                            color = ElectricVolt,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Today's Workout",
                    color = TextPrimary,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "5 Exercises • ~45–50 min • Beginner routine",
                    color = TextSecondary,
                    fontSize = 13.sp
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "Session Plan:",
                    color = TextSecondary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Exercise Pills
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    repository.exercises.forEachIndexed { index, exercise ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(PillBackground)
                                .border(1.dp, BorderActive, RoundedCornerShape(8.dp))
                                .padding(horizontal = 10.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = "${index + 1} ${exercise.name}",
                                color = TextPrimary,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Big Green CTA Button
                Button(
                    onClick = onStartWorkout,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ElectricVolt,
                        contentColor = CanvasBackground
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Start Today's Workout",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = CanvasBackground
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        FormFitIcon(
                            icon = FormFitIconType.ARROW_FORWARD,
                            tint = CanvasBackground,
                            size = 18.dp
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Marcus Trainer Note Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(SurfaceElevated)
                .border(1.dp, BorderHairline, RoundedCornerShape(14.dp))
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                // Volt Accent left line
                Box(
                    modifier = Modifier
                        .width(4.dp)
                        .height(80.dp)
                        .background(ElectricVolt)
                )
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .clip(CircleShape)
                                .background(SurfaceSubtle)
                                .border(1.dp, ElectricVolt, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "M", color = ElectricVolt, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Trainer Note from Marcus",
                            color = TextPrimary,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Head Coach",
                            color = TextSecondary,
                            fontSize = 11.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "\"Focus on slow controlled eccentric movement on chest presses today! Quality form builds safety and strength.\"",
                        color = TextSecondary,
                        fontSize = 12.sp,
                        lineHeight = 16.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Bottom Stats Row (2 Columns)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Weekly Target
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
                        Text(text = "Weekly Target", color = TextSecondary, fontSize = 11.sp)
                        Text(text = "⚡", fontSize = 12.sp)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = "3",
                            color = TextPrimary,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Text(
                            text = " /4 days",
                            color = TextSecondary,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(bottom = 3.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    LinearProgressIndicator(
                        progress = { 0.75f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(5.dp)
                            .clip(RoundedCornerShape(999.dp)),
                        color = ElectricVolt,
                        trackColor = SurfaceSubtle
                    )
                }
            }

            // Total Volume
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
                        Text(text = "Total Volume", color = TextSecondary, fontSize = 11.sp)
                        Text(text = "📈", fontSize = 12.sp)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = "12.4",
                            color = TextPrimary,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Text(
                            text = " k kg",
                            color = TextSecondary,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(bottom = 3.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "↗ +15% vs last week",
                        color = ElectricVolt,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(80.dp)) // Padding for bottom bar
    }
}

data class CalendarDay(
    val dayNum: Int,
    val isCurrentMonth: Boolean,
    val isWorkout: Boolean,
    val isToday: Boolean = false
)

@Composable
fun CalendarRow(row: List<CalendarDay>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        row.forEach { day ->
            val bg = when {
                day.isToday -> SurfaceSubtle
                day.isWorkout -> ElectricVolt
                else -> DarkGreyTile
            }
            val textColor = when {
                day.isToday -> ElectricVolt
                day.isWorkout -> CanvasBackground
                day.isCurrentMonth -> TextSecondary
                else -> TextTertiary
            }
            val borderModifier = if (day.isToday) {
                Modifier.border(1.5.dp, ElectricVolt, RoundedCornerShape(8.dp))
            } else {
                Modifier
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 2.dp)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(bg)
                    .then(borderModifier),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${day.dayNum}",
                    color = textColor,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
