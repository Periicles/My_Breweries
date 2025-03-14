package com.breweries.controller;

import com.breweries.dto.BreweriesDTO;
import com.breweries.service.BreweriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BreweriesController {

    @Autowired
    private BreweriesService breweriesService;

    @GetMapping("/irish-breweries")
    @PreAuthorize("hasRole('SCRAPER')")
    public List<BreweriesDTO> getAndSaveIrishBreweries() {
        return breweriesService.fetchAndSaveAllIrishBreweries();
    }

    @GetMapping("/breweries/nano")
    @PreAuthorize("hasRole('USER')")
    public List<BreweriesDTO> getNanoBreweries() {
        return breweriesService.fetchNanoBreweries();
    }

    @GetMapping("/breweries/full")
    @PreAuthorize("hasRole('USER')")
    public List<BreweriesDTO> getFullBreweries() {
        System.out.println("This request may take a while to complete. Please be patient.");
        return breweriesService.fetchFullBreweries();
    }
}
