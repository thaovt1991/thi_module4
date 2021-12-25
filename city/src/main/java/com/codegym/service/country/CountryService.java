package com.codegym.service.country;

import com.codegym.model.Country;
import com.codegym.repository.ICountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService implements ICountryService{

    @Autowired
    private ICountryRepository countryRepository ;

    @Override
    public Iterable<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public void save(Country country) {
         countryRepository.save(country) ;
    }

    @Override
    public void remove(Long id) {
        Country country = countryRepository.findById(id).get() ;
        country.setDelete(true);
        countryRepository.save(country);
    }

    @Override
    public List<Country> findAllNotDelete() {
        return countryRepository.findAllNotDelete();
    }
}
