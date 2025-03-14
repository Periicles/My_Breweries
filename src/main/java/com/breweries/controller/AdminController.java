package com.breweries.controller;

import com.breweries.dto.BreweriesDTO;
import com.breweries.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/breweries")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<BreweriesDTO> getAllBreweries() {
        return adminService.getAllBreweries();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public BreweriesDTO createBrewery(@RequestBody BreweriesDTO breweriesDTO) {
        return adminService.createBrewery(breweriesDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public BreweriesDTO updateBrewery(@PathVariable String id, @RequestBody BreweriesDTO breweriesDTO) {
        return adminService.updateBrewery(id, breweriesDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteBrewery(@PathVariable String id) {
        adminService.deleteBrewery(id);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public BreweriesDTO getBreweryById(@PathVariable String id) {
        return adminService.getBreweryById(id);
    }
}
