package com.rizvi.mockito.MockitoDemoExample.repositories;

import com.rizvi.mockito.MockitoDemoExample.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
