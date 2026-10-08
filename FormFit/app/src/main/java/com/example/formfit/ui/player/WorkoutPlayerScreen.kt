package com.example.formfit.ui.player

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
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
import com.example.formfit.theme.KineticCyan
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
import kotlinx.coroutines.delay

@Composable
fun WorkoutPlayerScreen(
    repository: WorkoutRepository,
    onExitWorkout: () -> Unit,
    onWorkoutFinished: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val exercises = repository.exercises
    val currentIndex = repository.currentExerciseIndex
    val currentExercise = exercises.getOrNull(currentIndex) ?: exercises.first()

    // Live ticking timer
    var timerSeconds by remember { mutableStateOf(repository.workoutSeconds) }
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            timerSeconds++
            repository.workoutSeconds = timerSeconds
        }
    }

    val minutes = timerSeconds / 60
    val seconds = timerSeconds % 60
    val timeFormatted = String.format("%02d:%02d", minutes, seconds)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(CanvasBackground)
    ) {
        // Top App Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { onExitWorkout() }
                    .padding(horizontal = 8.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                FormFitIcon(
                    icon = FormFitIconType.ARROW_BACK,
                    tint = TextPrimary,
                    size = 18.dp
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Exit Workout",
                    color = TextPrimary,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            // Live Timer Badge
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(999.dp))
                    .background(SurfaceSubtle)
                    .border(1.dp, BorderActive, RoundedCornerShape(999.dp))
                    .padding(horizontal = 12.dp, vertical = 5.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(7.dp)
                            .clip(CircleShape)
                            .background(ElectricVolt)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = timeFormatted,
                        color = ElectricVolt,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Sound Toggle Icon
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(SurfaceSubtle)
                    .border(1.dp, BorderHairline, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "🔊", fontSize = 14.sp)
            }
        }

        // Scrollable Body
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp)
        ) {
            // Header: CHEST + TRICEPS, SESSION #24
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "CHEST + TRICEPS",
                    color = TextPrimary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = "SESSION #24",
                    color = TextSecondary,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Progress Section
            val completedCount = repository.completedExerciseCount()
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Progress", color = TextSecondary, fontSize = 12.sp)
                Text(
                    text = "$completedCount of ${exercises.size} completed",
                    color = ElectricVolt,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Segmented Progress Bar (5 bars)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                exercises.forEachIndexed { idx, ex ->
                    val barColor = when {
                        ex.isCompleted -> ElectricVolt
                        idx == currentIndex -> ElectricVolt.copy(alpha = 0.6f)
                        else -> SurfaceSubtle
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(4.dp)
                            .clip(RoundedCornerShape(999.dp))
                            .background(barColor)
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Exercise Selection Carousel
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "🔄", fontSize = 12.sp)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Tap or swipe to do in any order",
                        color = TextSecondary,
                        fontSize = 11.sp
                    )
                }
                Text(
                    text = "${exercises.size} Exercises",
                    color = TextSecondary,
                    fontSize = 11.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Exercise Pills horizontal scroll
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                exercises.forEachIndexed { idx, ex ->
                    val isSelected = idx == currentIndex
                    val pillBg = if (isSelected) ElectricVolt else PillBackground
                    val textColor = if (isSelected) CanvasBackground else TextPrimary
                    val border = if (isSelected) ElectricVolt else BorderActive

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(999.dp))
                            .background(pillBg)
                            .border(1.dp, border, RoundedCornerShape(999.dp))
                            .clickable { repository.currentExerciseIndex = idx }
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "${idx + 1}. ${ex.name}",
                                color = textColor,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                            if (ex.isCompleted) {
                                Spacer(modifier = Modifier.width(4.dp))
                                FormFitIcon(
                                    icon = FormFitIconType.CHECK,
                                    tint = textColor,
                                    size = 13.dp
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Main Exercise Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(SurfaceElevated)
                    .border(1.dp, BorderHairline, RoundedCornerShape(16.dp))
                    .padding(16.dp)
            ) {
                Column {
                    // Category & Info
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(999.dp))
                                    .background(TagGreenBg)
                                    .border(1.dp, TagGreenBorder.copy(alpha = 0.5f), RoundedCornerShape(999.dp))
                                    .padding(horizontal = 8.dp, vertical = 3.dp)
                            ) {
                                Text(
                                    text = currentExercise.muscleGroup,
                                    color = ElectricVolt,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = currentExercise.category,
                                color = TextSecondary,
                                fontSize = 12.sp
                            )
                        }

                        FormFitIcon(
                            icon = FormFitIconType.INFO,
                            tint = TextSecondary,
                            size = 18.dp
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = currentExercise.name,
                        color = TextPrimary,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraBold
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Movement Animation & Diagram View
                    PecDeckMovementDiagram(
                        holdCue = currentExercise.holdCue,
                        tempoCue = currentExercise.tempoCue
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Form Cue Box with lightbulb icon
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(TagGreenBg)
                            .border(1.dp, TagGreenBorder.copy(alpha = 0.3f), RoundedCornerShape(10.dp))
                            .padding(12.dp)
                    ) {
                        Row {
                            Text(text = "💡", fontSize = 14.sp)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = currentExercise.formCue,
                                color = TextPrimary,
                                fontSize = 12.sp,
                                lineHeight = 16.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Target Metrics Row (3 Columns)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        MetricTile(
                            modifier = Modifier.weight(1f),
                            label = "REPS TARGET",
                            value = "${currentExercise.repsTarget}",
                            sublabel = currentExercise.repType
                        )
                        MetricTile(
                            modifier = Modifier.weight(1f),
                            label = "SETS",
                            value = "${currentExercise.setsTarget}",
                            sublabel = currentExercise.setType
                        )
                        MetricTile(
                            modifier = Modifier.weight(1f),
                            label = "LOAD",
                            value = "${currentExercise.loadKg.toInt()}",
                            unit = "kg",
                            sublabel = currentExercise.equipmentNote,
                            isAccent = true
                        )
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    // Set Checklist Section
                    val finishedSets = currentExercise.sets.count { it.isCompleted }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "SET CHECKLIST",
                            color = TextSecondary,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.5.sp
                        )
                        Text(
                            text = "$finishedSets of ${currentExercise.sets.size} finished",
                            color = TextSecondary,
                            fontSize = 11.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Set rows
                    currentExercise.sets.forEachIndexed { sIdx, sItem ->
                        SetRowItem(
                            set = sItem,
                            onToggle = { repository.toggleSetCompletion(currentIndex, sIdx) }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Mark Exercise Completed Button
                    val isLastExercise = currentIndex == exercises.size - 1
                    Button(
                        onClick = {
                            repository.completeExercise(currentIndex)
                            if (isLastExercise || repository.completedExerciseCount() == exercises.size) {
                                onWorkoutFinished()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
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
                            FormFitIcon(
                                icon = FormFitIconType.CHECK,
                                tint = CanvasBackground,
                                size = 18.dp
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (isLastExercise) "Finish Workout" else "Mark Exercise Completed",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = CanvasBackground
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }

        // Bottom Bar (Previous | Exercise X/Y | Next)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(SurfaceElevated)
                .border(1.dp, BorderHairline, RoundedCornerShape(0.dp))
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Previous
                Row(
                    modifier = Modifier
                        .clickable(enabled = currentIndex > 0) {
                            if (currentIndex > 0) repository.currentExerciseIndex--
                        }
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "⏮", fontSize = 13.sp)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Previous",
                        color = if (currentIndex > 0) TextPrimary else TextTertiary,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Text(
                    text = "Exercise ${currentIndex + 1} / ${exercises.size}",
                    color = TextSecondary,
                    fontSize = 12.sp
                )

                // Next
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (currentIndex < exercises.size - 1) SurfaceSubtle else Color.Transparent)
                        .clickable {
                            if (currentIndex < exercises.size - 1) {
                                repository.currentExerciseIndex++
                            } else {
                                onWorkoutFinished()
                            }
                        }
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (currentIndex < exercises.size - 1) "Next Exercise" else "Summary",
                        color = ElectricVolt,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    FormFitIcon(
                        icon = FormFitIconType.ARROW_FORWARD,
                        tint = ElectricVolt,
                        size = 14.dp
                    )
                }
            }
        }
    }
}

@Composable
fun MetricTile(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    unit: String? = null,
    sublabel: String,
    isAccent: Boolean = false
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(SurfaceSubtle)
            .border(1.dp, BorderActive, RoundedCornerShape(12.dp))
            .padding(vertical = 12.dp, horizontal = 8.dp),
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
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = value,
                    color = if (isAccent) ElectricVolt else TextPrimary,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                if (unit != null) {
                    Text(
                        text = " $unit",
                        color = if (isAccent) ElectricVolt else TextSecondary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 3.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = sublabel,
                color = TextSecondary,
                fontSize = 10.sp
            )
        }
    }
}

@Composable
fun SetRowItem(
    set: com.example.formfit.model.ExerciseSet,
    onToggle: () -> Unit
) {
    val isDone = set.isCompleted
    val isCurrent = set.isCurrent && !isDone
    val borderColor = when {
        isDone -> TagGreenBorder.copy(alpha = 0.6f)
        isCurrent -> ElectricVolt
        else -> BorderHairline
    }
    val bgColor = if (isCurrent) SurfaceSubtle else SurfaceElevated

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(bgColor)
            .border(if (isCurrent) 1.5.dp else 1.dp, borderColor, RoundedCornerShape(12.dp))
            .clickable { onToggle() }
            .padding(horizontal = 14.dp, vertical = 10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Checkmark toggle circle
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(if (isDone) ElectricVolt else Color.Transparent)
                        .border(
                            2.dp,
                            if (isDone) ElectricVolt else if (isCurrent) ElectricVolt else BorderActive,
                            CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (isDone) {
                        FormFitIcon(
                            icon = FormFitIconType.CHECK,
                            tint = CanvasBackground,
                            size = 14.dp
                        )
                    } else if (isCurrent) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(ElectricVolt)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = "Set ${set.setNumber}",
                    color = TextPrimary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

                if (isCurrent) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(ElectricVoltDim)
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "CURRENT",
                            color = ElectricVolt,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "${set.targetReps} reps @ ${set.weightKg.toInt()} kg",
                    color = TextSecondary,
                    fontSize = 13.sp
                )

                Spacer(modifier = Modifier.width(10.dp))

                if (isDone) {
                    Text(
                        text = "Done",
                        color = ElectricVolt,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                } else if (isCurrent) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(DarkGreyTile)
                            .border(1.dp, BorderActive, RoundedCornerShape(6.dp))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "Log",
                            color = ElectricVolt,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PecDeckMovementDiagram(
    holdCue: String,
    tempoCue: String
) {
    val infiniteTransition = rememberInfiniteTransition(label = "pec_deck")
    val contraction by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "arm_movement"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(SurfaceSubtle)
            .border(1.dp, BorderHairline, RoundedCornerShape(12.dp))
            .padding(12.dp)
    ) {
        // Top notes
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(ElectricVolt)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = holdCue, color = TextPrimary, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
            }
            Text(text = "Loop Preview", color = TextSecondary, fontSize = 11.sp)
        }

        // Center animated canvas diagram of the biomechanical Pec Deck movement
        Canvas(modifier = Modifier.fillMaxSize().padding(vertical = 24.dp)) {
            val cx = size.width / 2f
            val cy = size.height / 2f

            // Torso / Bench representation
            drawCircle(
                color = Color(0xFF263238),
                radius = 16.dp.toPx(),
                center = Offset(cx, cy)
            )

            // Head representation
            drawCircle(
                color = Color(0xFF1E282D),
                radius = 10.dp.toPx(),
                center = Offset(cx, cy - 26.dp.toPx())
            )

            // Biomechanical arm levers (expanding and contracting with smooth animation)
            val spreadX = (45f - 30f * contraction).dp.toPx()
            val spreadY = (22f - 8f * contraction).dp.toPx()

            // Left arm lever
            drawLine(
                color = ElectricVolt,
                start = Offset(cx, cy),
                end = Offset(cx - spreadX, cy + spreadY),
                strokeWidth = 4.dp.toPx(),
                cap = StrokeCap.Round
            )
            drawCircle(
                color = Color.White,
                radius = 4.dp.toPx(),
                center = Offset(cx - spreadX, cy + spreadY)
            )

            // Right arm lever
            drawLine(
                color = ElectricVolt,
                start = Offset(cx, cy),
                end = Offset(cx + spreadX, cy + spreadY),
                strokeWidth = 4.dp.toPx(),
                cap = StrokeCap.Round
            )
            drawCircle(
                color = Color.White,
                radius = 4.dp.toPx(),
                center = Offset(cx + spreadX, cy + spreadY)
            )

            // Dynamic contraction arc
            drawArc(
                color = ElectricVolt.copy(alpha = 0.35f),
                startAngle = 180f,
                sweepAngle = 180f * contraction,
                useCenter = false,
                topLeft = Offset(cx - 30.dp.toPx(), cy - 10.dp.toPx()),
                size = androidx.compose.ui.geometry.Size(60.dp.toPx(), 40.dp.toPx()),
                style = Stroke(width = 2.dp.toPx())
            )
        }

        // Bottom notes
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(ElectricVolt)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = tempoCue, color = TextSecondary, fontSize = 11.sp)
            }
            Text(text = "Smooth squeeze", color = TextSecondary, fontSize = 11.sp)
        }
    }
}
