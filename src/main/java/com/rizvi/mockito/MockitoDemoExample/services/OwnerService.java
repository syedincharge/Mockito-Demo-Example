package com.rizvi.mockito.MockitoDemoExample.services;

import com.rizvi.mockito.MockitoDemoExample.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
 }
