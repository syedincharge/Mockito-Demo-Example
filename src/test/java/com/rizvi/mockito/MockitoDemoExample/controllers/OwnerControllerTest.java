package com.rizvi.mockito.MockitoDemoExample.controllers;

import com.rizvi.mockito.MockitoDemoExample.fauxspring.BindingResult;
import com.rizvi.mockito.MockitoDemoExample.model.Owner;
import com.rizvi.mockito.MockitoDemoExample.services.OwnerService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {


    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController controller;

    @Mock
    BindingResult bindingResult;

    @Test
    void processCreationFormHasErrors() {
        //given
        Owner owner = new Owner(1L, "Jim", "Bob");
        given(bindingResult.hasErrors()).willReturn(true);

        //when
        String viewName =controller.processCreationForm(owner, bindingResult);

        //then
     assertThat(viewName).isEqualToIgnoringCase("owners/createOrUpdateOwnerForm");
    }

    @Test
    void processCreationFormNotErrors() {

        //given
        Owner owner = new Owner(5L, "Jim", "Bob");
        given(bindingResult.hasErrors()).willReturn(false);
        given(ownerService.save(any())).willReturn(owner);

        //when
        String viewName =controller.processCreationForm(owner, bindingResult);


        //then
        assertThat(viewName).isEqualToIgnoringCase("redirect:/owners/5");
    }
}