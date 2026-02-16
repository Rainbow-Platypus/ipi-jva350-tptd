package com.ipi.jva350.service;

import com.ipi.jva350.model.SalarieAideADomicile;
import com.ipi.jva350.repository.SalarieAideADomicileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SalarieAideADomicileServiceIntegrationTest {

    @Autowired
    private SalarieAideADomicileService service;

    @Autowired
    private SalarieAideADomicileRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void testCalculeLimiteEntrepriseCongesPermis_casTypique() {
        // Given
        SalarieAideADomicile salarie = new SalarieAideADomicile(
                "Dupont", LocalDate.of(2020, 1, 1), LocalDate.of(2024, 11, 1),
                80, 20, 100, 25, 10);
        repository.save(salarie);

        // When
        long limite = service.calculeLimiteEntrepriseCongesPermis(
                LocalDate.of(2024, 11, 1),
                25.0,
                LocalDate.of(2020, 1, 1),
                LocalDate.of(2024, 12, 1),
                LocalDate.of(2024, 12, 15)
        );

        // Then
        assertTrue(limite >= 0);
    }

    @Test
    void testCalculeLimiteEntrepriseCongesPermis_nouvelEmploye() {
        // Given
        SalarieAideADomicile salarie = new SalarieAideADomicile(
                "Nouveau", LocalDate.of(2024, 6, 1), LocalDate.of(2024, 11, 1),
                50, 12.5, 80, 10, 0);
        repository.save(salarie);

        // When
        long limite = service.calculeLimiteEntrepriseCongesPermis(
                LocalDate.of(2024, 11, 1),
                10.0,
                LocalDate.of(2024, 6, 1),
                LocalDate.of(2024, 12, 1),
                LocalDate.of(2024, 12, 10)
        );

        // Then
        assertTrue(limite >= 0);
    }
}
