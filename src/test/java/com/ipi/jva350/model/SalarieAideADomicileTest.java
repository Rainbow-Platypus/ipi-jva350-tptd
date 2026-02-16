package com.ipi.jva350.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.*;

class SalarieAideADomicileTest {

    @Test
    void testALegalementDroitADesCongesPayes_plusDe10Jours() {
        // Given
        SalarieAideADomicile salarie = new SalarieAideADomicile(
                "Dupont", LocalDate.of(2020, 1, 1), LocalDate.of(2024, 11, 1),
                80, 20, 100, 25, 5);

        // When
        boolean result = salarie.aLegalementDroitADesCongesPayes();

        // Then
        assertTrue(result);
    }

    @Test
    void testALegalementDroitADesCongesPayes_exactement10Jours() {
        // Given
        SalarieAideADomicile salarie = new SalarieAideADomicile(
                "Martin", LocalDate.of(2020, 1, 1), LocalDate.of(2024, 11, 1),
                80, 20, 10, 25, 5);

        // When
        boolean result = salarie.aLegalementDroitADesCongesPayes();

        // Then
        assertFalse(result);
    }

    @Test
    void testALegalementDroitADesCongesPayes_moinsde10Jours() {
        // Given
        SalarieAideADomicile salarie = new SalarieAideADomicile(
                "Durand", LocalDate.of(2020, 1, 1), LocalDate.of(2024, 11, 1),
                80, 20, 5, 25, 5);

        // When
        boolean result = salarie.aLegalementDroitADesCongesPayes();

        // Then
        assertFalse(result);
    }

    @Test
    void testALegalementDroitADesCongesPayes_zeroJour() {
        // Given
        SalarieAideADomicile salarie = new SalarieAideADomicile(
                "Nouveau", LocalDate.of(2024, 9, 1), LocalDate.of(2024, 11, 1),
                20, 5, 0, 0, 0);

        // When
        boolean result = salarie.aLegalementDroitADesCongesPayes();

        // Then
        assertFalse(result);
    }

    // fais avec llm
    @ParameterizedTest(name = "Plage du {0} au {1} => {2} jour")
    @CsvSource({
            "2024-01-10, 2024-01-05, 0",  // debut apres fin
            "2024-01-03, 2024-01-03, 1",  // un seul jour ouvrable
            "2024-01-08, 2024-01-12, 6",  // lundi a vendredi
            "2024-01-07, 2024-01-07, 0",  // dimanche
            "2024-01-06, 2024-01-06, 0",  // samedi
    })
    //fin LLM

    void testCalculeJoursDeCongeDecomptesPourPlage(String debutStr, String finStr, int expectedSize) {
        // Given
        LocalDate dateDebut = LocalDate.parse(debutStr);
        LocalDate dateFin = LocalDate.parse(finStr);
        SalarieAideADomicile salarie = new SalarieAideADomicile(
                "Test", LocalDate.of(2020, 1, 1), LocalDate.of(2024, 11, 1),
                80, 20, 100, 25, 5);

        // When
        LinkedHashSet<LocalDate> result = salarie.calculeJoursDeCongeDecomptesPourPlage(dateDebut, dateFin);

        // Then
        assertNotNull(result);
        assertEquals(expectedSize, result.size());
    }
}
