package com.rizvi.mockito.MockitoDemoExample.controllers;

import com.rizvi.mockito.MockitoDemoExample.fauxspring.Model;
import com.rizvi.mockito.MockitoDemoExample.services.VetService;

public class VetController {

    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    public String listVets(Model model){

        model.addAttribute("vets", vetService.findAll());

        return "vets/index";
    }
}
