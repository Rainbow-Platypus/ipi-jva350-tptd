package com.ipi.jva350.repository;

import com.ipi.jva350.model.SalarieAideADomicile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SalarieAideADomicileRepositoryTest {

    @Autowired
    private SalarieAideADomicileRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void testFindByNom_existant() {
        // Given
        SalarieAideADomicile salarie = new SalarieAideADomicile(
                "Dupont", LocalDate.of(2020, 1, 1), LocalDate.of(2024, 11, 1),
                80, 20, 100, 25, 5);
        repository.save(salarie);

        // When
        SalarieAideADomicile result = repository.findByNom("Dupont");

        // Then
        assertNotNull(result);
        assertEquals("Dupont", result.getNom());
    }

    @Test
    void testFindByNom_inexistant() {
        // Given
        // rien en base

        // When
        SalarieAideADomicile result = repository.findByNom("Inconnu");

        // Then
        assertNull(result);
    }

    @Test
    void testPartCongesPrisTotauxAnneeNMoins1_aucunSalarie() {
        // Given
        // base vide

        // When
        Double result = repository.partCongesPrisTotauxAnneeNMoins1();

        // Then
        assertNull(result);
    }

    @Test
    void testPartCongesPrisTotauxAnneeNMoins1_avecSalaries() {
        // Given
        SalarieAideADomicile salarie1 = new SalarieAideADomicile(
                "Dupont", LocalDate.of(2020, 1, 1), LocalDate.of(2024, 11, 1),
                80, 20, 100, 25, 10);
        SalarieAideADomicile salarie2 = new SalarieAideADomicile(
                "Martin", LocalDate.of(2021, 3, 1), LocalDate.of(2024, 11, 1),
                60, 15, 80, 25, 15);
        repository.save(salarie1);
        repository.save(salarie2);

        // When
        Double result = repository.partCongesPrisTotauxAnneeNMoins1();

        // Then
        assertNotNull(result);
        assertEquals(0.5, result, 0.01);
    }
}
