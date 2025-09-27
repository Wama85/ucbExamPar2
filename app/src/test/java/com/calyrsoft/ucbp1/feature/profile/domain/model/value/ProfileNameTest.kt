package com.calyrsoft.ucbp1.features.profile.domain.model.value

import org.junit.Assert.*
import org.junit.Test

class ProfileNameTest {

    // ========== CASOS VÁLIDOS ==========
    @Test
    fun `nombre simple debe ser válido`() {
        val name = ProfileName("Juan Pérez")
        assertEquals("Juan Pérez", name.value)
    }

    @Test
    fun `nombre con un solo término debe ser válido`() {
        val name = ProfileName("Madonna")
        assertEquals("Madonna", name.value)
    }

    @Test
    fun `nombre con tres términos debe ser válido`() {
        val name = ProfileName("Juan Carlos Pérez")
        assertEquals("Juan Carlos Pérez", name.value)
    }

    @Test
    fun `nombre con acentos debe ser válido`() {
        val name = ProfileName("José María García")
        assertEquals("José María García", name.value)
    }

    @Test
    fun `nombre con ñ debe ser válido`() {
        val name = ProfileName("Peña Nieto")
        assertEquals("Peña Nieto", name.value)
    }

    @Test
    fun `nombre de dos caracteres debe ser válido`() {
        val name = ProfileName("Li")
        assertEquals("Li", name.value)
    }

    @Test
    fun `nombre de 100 caracteres debe ser válido`() {
        val longName = "a".repeat(100)
        val name = ProfileName(longName)
        assertEquals(100, name.value.length)
    }

    // ========== CASOS INVÁLIDOS ==========
    @Test(expected = IllegalArgumentException::class)
    fun `nombre vacío debe lanzar excepción`() {
        ProfileName("")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `nombre con solo espacios debe lanzar excepción`() {
        ProfileName("   ")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `nombre de un solo carácter debe lanzar excepción`() {
        ProfileName("J")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `nombre de más de 100 caracteres debe lanzar excepción`() {
        ProfileName("a".repeat(101))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `nombre con espacios al inicio debe lanzar excepción`() {
        ProfileName("  Juan Pérez")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `nombre con espacios al final debe lanzar excepción`() {
        ProfileName("Juan Pérez  ")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `nombre con espacios al inicio y final debe lanzar excepción`() {
        ProfileName("  Juan Pérez  ")
    }

    // ========== MÉTODOS AUXILIARES ==========
    @Test
    fun `initials de nombre completo debe retornar dos iniciales`() {
        val name = ProfileName("Juan Pérez")
        assertEquals("JP", name.initials())
    }

    @Test
    fun `initials de nombre simple debe retornar una inicial`() {
        val name = ProfileName("Madonna")
        assertEquals("M", name.initials())
    }

    @Test
    fun `initials de nombre con tres términos debe retornar dos iniciales`() {
        val name = ProfileName("Juan Carlos Pérez")
        assertEquals("JC", name.initials())
    }

    @Test
    fun `initials debe estar en mayúsculas`() {
        val name = ProfileName("juan pérez")
        assertEquals("JP", name.initials())
    }

    @Test
    fun `firstName debe retornar el primer nombre`() {
        val name = ProfileName("Juan Pérez")
        assertEquals("Juan", name.firstName())
    }

    @Test
    fun `firstName de nombre simple debe retornar el nombre completo`() {
        val name = ProfileName("Madonna")
        assertEquals("Madonna", name.firstName())
    }

    @Test
    fun `lastName debe retornar el apellido`() {
        val name = ProfileName("Juan Pérez")
        assertEquals("Pérez", name.lastName())
    }

    @Test
    fun `lastName de nombre compuesto debe retornar apellidos completos`() {
        val name = ProfileName("Juan Carlos Pérez García")
        assertEquals("Carlos Pérez García", name.lastName())
    }

    @Test
    fun `lastName de nombre simple debe retornar null`() {
        val name = ProfileName("Madonna")
        assertNull(name.lastName())
    }

    @Test
    fun `initials con espacios múltiples debe funcionar`() {
        val name = ProfileName("Juan  Pérez")
        assertEquals("JP", name.initials())
    }

    // ========== TESTS DE IGUALDAD ==========
    @Test
    fun `dos nombres iguales deben ser iguales`() {
        val name1 = ProfileName("Juan Pérez")
        val name2 = ProfileName("Juan Pérez")
        assertEquals(name1, name2)
    }

    @Test
    fun `dos nombres diferentes no deben ser iguales`() {
        val name1 = ProfileName("Juan Pérez")
        val name2 = ProfileName("María García")
        assertNotEquals(name1, name2)
    }

    @Test
    fun `hashCode debe ser igual para nombres iguales`() {
        val name1 = ProfileName("Juan Pérez")
        val name2 = ProfileName("Juan Pérez")
        assertEquals(name1.hashCode(), name2.hashCode())
    }

    // ========== EDGE CASES ==========
    @Test
    fun `nombre con caracteres especiales válidos debe funcionar`() {
        val name = ProfileName("O'Connor")
        assertEquals("O'Connor", name.value)
    }

    @Test
    fun `toString debe retornar el valor del nombre`() {
        val name = ProfileName("Juan Pérez")
        assertEquals("Juan Pérez", name.toString())
    }

    @Test
    fun `mensaje de error debe ser descriptivo para nombre muy corto`() {
        try {
            ProfileName("J")
            fail("Debería lanzar IllegalArgumentException")
        } catch (e: IllegalArgumentException) {
            assertTrue(e.message?.contains("entre 2 y 100") == true)
        }
    }

    @Test
    fun `mensaje de error debe ser descriptivo para nombre muy largo`() {
        try {
            ProfileName("a".repeat(101))
            fail("Debería lanzar IllegalArgumentException")
        } catch (e: IllegalArgumentException) {
            assertTrue(e.message?.contains("entre 2 y 100") == true)
        }
    }
}
