package com.ipi.jva350.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EntrepriseTest {

    @Test
    void estDansPlage_dateDansLaPlage() {
        // Given
        LocalDate d = LocalDate.of(2024, 6, 15);
        LocalDate debut = LocalDate.of(2024, 6, 1);
        LocalDate fin = LocalDate.of(2024, 6, 30);

        // When
        boolean result = Entreprise.estDansPlage(d, debut, fin);

        // Then
        assertTrue(result);
    }

    @Test
    void estDansPlage_dateEgaleAuDebut() {
        // Given
        LocalDate d = LocalDate.of(2024, 6, 1);
        LocalDate debut = LocalDate.of(2024, 6, 1);
        LocalDate fin = LocalDate.of(2024, 6, 30);

        // When
        boolean result = Entreprise.estDansPlage(d, debut, fin);

        // Then
        assertTrue(result);
    }

    @Test
    void estDansPlage_dateEgaleALaFin() {
        // Given
        LocalDate d = LocalDate.of(2024, 6, 30);
        LocalDate debut = LocalDate.of(2024, 6, 1);
        LocalDate fin = LocalDate.of(2024, 6, 30);


        // When
        boolean result = Entreprise.estDansPlage(d, debut, fin);

        // Then
        assertTrue(result);
    }

    @Test
    void estDansPlage_dateAvantLaPlage() {
        // Given
        LocalDate d = LocalDate.of(2024, 5, 31);
        LocalDate debut = LocalDate.of(2024, 6, 1);
        LocalDate fin = LocalDate.of(2024, 6, 30);

        // When
        boolean result = Entreprise.estDansPlage(d, debut, fin);

        // Then
        assertFalse(result);
    }

    @Test
    void estDansPlage_dateApresLaPlage() {
        // Given
        LocalDate d = LocalDate.of(2024, 7, 1);
        LocalDate debut = LocalDate.of(2024, 6, 1);
        LocalDate fin = LocalDate.of(2024, 6, 30);

        // When
        boolean result = Entreprise.estDansPlage(d, debut, fin);

        // Then
        assertFalse(result);
    }

    @Test
    void estDansPlage_plageUnSeulJour() {
        // Given
        LocalDate d = LocalDate.of(2024, 6, 15);

        // When
        boolean result = Entreprise.estDansPlage(d, d, d);

        // Then
        assertTrue(result);
    }

    @Test
    void estDansPlage_plageUnSeulJour_dateDifferente() {
        // Given
        LocalDate d = LocalDate.of(2024, 6, 16);
        LocalDate debut = LocalDate.of(2024, 6, 15);
        LocalDate fin = LocalDate.of(2024, 6, 15);

        // When
        boolean result = Entreprise.estDansPlage(d, debut, fin);

        // Then
        assertFalse(result);
    }

    // Fais avec LLM
    @ParameterizedTest(name = "estJourFerie({0}) doit retourner {1}")
    @CsvSource({
            "2024-01-01, true",   // Jour de l'an
            "2024-04-01, true",   // Lundi de Paques
            "2024-05-01, true",   // Fete du Travail
            "2024-05-08, true",   // Victoire 1945
            "2024-05-10, true",   // Ascension
            "2024-05-20, true",   // Lundi de Pentecote
            "2024-07-14, true",   // Fete nationale
            "2024-08-15, true",   // Assomption
            "2024-11-01, true",   // Toussaint
            "2024-11-11, true",   // Armistice
            "2024-12-25, true",   // Noel
            "2024-01-02, false",
            "2024-03-15, false",
            "2024-06-01, false",
            "2024-07-15, false",
            "2024-12-26, false",
            "2025-01-01, true",
            "2025-04-21, true",   // Lundi de Paques 2025
            "2025-05-01, true",
            "2025-12-25, true",
            "2023-01-01, true",
            "2023-02-15, false"
    })
    // FIN LLM

    void testEstJourFerie(String dateStr, boolean expected) {
        // Given
        LocalDate jour = LocalDate.parse(dateStr);

        // When
        boolean result = Entreprise.estJourFerie(jour);

        // Then
        assertEquals(expected, result);
    }
}
