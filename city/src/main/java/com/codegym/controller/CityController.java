package com.codegym.controller;

import com.codegym.model.City;
import com.codegym.model.Country;
import com.codegym.service.city.ICityService;
import com.codegym.service.country.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.util.List;
import java.util.Optional;

@Controller
public class CityController {

    @Autowired
    private ICityService cityService ;

    @Autowired
    private ICountryService iCountryService ;




    @GetMapping("cities")
    public ModelAndView showListCity(){
        ModelAndView modelAndView = new ModelAndView(("/city/list"));
        List<City> listCity = cityService.findAllNotDelete() ;
        modelAndView.addObject("listCity", listCity ) ;
        return modelAndView ;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/city/create");
        List<Country> countries = iCountryService.findAllNotDelete();
        modelAndView.addObject("countries", countries) ;
        modelAndView.addObject("city", new City());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveCity(@Validated @ModelAttribute("city") City city, BindingResult bindingResult) throws Exception {
        ModelAndView modelAndView = new ModelAndView("/city/create");

        List<Country> countries = iCountryService.findAllNotDelete();

        if (bindingResult.hasFieldErrors()) {
            List<ObjectError> errorList = bindingResult.getAllErrors();
            String error = "New city created error " + "\n";
            for (int i = 0; i < errorList.size(); i++) {
                error += "*" + errorList.get(i).getDefaultMessage() + "\n";
            }


            modelAndView.addObject("countries", countries) ;
            modelAndView.addObject("error", error);
            return modelAndView;
        }
        try {
            cityService.save(city);
            modelAndView.addObject("countries", countries) ;
            modelAndView.addObject("city", new City());
            modelAndView.addObject("message", "New city created successfully");
            return modelAndView;
        } catch (Exception e) {
            modelAndView.addObject("error", "Registered email");
            return modelAndView;
        }
    }



    @GetMapping("/view/{id}")
    public ModelAndView showInformaton(@PathVariable Long id){
        Optional<City> city = cityService.findById(id);
        List<Country> countries = iCountryService.findAllNotDelete();
        if (city.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/city/view");
            modelAndView.addObject("city", city.get());
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEdit(@PathVariable Long id){
        Optional<City> city = cityService.findById(id);
        List<Country> countries = iCountryService.findAllNotDelete();
        if (city.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/city/edit");
            modelAndView.addObject("countries", countries) ;
            modelAndView.addObject("city", city.get());
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit")
    public ModelAndView updateCity(@Validated @ModelAttribute("city") City city, BindingResult bindingResult) throws Exception {
        ModelAndView modelAndView = new ModelAndView("/city/edit");
        List<Country> countries = iCountryService.findAllNotDelete();
        modelAndView.addObject("countries", countries) ;
        if (bindingResult.hasFieldErrors()) {
            List<ObjectError> errorList = bindingResult.getAllErrors();
            String error = "Edit city error " + "\n";
            for (int i = 0; i < errorList.size(); i++) {
                error += "*" + errorList.get(i).getDefaultMessage() + "\n";
            }
            modelAndView.addObject("error", error);
            return modelAndView;
        }
        try {
            cityService.save(city);
            modelAndView.addObject("city", new City());
            modelAndView.addObject("message", "City updated successfully");
            return modelAndView;
        } catch (Exception e) {
            modelAndView.addObject("error", "Registered email");
            return modelAndView;
        }
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDelete(@PathVariable Long id){
        Optional<City> city = cityService.findById(id);
        if (city.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/city/delete");
            modelAndView.addObject("city", city.get());
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete")
    public String deleteCity(@ModelAttribute("city") City city)  {
        cityService.remove(city.getId());
        return "redirect:/cities";
    }

}
