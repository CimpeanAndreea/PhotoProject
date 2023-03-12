package project.core.service;

import project.core.model.Client;
import project.core.model.Location;
import project.core.model.validators.CompanyException;

import java.util.List;

public interface ILocationService {
    List<Location> getAllLocations();
    Location saveLocation(Location location);
}
