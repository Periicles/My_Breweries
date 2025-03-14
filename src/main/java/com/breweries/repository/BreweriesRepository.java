package com.breweries.repository;

import com.breweries.entity.Brewery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreweriesRepository extends JpaRepository<Brewery, String> {
}
