package com.breweries.controller;

import com.breweries.dto.BreweriesDTO;
import com.breweries.service.BreweriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BreweriesController {

    @Autowired
    private BreweriesService breweriesService;

    @GetMapping("/scraper/irish-breweries")
    public List<BreweriesDTO> getAndSaveIrishBreweries() {
        return breweriesService.fetchAndSaveAllIrishBreweries();
    }

    @GetMapping("/user/breweries/random")
    public List<BreweriesDTO> getRandomBreweries() {
        return breweriesService.fetchRandomBreweries();
    }

    @GetMapping("/user/breweries")
    public List<BreweriesDTO> getFullBreweries() {
        System.out.println("This request may take a while to complete. Please be patient.");
        return breweriesService.fetchFullBreweries();
    }

    @GetMapping("/admin/breweries")
    public List<BreweriesDTO> getAllBreweries() {
        return breweriesService.getIrishBreweriesInDB();
    }

    @PostMapping("/admin/breweries")
    public BreweriesDTO createBrewery(@RequestBody BreweriesDTO breweriesDTO) {
        return breweriesService.createBrewery(breweriesDTO);
    }

    @PutMapping("/admin/breweries/{id}")
    public BreweriesDTO updateBrewery(@PathVariable String id, @RequestBody BreweriesDTO breweriesDTO) {
        return breweriesService.updateBrewery(id, breweriesDTO);
    }

    @DeleteMapping("admin/breweries/{id}")
    public void deleteBrewery(@PathVariable String id) {
        breweriesService.deleteBrewery(id);
    }

    @GetMapping("admin/breweries/{id}")
    public BreweriesDTO getBreweryById(@PathVariable String id) {
        return breweriesService.getBreweryById(id);
    }
}
