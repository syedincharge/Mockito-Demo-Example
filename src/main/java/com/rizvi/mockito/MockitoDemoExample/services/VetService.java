package com.rizvi.mockito.MockitoDemoExample.services;

import com.rizvi.mockito.MockitoDemoExample.model.Vet;
import com.rizvi.mockito.MockitoDemoExample.services.springdatajpa.CrudService;

import java.util.Set;

public interface VetService extends CrudService<Vet, Long> {

    Set<Vet> findAll();

    Vet findById(Long aLong);

    Vet save(Vet object);

    void delete(Vet object);

    void deleteById(Long aLong);
}
