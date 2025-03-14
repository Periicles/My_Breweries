package com.breweries.service;

import com.breweries.dto.BreweriesDTO;
import com.breweries.entity.Brewery;
import com.breweries.repository.BreweriesRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    private BreweriesRepository breweriesRepository;

    public List<BreweriesDTO> getAllBreweries() {
        List<Brewery> breweries = breweriesRepository.findAll();
        return breweries.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public BreweriesDTO createBrewery(BreweriesDTO breweriesDTO) {
        Brewery brewery = convertToEntity(breweriesDTO);
        Brewery savedBrewery = breweriesRepository.save(brewery);
        return convertToDTO(savedBrewery);
    }

    public BreweriesDTO updateBrewery(String id, BreweriesDTO breweriesDTO) {
        Optional<Brewery> optionalBrewery = breweriesRepository.findById(id);
        if (optionalBrewery.isPresent()) {
            Brewery brewery = optionalBrewery.get();
            BeanUtils.copyProperties(breweriesDTO, brewery);
            Brewery updatedBrewery = breweriesRepository.save(brewery);
            return convertToDTO(updatedBrewery);
        }
        return null;
    }

    public void deleteBrewery(String id) {
        breweriesRepository.deleteById(id);
    }

    public BreweriesDTO getBreweryById(String id) {
        Optional<Brewery> optionalBrewery = breweriesRepository.findById(id);
        return optionalBrewery.map(this::convertToDTO).orElse(null);
    }

    private Brewery convertToEntity(BreweriesDTO breweriesDTO) {
        Brewery brewery = new Brewery();
        BeanUtils.copyProperties(breweriesDTO, brewery);
        return brewery;
    }

    private BreweriesDTO convertToDTO(Brewery brewery) {
        BreweriesDTO breweriesDTO = new BreweriesDTO();
        BeanUtils.copyProperties(brewery, breweriesDTO);
        return breweriesDTO;
    }
}
