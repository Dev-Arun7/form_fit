package com.example.formfit.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.formfit.model.DayPlan
import com.example.formfit.model.DayStatus
import com.example.formfit.model.Exercise
import com.example.formfit.model.ExerciseSet
import com.example.formfit.model.UserProfile

class WorkoutRepository {

    var userProfile by mutableStateOf(UserProfile())
        private set

    val exercises = mutableStateListOf(
        Exercise(
            id = "ex_1",
            name = "Bench Press",
            muscleGroup = "CHEST",
            category = "Barbell Compound",
            formCue = "Drive feet into floor, retract scapula tightly, lower bar to mid-sternum with elbows tucked at 45°.",
            tempoCue = "Controlled tempo 3-1-1",
            holdCue = "Brief pause on chest",
            repsTarget = 10,
            setsTarget = 4,
            loadKg = 60.0,
            equipmentNote = "Olympic Bar",
            sets = listOf(
                ExerciseSet(1, 10, 60.0, isCompleted = true),
                ExerciseSet(2, 10, 60.0, isCompleted = true),
                ExerciseSet(3, 8, 62.5, isCompleted = true),
                ExerciseSet(4, 8, 62.5, isCompleted = true)
            ),
            isCompleted = true
        ),
        Exercise(
            id = "ex_2",
            name = "Dumbbell Press",
            muscleGroup = "CHEST",
            category = "Free Weight Compound",
            formCue = "Keep wrists neutral, converge dumbbells at the top without clinking, full deep stretch at bottom.",
            tempoCue = "Tempo 2-1-1",
            holdCue = "Peak contraction squeeze",
            repsTarget = 12,
            setsTarget = 3,
            loadKg = 24.0,
            equipmentNote = "24 kg DB Pair",
            sets = listOf(
                ExerciseSet(1, 12, 24.0, isCompleted = true),
                ExerciseSet(2, 12, 24.0, isCompleted = true),
                ExerciseSet(3, 10, 24.0, isCompleted = true)
            ),
            isCompleted = true
        ),
        Exercise(
            id = "ex_3",
            name = "Pec Deck Fly",
            muscleGroup = "CHEST",
            category = "Machine Isolation",
            formCue = "Keep shoulders pinned back into the pad. Drive elbows toward the midline, avoiding excessive wrist flexion.",
            tempoCue = "Controlled tempo 2-1-2",
            holdCue = "Hold 1s at peak contraction",
            repsTarget = 12,
            repType = "hypertrophy",
            setsTarget = 3,
            setType = "progressive",
            loadKg = 35.0,
            equipmentNote = "pin #7",
            sets = listOf(
                ExerciseSet(1, 12, 35.0, isCompleted = true),
                ExerciseSet(2, 12, 35.0, isCompleted = true),
                ExerciseSet(3, 12, 35.0, isCompleted = false, isCurrent = true)
            ),
            isCompleted = false
        ),
        Exercise(
            id = "ex_4",
            name = "Incline Press",
            muscleGroup = "CHEST",
            category = "Dumbbell Upper Chest",
            formCue = "Set bench to 30°, maintain proud chest, press vertically over clavicles.",
            tempoCue = "Tempo 3-0-1",
            holdCue = "Top stretch lock",
            repsTarget = 10,
            setsTarget = 3,
            loadKg = 22.0,
            equipmentNote = "22 kg DB Pair",
            sets = listOf(
                ExerciseSet(1, 10, 22.0, isCompleted = false),
                ExerciseSet(2, 10, 22.0, isCompleted = false),
                ExerciseSet(3, 10, 22.0, isCompleted = false)
            ),
            isCompleted = false
        ),
        Exercise(
            id = "ex_5",
            name = "Rope Pushdown",
            muscleGroup = "TRICEPS",
            category = "Cable Isolation",
            formCue = "Keep elbows locked at sides, spread rope ends at the bottom lockout for peak tricep engagement.",
            tempoCue = "Tempo 2-1-2",
            holdCue = "Lockout & flare 1s",
            repsTarget = 15,
            setsTarget = 3,
            loadKg = 20.0,
            equipmentNote = "Cable Stack #4",
            sets = listOf(
                ExerciseSet(1, 15, 20.0, isCompleted = false),
                ExerciseSet(2, 15, 20.0, isCompleted = false),
                ExerciseSet(3, 15, 20.0, isCompleted = false)
            ),
            isCompleted = false
        )
    )

