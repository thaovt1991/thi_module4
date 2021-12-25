package com.codegym.repository;

import com.codegym.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICityRepository extends JpaRepository<City,Long> {

    @Query(value = "select c from City c where c.isDelete = false ")
    List<City> findAllNotDelete() ;
}
