package com.calyrsoft.ucbp1.features.profile.domain.model.value

@JvmInline
value class ProfileEmail(val value: String) {
    init {
        require(isValid(value)) { "Email inválido. Revisar: $value" }
    }

    companion object {
        private val EMAIL_REGEX =
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$".toRegex()

        private fun isValid(email: String): Boolean {
            return email.isNotBlank() &&
                    email.length <= 320 && // RFC 5321
                    EMAIL_REGEX.matches(email) &&
                    email.count { it == '@' } == 1
        }
    }

    fun domain(): String = value.substringAfter("@")
    fun username(): String = value.substringBefore("@")
}