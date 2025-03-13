package com.security.controller;

import com.security.dto.BreweryDTO;
import com.security.service.BreweryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BreweryController {

    @Autowired
    private BreweryService breweryService;

    @GetMapping("/user/breweries")
    public List<BreweryDTO> getBreweries() {
        return breweryService.getBreweries();
    }
}
