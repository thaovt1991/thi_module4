package com.codegym.service.country;

import com.codegym.model.Country;
import com.codegym.service.IGeneralService;

import java.util.List;

public interface ICountryService extends IGeneralService<Country> {

    List<Country> findAllNotDelete() ;
}
