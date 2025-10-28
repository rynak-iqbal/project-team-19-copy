package seg3x02.pharmacysystem.domain.agent.valueobjects

import java.security.MessageDigest
import java.time.LocalDateTime
import java.util.Base64

data class Credentials(
    val username: String,
    val passwordHash: String,
    val password: String,
    val temporaryPassword: Boolean,
    val lastPasswordChange: LocalDateTime,
    val failedLoginAttempts: Int
) {
    fun validatePassword(password: String): Boolean {
        return hashPassword(password) == passwordHash
    }

    fun requiresPasswordChange(): Boolean {
        return temporaryPassword
    }

    companion object {
        fun hashPassword(password: String): String {
            val digest = MessageDigest.getInstance("SHA-256")
            val hashBytes = digest.digest(password.toByteArray(Charsets.UTF_8))
            return Base64.getEncoder().encodeToString(hashBytes)
        }
    }
}
