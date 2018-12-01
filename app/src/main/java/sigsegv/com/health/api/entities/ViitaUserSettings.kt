package sigsegv.com.health.api.entities

data class ViitaUserSettings(
    val firstName: String,
    val lastName: String,
    val dateOfBirth: String,
    val weight: Int,
    val height: Int,
    val gender: String,
    val userMission: String,
    val sleepGoalStart: String,
    val sleepGoalEnd: String,
    val stepsGoal: Int,
    val caloriesGoal: Int
)
