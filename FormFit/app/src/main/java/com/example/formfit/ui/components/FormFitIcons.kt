package com.example.formfit.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class FormFitIconType {
    HOME,
    WORKOUTS,
    HISTORY,
    PROFILE,
    ARROW_BACK,
    ARROW_FORWARD,
    CHECK,
    INFO,
    CLOSE,
    SETTINGS,
    EDIT,
    PLAY
}

@Composable
fun FormFitIcon(
    icon: FormFitIconType,
    tint: Color,
    modifier: Modifier = Modifier,
    size: Dp = 24.dp
) {
    Canvas(modifier = modifier.size(size)) {
        val w = this.size.width
        val h = this.size.height
        val stroke = 2.dp.toPx()

        when (icon) {
            FormFitIconType.HOME -> {
                val path = Path().apply {
                    moveTo(w * 0.15f, h * 0.45f)
                    lineTo(w * 0.5f, h * 0.15f)
                    lineTo(w * 0.85f, h * 0.45f)
                    lineTo(w * 0.85f, h * 0.85f)
                    lineTo(w * 0.6f, h * 0.85f)
                    lineTo(w * 0.6f, h * 0.55f)
                    lineTo(w * 0.4f, h * 0.55f)
                    lineTo(w * 0.4f, h * 0.85f)
                    lineTo(w * 0.15f, h * 0.85f)
                    close()
                }
                drawPath(path, color = tint, style = Stroke(width = stroke, cap = StrokeCap.Round, join = StrokeJoin.Round))
            }
            FormFitIconType.WORKOUTS -> {
                // Dumbbell icon
                drawLine(tint, Offset(w * 0.25f, h * 0.5f), Offset(w * 0.75f, h * 0.5f), strokeWidth = stroke * 1.5f, cap = StrokeCap.Round)
                drawLine(tint, Offset(w * 0.25f, h * 0.3f), Offset(w * 0.25f, h * 0.7f), strokeWidth = stroke * 1.8f, cap = StrokeCap.Round)
                drawLine(tint, Offset(w * 0.15f, h * 0.35f), Offset(w * 0.15f, h * 0.65f), strokeWidth = stroke * 1.5f, cap = StrokeCap.Round)
                drawLine(tint, Offset(w * 0.75f, h * 0.3f), Offset(w * 0.75f, h * 0.7f), strokeWidth = stroke * 1.8f, cap = StrokeCap.Round)
                drawLine(tint, Offset(w * 0.85f, h * 0.35f), Offset(w * 0.85f, h * 0.65f), strokeWidth = stroke * 1.5f, cap = StrokeCap.Round)
            }
            FormFitIconType.HISTORY -> {
                // Calendar icon
                drawRoundRect(
                    color = tint,
                    topLeft = Offset(w * 0.15f, h * 0.25f),
                    size = Size(w * 0.7f, h * 0.65f),
                    cornerRadius = CornerRadius(4.dp.toPx()),
                    style = Stroke(width = stroke)
                )
                drawLine(tint, Offset(w * 0.15f, h * 0.45f), Offset(w * 0.85f, h * 0.45f), strokeWidth = stroke)
                drawLine(tint, Offset(w * 0.35f, h * 0.12f), Offset(w * 0.35f, h * 0.25f), strokeWidth = stroke * 1.2f, cap = StrokeCap.Round)
                drawLine(tint, Offset(w * 0.65f, h * 0.12f), Offset(w * 0.65f, h * 0.25f), strokeWidth = stroke * 1.2f, cap = StrokeCap.Round)
            }
            FormFitIconType.PROFILE -> {
                // Person icon
                drawCircle(color = tint, radius = w * 0.2f, center = Offset(w * 0.5f, h * 0.32f), style = Stroke(width = stroke))
                val path = Path().apply {
                    moveTo(w * 0.2f, h * 0.85f)
                    quadraticTo(w * 0.2f, h * 0.62f, w * 0.5f, h * 0.62f)
                    quadraticTo(w * 0.8f, h * 0.62f, w * 0.8f, h * 0.85f)
                }
                drawPath(path, color = tint, style = Stroke(width = stroke, cap = StrokeCap.Round))
            }
            FormFitIconType.ARROW_BACK -> {
                drawLine(tint, Offset(w * 0.75f, h * 0.5f), Offset(w * 0.25f, h * 0.5f), strokeWidth = stroke, cap = StrokeCap.Round)
                drawLine(tint, Offset(w * 0.45f, h * 0.25f), Offset(w * 0.25f, h * 0.5f), strokeWidth = stroke, cap = StrokeCap.Round)
                drawLine(tint, Offset(w * 0.45f, h * 0.75f), Offset(w * 0.25f, h * 0.5f), strokeWidth = stroke, cap = StrokeCap.Round)
            }
            FormFitIconType.ARROW_FORWARD -> {
                drawLine(tint, Offset(w * 0.25f, h * 0.5f), Offset(w * 0.75f, h * 0.5f), strokeWidth = stroke, cap = StrokeCap.Round)
                drawLine(tint, Offset(w * 0.55f, h * 0.25f), Offset(w * 0.75f, h * 0.5f), strokeWidth = stroke, cap = StrokeCap.Round)
                drawLine(tint, Offset(w * 0.55f, h * 0.75f), Offset(w * 0.75f, h * 0.5f), strokeWidth = stroke, cap = StrokeCap.Round)
            }
            FormFitIconType.CHECK -> {
                val path = Path().apply {
                    moveTo(w * 0.2f, h * 0.5f)
                    lineTo(w * 0.42f, h * 0.75f)
                    lineTo(w * 0.82f, h * 0.25f)
                }
                drawPath(path, color = tint, style = Stroke(width = stroke * 1.2f, cap = StrokeCap.Round, join = StrokeJoin.Round))
            }
            FormFitIconType.INFO -> {
                drawCircle(color = tint, radius = w * 0.4f, center = Offset(w * 0.5f, h * 0.5f), style = Stroke(width = stroke))
                drawCircle(color = tint, radius = stroke * 0.8f, center = Offset(w * 0.5f, h * 0.32f), style = Fill)
                drawLine(tint, Offset(w * 0.5f, h * 0.45f), Offset(w * 0.5f, h * 0.72f), strokeWidth = stroke, cap = StrokeCap.Round)
            }
            FormFitIconType.CLOSE -> {
                drawLine(tint, Offset(w * 0.28f, h * 0.28f), Offset(w * 0.72f, h * 0.72f), strokeWidth = stroke, cap = StrokeCap.Round)
                drawLine(tint, Offset(w * 0.72f, h * 0.28f), Offset(w * 0.28f, h * 0.72f), strokeWidth = stroke, cap = StrokeCap.Round)
            }
            FormFitIconType.SETTINGS -> {
                drawCircle(color = tint, radius = w * 0.22f, center = Offset(w * 0.5f, h * 0.5f), style = Stroke(width = stroke))
                for (i in 0 until 6) {
                    val angle = (i * 60f) * (Math.PI / 180f).toFloat()
                    val r1 = w * 0.35f
                    val r2 = w * 0.44f
                    val cx = w * 0.5f
                    val cy = h * 0.5f
                    drawLine(
                        tint,
                        Offset(cx + kotlin.math.cos(angle) * r1, cy + kotlin.math.sin(angle) * r1),
                        Offset(cx + kotlin.math.cos(angle) * r2, cy + kotlin.math.sin(angle) * r2),
                        strokeWidth = stroke * 1.5f,
                        cap = StrokeCap.Round
                    )
                }
            }
            FormFitIconType.EDIT -> {
                val path = Path().apply {
                    moveTo(w * 0.2f, h * 0.8f)
                    lineTo(w * 0.35f, h * 0.78f)
                    lineTo(w * 0.78f, h * 0.35f)
                    lineTo(w * 0.65f, h * 0.22f)
                    lineTo(w * 0.22f, h * 0.65f)
                    close()
                }
                drawPath(path, color = tint, style = Stroke(width = stroke, cap = StrokeCap.Round, join = StrokeJoin.Round))
                drawLine(tint, Offset(w * 0.15f, h * 0.85f), Offset(w * 0.85f, h * 0.85f), strokeWidth = stroke, cap = StrokeCap.Round)
            }
            FormFitIconType.PLAY -> {
                val path = Path().apply {
                    moveTo(w * 0.3f, h * 0.2f)
                    lineTo(w * 0.78f, h * 0.5f)
                    lineTo(w * 0.3f, h * 0.8f)
                    close()
                }
                drawPath(path, color = tint, style = Fill)
            }
        }
    }
}
