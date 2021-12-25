package com.codegym.service.city;

import com.codegym.model.City;
import com.codegym.model.Country;
import com.codegym.repository.ICityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CityService implements ICityService{

    @Autowired
    private ICityRepository cityRepository ;

    @Override
    public Iterable<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public Optional<City> findById(Long id) {
        return cityRepository.findById(id);
    }

    @Override
    public void save(City city) {
          cityRepository.save(city) ;
    }

    @Override
    public void remove(Long id) {
        City city = cityRepository.findById(id).get() ;
        city.setDelete(true);
        cityRepository.save(city);
    }

    @Override
    public List<City> findAllNotDelete() {
        return cityRepository.findAllNotDelete();
    }
}
