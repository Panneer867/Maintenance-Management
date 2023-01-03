package com.ingroinfo.mm.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ingroinfo.mm.entity.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

	@Query("SELECT s.id,s.name FROM State s")
	List<Object[]> getAllStates();

	@Query("SELECT c.id,c.name FROM State s JOIN s.cities AS c WHERE s.id=:id")
	List<Object[]> getCitiesByState(Integer id);

	State findByName(String name);
}
