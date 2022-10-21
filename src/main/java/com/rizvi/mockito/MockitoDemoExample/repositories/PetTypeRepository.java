package com.rizvi.mockito.MockitoDemoExample.repositories;

import com.rizvi.mockito.MockitoDemoExample.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
