package com.rizvi.mockito.MockitoDemoExample.repositories;

import com.rizvi.mockito.MockitoDemoExample.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
