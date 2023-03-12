package project.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.core.model.Client;
import project.core.model.Location;
import project.core.model.validators.CompanyException;
import project.core.model.validators.ValidatorException;
import project.core.repository.LocationRepository;

import java.util.List;

@Service
public class LocationService implements ILocationService{
    public static final Logger log = LoggerFactory.getLogger(LocationService.class);

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<Location> getAllLocations() {
        log.trace("getAllLocations --- method entered");

        List<Location> result = locationRepository.findAll();

        log.trace("getAllLocations: result{}", result);

        return result;
    }

    @Override
    public Location saveLocation(Location location) {
        log.trace("saveLocation - method entered location={}", location);

        Location l = locationRepository.save(location);
        log.trace("saveLocation - method finished location={}", l);
        return l;
    }
}
