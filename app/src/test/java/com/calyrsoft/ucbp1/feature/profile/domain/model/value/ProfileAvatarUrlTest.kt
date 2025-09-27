package com.calyrsoft.ucbp1.features.profile.domain.model.value

import org.junit.Assert.*
import org.junit.Test

class ProfileAvatarUrlTest {

    // ========== CASOS VÁLIDOS - NULL ==========
    @Test
    fun `ProfileAvatarUrl null debe usar valor por defecto`() {
        val avatar = ProfileAvatarUrl(null)
        assertEquals("https://example.com/default-avatar.png", avatar.orDefault())
    }

    @Test
    fun `ProfileAvatarUrl null debe tener value null`() {
        val avatar = ProfileAvatarUrl(null)
        assertNull(avatar.value)
    }

    @Test
    fun `ProfileAvatarUrl null debe ser default`() {
        val avatar = ProfileAvatarUrl(null)
        assertTrue(avatar.isDefault())
    }

    // ========== CASOS VÁLIDOS - URLS HTTP/HTTPS ==========
    @Test
    fun `ProfileAvatarUrl con URL HTTPS válida debe almacenar el valor`() {
        val url = "https://cdn.example.com/avatar.jpg"
        val avatar = ProfileAvatarUrl(url)
        assertEquals(url, avatar.value)
    }

    @Test
    fun `ProfileAvatarUrl con URL HTTP válida debe almacenar el valor`() {
        val url = "http://example.com/avatar.png"
        val avatar = ProfileAvatarUrl(url)
        assertEquals(url, avatar.value)
    }

    @Test
    fun `ProfileAvatarUrl con URL válida debe retornar esa URL en orDefault`() {
        val url = "https://cdn.example.com/avatar.jpg"
        val avatar = ProfileAvatarUrl(url)
        assertEquals(url, avatar.orDefault())
    }

    @Test
    fun `ProfileAvatarUrl con URL personalizada no debe ser default`() {
        val avatar = ProfileAvatarUrl("https://cdn.example.com/custom.jpg")
        assertFalse(avatar.isDefault())
    }

    @Test
    fun `URL con parámetros debe ser válida`() {
        val url = "https://cdn.example.com/avatar.jpg?size=200&format=webp"
        val avatar = ProfileAvatarUrl(url)
        assertEquals(url, avatar.value)
    }

    @Test
    fun `URL con subdominios debe ser válida`() {
        val url = "https://cdn.storage.example.com/avatars/user123.png"
        val avatar = ProfileAvatarUrl(url)
        assertEquals(url, avatar.value)
    }

    @Test
    fun `isHttpUrl debe retornar true para URLs HTTPS`() {
        val avatar = ProfileAvatarUrl("https://example.com/avatar.jpg")
        assertTrue(avatar.isHttpUrl())
    }

    @Test
    fun `isHttpUrl debe retornar true para URLs HTTP`() {
        val avatar = ProfileAvatarUrl("http://example.com/avatar.jpg")
        assertTrue(avatar.isHttpUrl())
    }

    // ========== CASOS VÁLIDOS - DRAWABLE RESOURCES ==========
    @Test
    fun `ProfileAvatarUrl con drawable debe ser válido`() {
        val url = "drawable/profile"
        val avatar = ProfileAvatarUrl(url)
        assertEquals(url, avatar.value)
    }

    @Test
    fun `drawable con nombre largo debe ser válido`() {
        val url = "drawable/ic_profile_avatar_default"
        val avatar = ProfileAvatarUrl(url)
        assertEquals(url, avatar.value)
    }

    @Test
    fun `isDrawableResource debe retornar true para drawables`() {
        val avatar = ProfileAvatarUrl("drawable/profile")
        assertTrue(avatar.isDrawableResource())
    }

    @Test
    fun `isDrawableResource debe retornar false para URLs HTTP`() {
        val avatar = ProfileAvatarUrl("https://example.com/avatar.jpg")
        assertFalse(avatar.isDrawableResource())
    }

    @Test
    fun `isDrawableResource debe retornar false para null`() {
        val avatar = ProfileAvatarUrl(null)
        assertFalse(avatar.isDrawableResource())
    }

    @Test
    fun `isHttpUrl debe retornar false para drawables`() {
        val avatar = ProfileAvatarUrl("drawable/profile")
        assertFalse(avatar.isHttpUrl())
    }

    // ========== CASOS VÁLIDOS - ANDROID URIs ==========
    @Test
    fun `content URI debe ser válido`() {
        val uri = "content://com.android.providers.media.documents/document/image:12345"
        val avatar = ProfileAvatarUrl(uri)
        assertEquals(uri, avatar.value)
    }

    @Test
    fun `file URI debe ser válido`() {
        val uri = "file:///storage/emulated/0/Pictures/avatar.jpg"
        val avatar = ProfileAvatarUrl(uri)
        assertEquals(uri, avatar.value)
    }

