package com.breweries.service;

import com.breweries.dto.BreweriesDTO;
import com.breweries.entity.Brewery;
import com.breweries.repository.BreweriesRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BreweriesService {

    @Autowired
    private RestClient restClient;

    @Autowired
    private BreweriesRepository breweriesRepository;

    // This method will fetch all Irish breweries and save them to the database.
    public List<BreweriesDTO> fetchAndSaveAllIrishBreweries() {
        int page = 1;
        int perPage = 50;
        List<BreweriesDTO> allBreweries = new ArrayList<>();

        while (true) {
            String url = "https://api.openbrewerydb.org/v1/breweries?by_country=ireland&page=" + page + "&per_page=" + perPage;
            try {
                BreweriesDTO[] breweriesDTO = restClient.get()
                        .uri(url)
                        .retrieve()
                        .body(BreweriesDTO[].class);
                if (breweriesDTO == null || breweriesDTO.length == 0) {
                    break;
                }
                allBreweries.addAll(Arrays.asList(breweriesDTO));
                for (BreweriesDTO breweryDTO : breweriesDTO) {
                    Brewery brewery = convertToEntity(breweryDTO);
                    breweriesRepository.save(brewery);
                }
                // If the number of breweries returned is less than perPage, it means we've reached the last page
                if (breweriesDTO.length < perPage) {
                    break;
                }
                page++;
            } catch (HttpClientErrorException | HttpServerErrorException e) {
                // Handle client and server errors
                e.printStackTrace();
                break;
            } catch (RestClientResponseException e) {
                // Handle other errors
                e.printStackTrace();
                break;
            }
        }
        return allBreweries;
    }

    private Brewery convertToEntity(BreweriesDTO breweriesDTO) {
        Brewery brewery = new Brewery();
        BeanUtils.copyProperties(breweriesDTO, brewery);
        return brewery;
    }

    // This method will fetch only nano breweries, which are tiny breweries which typically only distributes locally.
    public List<BreweriesDTO> fetchNanoBreweries() {
        int page = 1;
        int perPage = 200;
        List<BreweriesDTO> allBreweries = new ArrayList<>();

        while (true) {
            String url = "https://api.openbrewerydb.org/v1/breweries?by_tag=nano&page=" + page + "&per_page=" + perPage;
            try {
                BreweriesDTO[] breweriesDTO = restClient.get()
                        .uri(url)
                        .retrieve()
                        .body(BreweriesDTO[].class);
                if (breweriesDTO == null || breweriesDTO.length == 0) {
                    break;
                }
                allBreweries.addAll(Arrays.asList(breweriesDTO));
                for (BreweriesDTO breweryDTO : breweriesDTO) {
                    Brewery brewery = convertToEntity(breweryDTO);
                    breweriesRepository.save(brewery);
                }
                // If the number of breweries returned is less than perPage, it means we've reached the last page
                if (breweriesDTO.length < perPage) {
                    break;
                }
                page++;
            } catch (HttpClientErrorException | HttpServerErrorException e) {
                // Handle client and server errors
                e.printStackTrace();
                break;
            } catch (RestClientResponseException e) {
                // Handle other errors
                e.printStackTrace();
                break;
            }
        }
        return allBreweries;
    }

    // This methods will fetch all breweries.
    public List<BreweriesDTO> fetchFullBreweries() {
        int page = 1;
        int perPage = 200;
        List<BreweriesDTO> allBreweries = new ArrayList<>();

        while (true) {
            String url = "https://api.openbrewerydb.org/v1/breweries?page=" + page + "&per_page=" + perPage;
            try {
                BreweriesDTO[] breweriesDTO = restClient.get()
                        .uri(url)
                        .retrieve()
                        .body(BreweriesDTO[].class);
                if (breweriesDTO == null || breweriesDTO.length == 0) {
                    break;
                }
                allBreweries.addAll(Arrays.asList(breweriesDTO));
                for (BreweriesDTO breweryDTO : breweriesDTO) {
                    Brewery brewery = convertToEntity(breweryDTO);
                    breweriesRepository.save(brewery);
                }
                // If the number of breweries returned is less than perPage, it means we've reached the last page
                if (breweriesDTO.length < perPage) {
                    break;
                }
                page++;
            } catch (HttpClientErrorException | HttpServerErrorException e) {
                // Handle client and server errors
                e.printStackTrace();
                break;
            } catch (RestClientResponseException e) {
                // Handle other errors
                e.printStackTrace();
                break;
            }
        }
        return allBreweries;
    }
}
