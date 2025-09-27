package com.calyrsoft.ucbp1.features.profile.domain.model.value

import org.junit.Assert.*
import org.junit.Test

class ProfileEmailTest {

    // ========== CASOS VÁLIDOS ==========
    @Test
    fun `email válido simple debe crearse correctamente`() {
        val email = ProfileEmail("usuario@ejemplo.com")
        assertEquals("usuario@ejemplo.com", email.value)
    }

    @Test
    fun `email con subdominios debe ser válido`() {
        val email = ProfileEmail("usuario@mail.ejemplo.com")
        assertEquals("usuario@mail.ejemplo.com", email.value)
    }

    @Test
    fun `email con números debe ser válido`() {
        val email = ProfileEmail("usuario123@ejemplo456.com")
        assertEquals("usuario123@ejemplo456.com", email.value)
    }

    @Test
    fun `email con guiones y puntos debe ser válido`() {
        val email = ProfileEmail("usuario.nombre@ejemplo-empresa.com")
        assertEquals("usuario.nombre@ejemplo-empresa.com", email.value)
    }

    @Test
    fun `email con signo más debe ser válido`() {
        val email = ProfileEmail("usuario+etiqueta@ejemplo.com")
        assertEquals("usuario+etiqueta@ejemplo.com", email.value)
    }

    @Test
    fun `email con guion bajo debe ser válido`() {
        val email = ProfileEmail("usuario_nombre@ejemplo.com")
        assertEquals("usuario_nombre@ejemplo.com", email.value)
    }

    // ========== CASOS INVÁLIDOS ==========
    @Test(expected = IllegalArgumentException::class)
    fun `email sin arroba debe lanzar excepción`() {
        ProfileEmail("usuarioejemplo.com")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `email vacío debe lanzar excepción`() {
        ProfileEmail("")
    }

    @Test
    fun `email sin arroba debe tener mensaje de error correcto`() {
        try {
            ProfileEmail("usuarioejemplo.com")
            fail("Debería haber lanzado IllegalArgumentException")
        } catch (e: IllegalArgumentException) {
            assertTrue(e.message?.contains("Email inválido") == true)
            assertTrue(e.message?.contains("usuarioejemplo.com") == true)
        }
    }

    // ========== TESTS DE IGUALDAD ==========
    @Test
    fun `dos emails con el mismo valor deben ser iguales`() {
        val email1 = ProfileEmail("usuario@ejemplo.com")
        val email2 = ProfileEmail("usuario@ejemplo.com")
        assertEquals(email1, email2)
    }

    @Test
    fun `dos emails con diferente valor no deben ser iguales`() {
        val email1 = ProfileEmail("usuario1@ejemplo.com")
        val email2 = ProfileEmail("usuario2@ejemplo.com")
        assertNotEquals(email1, email2)
    }

    @Test
    fun `hashCode debe ser igual para emails iguales`() {
        val email1 = ProfileEmail("usuario@ejemplo.com")
        val email2 = ProfileEmail("usuario@ejemplo.com")
        assertEquals(email1.hashCode(), email2.hashCode())
    }

    // ========== EDGE CASES ==========
    @Test
    fun `email largo debe funcionar`() {
        val emailLargo = "usuario.muy.largo.con.puntos@subdominio.ejemplo.empresa.com"
        val email = ProfileEmail(emailLargo)
        assertEquals(emailLargo, email.value)
    }

    @Test
    fun `toString debe retornar el valor del email`() {
        val email = ProfileEmail("usuario@ejemplo.com")
        assertEquals("usuario@ejemplo.com", email.toString())
    }

    // ========== TESTS QUE MUESTRAN LIMITACIONES DE LA VALIDACIÓN ACTUAL ==========
    // Estos tests PASAN pero demuestran que la validación actual es insuficiente

    @Test
    fun `LIMITACIÓN - email con solo arroba es aceptado`() {
        // Tu validación solo verifica contains("@"), no la estructura
        val email = ProfileEmail("@")
        assertEquals("@", email.value)
        // Este email NO DEBERÍA ser válido, pero tu validación lo acepta
    }

    @Test
    fun `LIMITACIÓN - email sin usuario es aceptado`() {
        val email = ProfileEmail("@ejemplo.com")
        assertEquals("@ejemplo.com", email.value)
        // Este tampoco debería ser válido
    }

    @Test
    fun `LIMITACIÓN - email sin dominio es aceptado`() {
        val email = ProfileEmail("usuario@")
        assertEquals("usuario@", email.value)
        // Este tampoco debería ser válido
    }

    @Test
    fun `LIMITACIÓN - email con múltiples arrobas es aceptado`() {
        val email = ProfileEmail("usuario@@ejemplo.com")
        assertEquals("usuario@@ejemplo.com", email.value)
        // Este tampoco debería ser válido
    }

    @Test
    fun `LIMITACIÓN - email con espacios es aceptado`() {
        val email = ProfileEmail("usuario con espacios@ejemplo.com")
        assertEquals("usuario con espacios@ejemplo.com", email.value)
        // Este tampoco debería ser válido
    }
}