    val weeklySchedule = listOf(
        DayPlan(
            dayName = "MON 24",
            dayOfWeek = "MONDAY",
            dayNumber = 24,
            title = "Chest + Triceps",
            subtitle = "Bench Press, Incline DB, Tricep Dips +2",
            status = DayStatus.COMPLETED,
            exerciseCount = 5,
            durationText = "48m logged"
        ),
        DayPlan(
            dayName = "TUE 25",
            dayOfWeek = "TUESDAY",
            dayNumber = 25,
            title = "Back + Biceps",
            subtitle = "Lat Pulldown, Cable Row, Barbell Curl",
            status = DayStatus.SCHEDULED,
            exerciseCount = 5,
            durationText = "~45–50 min",
            exercisePreview = listOf("1 Lat Pulldown  (4 sets x 10–12 reps)", "2 Cable Row  (3 sets x 12 reps)", "3 Barbell Curl  (3 sets x 10 reps)"),
            isToday = true
        ),
        DayPlan(
            dayName = "WED 26",
            dayOfWeek = "WEDNESDAY",
            dayNumber = 26,
            title = "Legs + Shoulders",
            subtitle = "Squats, Leg Press, Lateral Raise +3 more",
            status = DayStatus.SCHEDULED,
            exerciseCount = 6,
            durationText = "~50–55 min"
        ),
        DayPlan(
            dayName = "THU 27",
            dayOfWeek = "THURSDAY",
            dayNumber = 27,
            title = "Rest & Active Recovery",
            subtitle = "15-Min Light Mobility Protocol • Hamstrings, thoracic spine & hips",
            status = DayStatus.REST_DAY,
            exerciseCount = 0,
            durationText = "Rest Day"
        ),
        DayPlan(
            dayName = "FRI 28",
            dayOfWeek = "FRIDAY",
            dayNumber = 28,
            title = "Chest + Triceps",
            subtitle = "Incline Dumbbell Press, Cable Flyes +3 more",
            status = DayStatus.SCHEDULED,
            exerciseCount = 5,
            durationText = "~45–50 min"
        ),
        DayPlan(
            dayName = "SAT & SUN",
            dayOfWeek = "SAT & SUN",
            dayNumber = 29,
            title = "Rest & Cardio",
            subtitle = "Outdoor walk or 30m light cycling recommended • 10k Steps Goal",
            status = DayStatus.OPTIONAL,
            exerciseCount = 0,
            durationText = "Optional Zone 2"
        )
    )

    var currentExerciseIndex by mutableIntStateOf(2) // Default to Pec Deck Fly (#3) as shown in active player screen
    var workoutSeconds by mutableIntStateOf(1122) // 18:42 default
    var isTimerRunning by mutableStateOf(true)

    fun toggleSetCompletion(exerciseIndex: Int, setIndex: Int) {
        if (exerciseIndex in exercises.indices) {
            val ex = exercises[exerciseIndex]
            if (setIndex in ex.sets.indices) {
                val currentSet = ex.sets[setIndex]
                val updatedSets = ex.sets.toMutableList()
                val nextCompleted = !currentSet.isCompleted
                updatedSets[setIndex] = currentSet.copy(isCompleted = nextCompleted)
                
                // If set was completed, move active pointer to next incomplete set
                if (nextCompleted && setIndex + 1 < updatedSets.size) {
                    updatedSets[setIndex] = updatedSets[setIndex].copy(isCurrent = false)
                    updatedSets[setIndex + 1] = updatedSets[setIndex + 1].copy(isCurrent = true)
                }
                
                val allDone = updatedSets.all { it.isCompleted }
                exercises[exerciseIndex] = ex.copy(sets = updatedSets, isCompleted = allDone)
            }
        }
    }

    fun completeExercise(exerciseIndex: Int) {
        if (exerciseIndex in exercises.indices) {
            val ex = exercises[exerciseIndex]
            val completedSets = ex.sets.map { it.copy(isCompleted = true, isCurrent = false) }
            exercises[exerciseIndex] = ex.copy(sets = completedSets, isCompleted = true)
            if (exerciseIndex + 1 < exercises.size) {
                currentExerciseIndex = exerciseIndex + 1
            }
        }
    }

    fun updateWeight(newWeight: Double) {
        userProfile = userProfile.copy(bodyWeightKg = newWeight)
    }

    fun toggleVibrations(enabled: Boolean) {
        userProfile = userProfile.copy(hapticVibrations = enabled)
    }

    fun completedExerciseCount(): Int {
        return exercises.count { it.isCompleted }
    }
}
