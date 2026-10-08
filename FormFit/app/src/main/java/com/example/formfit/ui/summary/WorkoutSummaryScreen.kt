package com.example.formfit.ui.summary

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.example.formfit.theme.ElectricVoltDark
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
fun WorkoutSummaryScreen(
    repository: WorkoutRepository,
    onReturnHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val profile = repository.userProfile
    val totalTimeMin = (repository.workoutSeconds / 60).coerceAtLeast(48)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(CanvasBackground)
            .verticalScroll(scrollState)
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        // Top Bar (SESSION SUMMARY | X)
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
                    .padding(horizontal = 10.dp, vertical = 5.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(ElectricVolt)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "SESSION SUMMARY",
                        color = ElectricVolt,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp
                    )
                }
            }

            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(SurfaceSubtle)
                    .clickable { onReturnHome() },
                contentAlignment = Alignment.Center
            ) {
                FormFitIcon(
                    icon = FormFitIconType.CLOSE,
                    tint = TextPrimary,
                    size = 18.dp
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Hero Celebration Graphic
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(ElectricVoltDark)
                    .border(2.dp, ElectricVoltDim, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "🎉", fontSize = 42.sp)

                // Top right small checkmark badge
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 4.dp, end = 4.dp)
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(ElectricVolt),
                    contentAlignment = Alignment.Center
                ) {
                    FormFitIcon(
                        icon = FormFitIconType.CHECK,
                        tint = CanvasBackground,
                        size = 14.dp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Workout Complete!",
                color = TextPrimary,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Chest + Triceps • Great effort today, ${profile.name}!",
                color = TextSecondary,
                fontSize = 13.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Completion Stats Card
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
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        FormFitIcon(
                            icon = FormFitIconType.CHECK,
                            tint = ElectricVolt,
                            size = 16.dp
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "5 / 5 Exercises Completed",
                            color = TextPrimary,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(999.dp))
                            .background(TagGreenBg)
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = "100%",
                            color = ElectricVolt,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                LinearProgressIndicator(
                    progress = { 1f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(999.dp)),
                    color = ElectricVolt,
                    trackColor = SurfaceSubtle
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 3 Stat Tiles
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    SummaryMetricBox(
                        modifier = Modifier.weight(1f),
                        label = "TIME SPENT",
                        value = "${totalTimeMin}m"
                    )
                    SummaryMetricBox(
                        modifier = Modifier.weight(1f),
                        label = "TOTAL VOL.",
                        value = "12.4t",
                        isAccent = true
                    )
                    SummaryMetricBox(
                        modifier = Modifier.weight(1f),
                        label = "INTENSITY",
                        value = "Optimal",
                        sublabel = "Hypertrophy"
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Streak Card
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
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "🔥", fontSize = 16.sp)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "8 Day Streak!",
                            color = TextPrimary,
                            fontSize = 16.sp,
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
                            text = "Personal Best",
                            color = TextSecondary,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "WEEK 24", color = TextSecondary, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    Text(text = "Active & Consistent", color = ElectricVolt, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Streak Grid Row (M, T, W, T, F, TODAY, S)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    val days = listOf(
                        Triple("M", true, false),
                        Triple("T", true, false),
                        Triple("W", true, false),
                        Triple("T", true, false),
                        Triple("F", true, false),
                        Triple("TODAY", false, true), // lightning bolt
                        Triple("S", false, false) // dot
                    )

                    days.forEach { (label, done, isToday) ->
                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = label,
                                color = if (isToday) ElectricVolt else TextSecondary,
                                fontSize = if (isToday) 9.sp else 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(1f)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(
                                        when {
                                            isToday -> ElectricVolt
                                            done -> ElectricVoltDim
                                            else -> DarkGreyTile
                                        }
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                when {
                                    isToday -> Text(text = "⚡", fontSize = 14.sp)
                                    done -> Text(text = "✓", color = ElectricVolt, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                    else -> Text(text = "•", color = TextTertiary, fontSize = 12.sp)
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Next Up Box
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(SurfaceSubtle)
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Box(
                            modifier = Modifier
                                .width(3.dp)
                                .height(56.dp)
                                .background(ElectricVolt)
                        )
                        Row(
                            modifier = Modifier
                                .padding(12.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "🕒", fontSize = 16.sp)
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = "NEXT UP",
                                    color = TextSecondary,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 0.5.sp
                                )
                                Text(
                                    text = "Tomorrow: Back + Biceps",
                                    color = TextPrimary,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "5 exercises scheduled",
                                    color = TextSecondary,
                                    fontSize = 11.sp
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Done - Return Home CTA Button
        Button(
            onClick = onReturnHome,
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
                    text = "Done — Return Home",
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

        Spacer(modifier = Modifier.height(14.dp))

        // Share Milestone Card Link
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { }
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "🔗", fontSize = 13.sp)
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "Share Milestone Card",
                color = TextSecondary,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
fun SummaryMetricBox(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    sublabel: String? = null,
    isAccent: Boolean = false
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(SurfaceSubtle)
            .border(1.dp, BorderActive, RoundedCornerShape(10.dp))
            .padding(vertical = 12.dp, horizontal = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = label,
                color = TextSecondary,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                color = if (isAccent) ElectricVolt else TextPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold
            )
            if (sublabel != null) {
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = sublabel,
                    color = TextSecondary,
                    fontSize = 10.sp
                )
            }
        }
    }
}
