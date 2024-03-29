package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

	City findByName(String stateName);

}
