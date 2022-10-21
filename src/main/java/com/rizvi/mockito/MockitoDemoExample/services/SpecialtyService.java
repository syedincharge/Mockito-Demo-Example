package com.rizvi.mockito.MockitoDemoExample.services;

import com.rizvi.mockito.MockitoDemoExample.model.Speciality;
import org.springframework.data.repository.Repository;

import java.util.Set;

public interface SpecialtyService extends Repository<Speciality, Long> {
    Set<Speciality> findAll();

    Speciality findById(Long aLong);

    Speciality save(Speciality object);

    void delete(Speciality object);

    void deleteById(Long aLong);
}
