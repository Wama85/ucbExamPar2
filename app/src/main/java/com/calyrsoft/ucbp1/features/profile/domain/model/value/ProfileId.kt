package com.calyrsoft.ucbp1.features.profile.domain.model.value

@JvmInline
value class ProfileId(val value: String) {
    init {
        require(value.isNotBlank()) { "ProfileId no puede estar vacío" }
        require(value.length in 1..100) { "ProfileId debe tener entre 1 y 100 caracteres" }
        require(value.matches(ID_REGEX)) {
            "ProfileId contiene caracteres inválidos: $value. Solo se permiten letras, números, guiones y guiones bajos"
        }
    }

    companion object {
        // Permite: user_123, usr-abc-123, 12345, etc.
        private val ID_REGEX = "^[a-zA-Z0-9_-]+$".toRegex()
    }
}