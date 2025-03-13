package com.security.service;

import com.security.dto.BreweryDTO;
import com.security.entity.Brewery;
import com.security.repository.BreweryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BreweryService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BreweryRepository breweryRepository;

    public List<BreweryDTO> fetchAndSaveAllIrishBreweries() {
        int page = 1;
        int perPage = 50;
        List<BreweryDTO> allBreweries = new ArrayList<>();

        while (true) {
            String url = "https://api.openbrewerydb.org/v1/breweries?by_country=ireland&page=" + page + "&per_page=" + perPage;
            BreweryDTO[] breweriesDTO = restTemplate.getForObject(url, BreweryDTO[].class);
            if (breweriesDTO == null || breweriesDTO.length == 0) {
                break;
            }
            allBreweries.addAll(Arrays.asList(breweriesDTO));
            // Save each brewery to the database
            for (BreweryDTO breweryDTO : breweriesDTO) {
                Brewery brewery = convertToEntity(breweryDTO);
                breweryRepository.save(brewery);
            }
            // If the number of breweries returned is less than perPage, it means we've reached the last page
            if (breweriesDTO.length < perPage) {
                break;
            }
            page++;
        }
        return allBreweries;
    }

    private Brewery convertToEntity(BreweryDTO breweryDTO) {
        Brewery brewery = new Brewery();
        BeanUtils.copyProperties(breweryDTO, brewery);
        return brewery;
    }
}
