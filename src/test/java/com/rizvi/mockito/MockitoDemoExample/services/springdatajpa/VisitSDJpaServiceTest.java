package com.rizvi.mockito.MockitoDemoExample.services.springdatajpa;

import com.rizvi.mockito.MockitoDemoExample.model.Visit;
import com.rizvi.mockito.MockitoDemoExample.repositories.VisitRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService service;

    @Test
    @DisplayName("Test for FindAll")
    void findAll() {
        Visit visit = new Visit();
        Set<Visit> visits = new HashSet<>();
        visits.add(visit);

        when(visitRepository.findAll()).thenReturn(visits);
        Set<Visit> foundVisits = service.findAll();
        verify(visitRepository).findAll();
        assertThat(foundVisits).hasSize(1);
    }

    @Test
    @DisplayName("Test for FindById")
    void findById() {
        Visit visit = new Visit();
        when(visitRepository.findById(anyLong())).thenReturn(Optional.of(visit));
        Visit foundVisit = service.findById(1L);
        verify(visitRepository).findById(anyLong());
        assertThat(foundVisit).isNotNull();
    }

    @Test
    @DisplayName("Test for Save")
    void save() {
        Visit visit = new Visit();

        when(visitRepository.save(any(Visit.class))).thenReturn(visit);
        Visit savedVisit = service.save(new Visit());
        verify(visitRepository).save(any(Visit.class));
        assertThat(savedVisit).isNotNull();

    }

    @Test
    @DisplayName("Test for Delete")
    void delete() {
        Visit visit = new Visit();

        service.delete(visit);

        verify(visitRepository).delete(any(Visit.class));

    }

    @Test

    @DisplayName("Test for DeleteById")
    void deleteById() {
       service.deleteById(1L);
       verify(visitRepository).deleteById(any());
    }
}