package com.calyrsoft.ucbp1.features.profile.domain.model.value

@JvmInline
value class ProfileName(val value: String) {
    init {
        require(value.isNotBlank()) { "El nombre no puede estar vacío" }
        require(value.length in 2..100) { "El nombre debe tener entre 2 y 100 caracteres" }
        require(value.trim() == value) { "El nombre no debe tener espacios al inicio o final" }
    }

    fun initials(): String {
        return value.split(" ")
            .filter { it.isNotBlank() }
            .take(2)
            .joinToString("") { it.first().uppercase() }
    }

    fun firstName(): String = value.split(" ").first()
    fun lastName(): String? = value.split(" ").drop(1).joinToString(" ").takeIf { it.isNotBlank() }
}