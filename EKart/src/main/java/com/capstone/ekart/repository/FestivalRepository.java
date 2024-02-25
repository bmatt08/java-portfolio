package com.capstone.ekart.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.capstone.ekart.entity.Festival;

public interface FestivalRepository extends CrudRepository<Festival, Integer>{
	
	@Query("select f from Festival f where f.festivalStartsAt > ?1 order by f.festivalStartsAt")
	List<Festival> findByFestivalStartsAfter(LocalDateTime dateTime);
}
