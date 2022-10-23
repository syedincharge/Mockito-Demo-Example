package com.rizvi.mockito.MockitoDemoExample.controllers;

import com.rizvi.mockito.MockitoDemoExample.fauxspring.BindingResult;
import com.rizvi.mockito.MockitoDemoExample.fauxspring.Model;
import com.rizvi.mockito.MockitoDemoExample.model.Owner;
import com.rizvi.mockito.MockitoDemoExample.services.OwnerService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {


    private static final String OWNERS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
    private static final String REDIRECT_OWNERS_5 = "redirect:/owners/5";
    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController controller;

    @Mock
    BindingResult bindingResult;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @BeforeEach
    void setUp() {
        given(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture())).willAnswer(
                invocation -> {
                    List<Owner> owners = new ArrayList<>();

                    String name = invocation.getArgument(0);
                    if (name.equals("%Buck%")) {
                        owners.add(new Owner(1L, "Joe", "Buck"));
                        return owners;
                    } else if (name.equals("%DontFindMe%")) {
                        return owners;
                    }else if (name.equals("%FindMe%")) {
                        owners.add(new Owner(1L, "Joe", "Buck"));
                        owners.add(new Owner(2L, "John", "Doe"));
                        return owners;
                    }
                    throw new RuntimeException("Invalid Argument");
                });
    }
    @Test
    void processFindFormWildcardStringFound(){
        //given
        Owner owner = new Owner(1L, "Joe", "FindMe");

        //when
        String viewName =controller.processFindForm(owner, bindingResult, Mockito.mock(Model.class));

        //then
        assertThat("%FindMe%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("owners/ownersList").isEqualToIgnoringCase(viewName);

    }

    @Test
    void processFindFormWildcardStringAnnotation(){
        //given
        Owner owner = new Owner(1L, "Joe", "Buck");


        //when
        String viewName =controller.processFindForm(owner, bindingResult, null);

        //then
        assertThat("%Buck%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("redirect:/owners/1").isEqualToIgnoringCase(viewName);

    }

    @Test
    void processFindFormWildcardStringNotFound(){
        //given
        Owner owner = new Owner(1L, "Joe", "DontFindMe");

        //when
        String viewName =controller.processFindForm(owner, bindingResult, null);

        //then
        assertThat("%DontFindMe%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("owners/findOwners").isEqualToIgnoringCase(viewName);

    }

    @Test
    void processCreationFormHasErrors() {
        //given
        Owner owner = new Owner(1L, "Jim", "Bob");
        given(bindingResult.hasErrors()).willReturn(true);

        //when
        String viewName =controller.processCreationForm(owner, bindingResult);

        //then
     assertThat(viewName).isEqualToIgnoringCase(OWNERS_CREATE_OR_UPDATE_OWNER_FORM);
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
        assertThat(viewName).isEqualToIgnoringCase(REDIRECT_OWNERS_5);
    }
}