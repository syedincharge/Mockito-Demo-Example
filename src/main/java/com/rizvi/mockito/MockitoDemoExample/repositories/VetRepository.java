package com.rizvi.mockito.MockitoDemoExample.repositories;

import com.rizvi.mockito.MockitoDemoExample.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
