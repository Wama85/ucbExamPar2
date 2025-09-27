package com.calyrsoft.ucbp1.features.profile.domain.model.value

@JvmInline
value class ProfileAvatarUrl(val value: String?) {

    init {
        value?.let { url ->
            require(isValidUrl(url)) { "URL de avatar inválida: $url" }
        }
    }

    fun orDefault(): String = value ?: DEFAULT_AVATAR_URL

    fun isDefault(): Boolean = value == null || value == DEFAULT_AVATAR_URL

    fun isDrawableResource(): Boolean = value?.startsWith("drawable/") == true

    fun isHttpUrl(): Boolean = value?.let {
        it.startsWith("http://") || it.startsWith("https://")
    } ?: false

    companion object {
        const val DEFAULT_AVATAR_URL = "https://example.com/default-avatar.png"

        private fun isValidUrl(url: String): Boolean {
            if (url.isBlank()) return false

            // Acepta URLs HTTP/HTTPS
            if (url.startsWith("http://") || url.startsWith("https://")) {
                return url.length <= 2048
            }

            // Acepta referencias a drawables (drawable/nombre_recurso)
            if (url.startsWith("drawable/")) {
                return url.length in 9..200 // "drawable/" tiene 9 caracteres
            }

            // Acepta URIs de Android (content://, file://, android.resource://)
            if (url.startsWith("content://") ||
                url.startsWith("file://") ||
                url.startsWith("android.resource://")) {
                return true
            }

            return false
        }
    }
}