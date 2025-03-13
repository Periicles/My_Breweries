package com.security.service;

import com.security.dto.BreweryDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class BreweryService {

    private static final String BREWERIES_API_URL = "https://api.openbrewerydb.org/v1/breweries?per_page=3";

    public List<BreweryDTO> getBreweries() {
        RestTemplate restTemplate = new RestTemplate();
        BreweryDTO[] breweries = restTemplate.getForObject(BREWERIES_API_URL, BreweryDTO[].class);
        return Arrays.asList(breweries);
    }
}
