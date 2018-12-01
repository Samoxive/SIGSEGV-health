package sigsegv.com.health.api.entities

import java.util.*

data class UserSettings(
    val firstName: String,
    val lastName: String,
    val dateOfBirth: Date,
    val weight: Int,
    val height: Int,
    val gender: String,
    val userMission: String,
    val sleepGoalStart: ViitaTime,
    val sleepGoalEnd: ViitaTime,
    val stepsGoal: Int,
    val caloriesGoal: Int
)

fun ViitaUserSettings.toUserSettings() = UserSettings(
    this.firstName,
    this.lastName,
    this.dateOfBirth.toDate(),
    this.weight,
    this.height,
    this.gender,
    this.userMission,
    this.sleepGoalStart.toViitaTime(),
    this.sleepGoalEnd.toViitaTime(),
    this.stepsGoal,
    this.caloriesGoal
)