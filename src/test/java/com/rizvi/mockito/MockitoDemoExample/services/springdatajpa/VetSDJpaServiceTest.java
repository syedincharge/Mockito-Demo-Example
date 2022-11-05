package com.rizvi.mockito.MockitoDemoExample.services.springdatajpa;

import com.rizvi.mockito.MockitoDemoExample.model.Person;
import com.rizvi.mockito.MockitoDemoExample.model.Vet;
import com.rizvi.mockito.MockitoDemoExample.model.Visit;
import com.rizvi.mockito.MockitoDemoExample.repositories.VetRepository;
import com.rizvi.mockito.MockitoDemoExample.repositories.VisitRepository;
import com.rizvi.mockito.MockitoDemoExample.services.VetService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VetSDJpaServiceTest {

    @Mock
    private VetRepository vetRepository;

    @InjectMocks
    private VetSDJpaService service;

    @Test
    void findAll() {


    }

    @Test
    void findById() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() {


    }

    @Test
    void deleteById() {

        service.deleteById(1l);

        verify(vetRepository).deleteById(1l);
    }
}