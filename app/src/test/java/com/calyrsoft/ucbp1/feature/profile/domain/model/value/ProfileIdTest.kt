package com.calyrsoft.ucbp1.features.profile.domain.model.value

import org.junit.Assert.*
import org.junit.Test

class ProfileIdTest {

    // ========== CASOS VÁLIDOS ==========
    @Test
    fun `ProfileId alfanumérico con guion bajo debe ser válido`() {
        val id = ProfileId("user_123")
        assertEquals("user_123", id.value)
    }

    @Test
    fun `ProfileId alfanumérico con guion debe ser válido`() {
        val id = ProfileId("user-abc-123")
        assertEquals("user-abc-123", id.value)
    }

    @Test
    fun `ProfileId solo numérico debe ser válido`() {
        val id = ProfileId("12345")
        assertEquals("12345", id.value)
    }

    @Test
    fun `ProfileId solo letras debe ser válido`() {
        val id = ProfileId("usuario")
        assertEquals("usuario", id.value)
    }

    @Test
    fun `ProfileId mixto debe ser válido`() {
        val id = ProfileId("usr_abc-123_xyz")
        assertEquals("usr_abc-123_xyz", id.value)
    }

    @Test
    fun `ProfileId con mayúsculas debe ser válido`() {
        val id = ProfileId("USER_123")
        assertEquals("USER_123", id.value)
    }

    @Test
    fun `ProfileId con mayúsculas y minúsculas debe ser válido`() {
        val id = ProfileId("UsEr_123")
        assertEquals("UsEr_123", id.value)
    }

    @Test
    fun `ProfileId de un carácter debe ser válido`() {
        val id = ProfileId("a")
        assertEquals("a", id.value)
    }

    @Test
    fun `ProfileId largo debe ser válido`() {
        val longId = "user_" + "a".repeat(90)
        val id = ProfileId(longId)
        assertEquals(longId, id.value)
    }

    @Test
    fun `ProfileId estilo Firebase debe ser válido`() {
        val id = ProfileId("abc123def456")
        assertEquals("abc123def456", id.value)
    }

    @Test
    fun `ProfileId que empieza con número debe ser válido`() {
        val id = ProfileId("123user")
        assertEquals("123user", id.value)
    }

    @Test
    fun `ProfileId que empieza con guion debe ser válido`() {
        val id = ProfileId("-user123")
        assertEquals("-user123", id.value)
    }

    @Test
    fun `ProfileId que empieza con guion bajo debe ser válido`() {
        val id = ProfileId("_user123")
        assertEquals("_user123", id.value)
    }

    // ========== CASOS INVÁLIDOS ==========
    @Test(expected = IllegalArgumentException::class)
    fun `ProfileId vacío debe lanzar excepción`() {
        ProfileId("")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `ProfileId con solo espacios debe lanzar excepción`() {
        ProfileId("   ")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `ProfileId con espacios debe lanzar excepción`() {
        ProfileId("user 123")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `ProfileId con punto debe lanzar excepción`() {
        ProfileId("user.123")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `ProfileId con arroba debe lanzar excepción`() {
        ProfileId("user@123")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `ProfileId con slash debe lanzar excepción`() {
        ProfileId("user/123")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `ProfileId con caracteres especiales debe lanzar excepción`() {
        ProfileId("user#123")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `ProfileId de más de 100 caracteres debe lanzar excepción`() {
        ProfileId("a".repeat(101))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `ProfileId con acentos debe lanzar excepción`() {
        ProfileId("usér_123")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `ProfileId con ñ debe lanzar excepción`() {
        ProfileId("español_123")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `ProfileId con paréntesis debe lanzar excepción`() {
        ProfileId("user(123)")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `ProfileId con corchetes debe lanzar excepción`() {
        ProfileId("user[123]")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `ProfileId con símbolos debe lanzar excepción`() {
        ProfileId("user$123")
    }

    // ========== TESTS DE IGUALDAD ==========
    @Test
    fun `dos ProfileIds iguales deben ser iguales`() {
        val id1 = ProfileId("user_123")
        val id2 = ProfileId("user_123")
        assertEquals(id1, id2)
    }

    @Test
    fun `dos ProfileIds diferentes no deben ser iguales`() {
        val id1 = ProfileId("user_123")
        val id2 = ProfileId("user_456")
        assertNotEquals(id1, id2)
    }

    @Test
    fun `ProfileIds con diferente capitalización no deben ser iguales`() {
        val id1 = ProfileId("USER_123")
        val id2 = ProfileId("user_123")
        assertNotEquals(id1, id2)
    }

    @Test
    fun `hashCode debe ser igual para ProfileIds iguales`() {
        val id1 = ProfileId("user_123")
        val id2 = ProfileId("user_123")
        assertEquals(id1.hashCode(), id2.hashCode())
    }

    // ========== EDGE CASES ==========
    @Test
    fun `ProfileId con múltiples guiones debe ser válido`() {
        val id = ProfileId("user---123")
        assertEquals("user---123", id.value)
    }

    @Test
    fun `ProfileId con múltiples guiones bajos debe ser válido`() {
        val id = ProfileId("user___123")
        assertEquals("user___123", id.value)
    }

    @Test
    fun `ProfileId que termina con guion debe ser válido`() {
        val id = ProfileId("user123-")
        assertEquals("user123-", id.value)
    }

    @Test
    fun `ProfileId que termina con guion bajo debe ser válido`() {
        val id = ProfileId("user123_")
        assertEquals("user123_", id.value)
    }

    @Test
    fun `toString debe retornar el valor del id`() {
        val id = ProfileId("user_123")
        assertEquals("user_123", id.toString())
    }

    @Test
    fun `ProfileId en el límite superior debe funcionar`() {
        val id = ProfileId("a".repeat(100))
        assertEquals(100, id.value.length)
    }

    @Test
    fun `mensaje de error debe ser descriptivo para caracteres inválidos`() {
        try {
            ProfileId("user@123")
            fail("Debería lanzar IllegalArgumentException")
        } catch (e: IllegalArgumentException) {
            assertTrue(e.message?.contains("caracteres inválidos") == true)
            assertTrue(e.message?.contains("user@123") == true)
        }
    }

    @Test
    fun `mensaje de error debe ser descriptivo para id vacío`() {
        try {
            ProfileId("")
            fail("Debería lanzar IllegalArgumentException")
        } catch (e: IllegalArgumentException) {
            assertTrue(e.message?.contains("no puede estar vacío") == true)
        }
    }

    @Test
    fun `mensaje de error debe ser descriptivo para longitud inválida`() {
        try {
            ProfileId("a".repeat(101))
            fail("Debería lanzar IllegalArgumentException")
        } catch (e: IllegalArgumentException) {
            assertTrue(e.message?.contains("entre 1 y 100") == true)
        }
    }

    // ========== FORMATOS COMUNES ==========
    @Test
    fun `ProfileId estilo MongoDB debe ser válido`() {
        val id = ProfileId("507f1f77bcf86cd799439011")
        assertNotNull(id)
    }

    @Test
    fun `ProfileId estilo incremental debe ser válido`() {
        val id = ProfileId("000001")
        assertEquals("000001", id.value)
    }

    @Test
    fun `ProfileId con prefijo debe ser válido`() {
        val id = ProfileId("usr_abc123")
        assertEquals("usr_abc123", id.value)
    }
}