package com.example.formfit.model

data class ExerciseSet(
    val setNumber: Int,
    val targetReps: Int,
    val weightKg: Double,
    val isCompleted: Boolean = false,
    val isCurrent: Boolean = false
)

data class Exercise(
    val id: String,
    val name: String,
    val muscleGroup: String,
    val category: String, // e.g. "Machine Isolation", "Free Weight Compound"
    val formCue: String,
    val tempoCue: String,
    val holdCue: String,
    val repsTarget: Int,
    val repType: String = "hypertrophy",
    val setsTarget: Int,
    val setType: String = "progressive",
    val loadKg: Double,
    val equipmentNote: String = "pin #7",
    val sets: List<ExerciseSet>,
    val isCompleted: Boolean = false
)

data class DayPlan(
    val dayName: String, // "MON 24", "TUE 25"
    val dayOfWeek: String, // "MONDAY", "TUESDAY"
    val dayNumber: Int,
    val title: String,
    val subtitle: String,
    val status: DayStatus,
    val exerciseCount: Int = 5,
    val durationText: String = "~45–50 min",
    val exercisePreview: List<String> = emptyList(),
    val isToday: Boolean = false
)

enum class DayStatus {
    COMPLETED,
    SCHEDULED,
    REST_DAY,
    OPTIONAL
}

data class UserProfile(
    val name: String = "Arun",
    val memberSince: String = "August 2026",
    val isPro: Boolean = true,
    val target: String = "Muscle Gain",
    val bodyWeightKg: Double = 72.0,
    val weightChangeMonth: Double = -1.2,
    val heightCm: Int = 170,
    val totalVolumeLogs: Int = 84,
    val streakDays: Int = 8,
    val bestStreakDays: Int = 14,
    val coachName: String = "Marcus (Elite Strength)",
    val routineName: String = "4-Day Push/Pull/Legs Split",
    val hapticVibrations: Boolean = true
)