    @Test
    fun `android resource URI debe ser válido`() {
        val uri = "android.resource://com.example.app/drawable/avatar"
        val avatar = ProfileAvatarUrl(uri)
        assertEquals(uri, avatar.value)
    }

    // ========== CASOS INVÁLIDOS ==========
    @Test(expected = IllegalArgumentException::class)
    fun `URL sin protocolo debe lanzar excepción`() {
        ProfileAvatarUrl("example.com/avatar.png")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `URL vacía debe lanzar excepción`() {
        ProfileAvatarUrl("")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `URL con solo espacios debe lanzar excepción`() {
        ProfileAvatarUrl("   ")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `drawable sin slash debe lanzar excepción`() {
        ProfileAvatarUrl("drawable")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `ruta relativa inválida debe lanzar excepción`() {
        ProfileAvatarUrl("images/avatar.png")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `protocolo inválido debe lanzar excepción`() {
        ProfileAvatarUrl("ftp://example.com/avatar.png")
    }

    // ========== TESTS DE IGUALDAD ==========
    @Test
    fun `dos URLs iguales deben ser iguales`() {
        val avatar1 = ProfileAvatarUrl("https://example.com/avatar.png")
        val avatar2 = ProfileAvatarUrl("https://example.com/avatar.png")
        assertEquals(avatar1, avatar2)
    }

    @Test
    fun `dos URLs diferentes no deben ser iguales`() {
        val avatar1 = ProfileAvatarUrl("https://example.com/avatar1.png")
        val avatar2 = ProfileAvatarUrl("https://example.com/avatar2.png")
        assertNotEquals(avatar1, avatar2)
    }

    @Test
    fun `null y URL no deben ser iguales`() {
        val avatar1 = ProfileAvatarUrl(null)
        val avatar2 = ProfileAvatarUrl("https://example.com/avatar.png")
        assertNotEquals(avatar1, avatar2)
    }

    @Test
    fun `dos nulls deben ser iguales`() {
        val avatar1 = ProfileAvatarUrl(null)
        val avatar2 = ProfileAvatarUrl(null)
        assertEquals(avatar1, avatar2)
    }

    @Test
    fun `drawable y URL no deben ser iguales`() {
        val avatar1 = ProfileAvatarUrl("drawable/profile")
        val avatar2 = ProfileAvatarUrl("https://example.com/avatar.png")
        assertNotEquals(avatar1, avatar2)
    }

    @Test
    fun `dos drawables iguales deben ser iguales`() {
        val avatar1 = ProfileAvatarUrl("drawable/profile")
        val avatar2 = ProfileAvatarUrl("drawable/profile")
        assertEquals(avatar1, avatar2)
    }

    @Test
    fun `hashCode debe ser igual para avatars iguales`() {
        val avatar1 = ProfileAvatarUrl("drawable/profile")
        val avatar2 = ProfileAvatarUrl("drawable/profile")
        assertEquals(avatar1.hashCode(), avatar2.hashCode())
    }

    // ========== EDGE CASES ==========
    @Test
    fun `URL muy larga debe ser válida si no excede 2048 caracteres`() {
        val longUrl = "https://example.com/" + "a".repeat(2020) + ".jpg"
        val avatar = ProfileAvatarUrl(longUrl)
        assertNotNull(avatar)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `URL de más de 2048 caracteres debe lanzar excepción`() {
        val longUrl = "https://example.com/" + "a".repeat(2050) + ".jpg"
        ProfileAvatarUrl(longUrl)
    }

    @Test
    fun `drawable en el límite inferior debe funcionar`() {
        val avatar = ProfileAvatarUrl("drawable/a")
        assertEquals("drawable/a", avatar.value)
    }

    @Test
    fun `toString debe funcionar correctamente`() {
        val avatar = ProfileAvatarUrl("drawable/profile")
        assertNotNull(avatar.toString())
    }

    @Test
    fun `mensaje de error debe incluir la URL inválida`() {
        try {
            ProfileAvatarUrl("invalid")
            fail("Debería lanzar IllegalArgumentException")
        } catch (e: IllegalArgumentException) {
            assertTrue(e.message?.contains("invalid") == true)
            assertTrue(e.message?.contains("URL de avatar inválida") == true)
        }
    }

    // ========== CASOS REALES DE USO ==========
    @Test
    fun `URL de CDN debe funcionar`() {
        val avatar = ProfileAvatarUrl("https://cdn.example.com/users/123/avatar.webp")
        assertTrue(avatar.isHttpUrl())
        assertFalse(avatar.isDrawableResource())
    }

    @Test
    fun `drawable de Material Design debe funcionar`() {
        val avatar = ProfileAvatarUrl("drawable/ic_account_circle")
        assertTrue(avatar.isDrawableResource())
        assertFalse(avatar.isHttpUrl())
    }

    @Test
    fun `content URI de galería debe funcionar`() {
        val uri = "content://media/external/images/media/12345"
        val avatar = ProfileAvatarUrl(uri)
        assertFalse(avatar.isHttpUrl())
        assertFalse(avatar.isDrawableResource())
    }
}