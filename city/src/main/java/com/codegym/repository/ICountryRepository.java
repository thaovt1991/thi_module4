package com.codegym.repository;

import com.codegym.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICountryRepository extends JpaRepository<Country, Long> {

    @Query(value = "select c from Country c where c.isDelete = false ")
    List<Country> findAllNotDelete() ;
}
