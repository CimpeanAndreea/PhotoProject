package project.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import project.core.model.Location;
import project.core.model.validators.CompanyException;
import project.core.service.ILocationService;
import project.web.converter.LocationConverter;
import project.web.dto.ClientDto;
import project.web.dto.LocationDto;

import java.util.Set;

@RestController
public class LocationController {
    public static final Logger log = LoggerFactory.getLogger(LocationController.class);

    @Autowired
    private ILocationService locationService;

    @Autowired
    private LocationConverter locationConverter;

    @RequestMapping(value = "/locations", method = RequestMethod.GET)
    Set<LocationDto> getAllLocations(){
        log.trace("start get locations");
        Set<LocationDto> locations = locationConverter.convertModelsToDtos(locationService.getAllLocations());
        log.trace("end get locations={}", locations);
        return locations;
    }

    @RequestMapping(value = "/locations", method = RequestMethod.POST)
    LocationDto saveLocation(@RequestBody LocationDto locationDto){
        System.out.println("LOCATION IN SERVER");
        System.out.println(locationDto);
        log.trace("start save location={}", locationDto);
        Location location = locationConverter.convertDtoToModel(locationDto);
        return locationConverter.convertModelToDto(locationService.saveLocation(location));
    }
}
