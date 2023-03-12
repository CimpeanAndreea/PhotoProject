package project.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.core.model.Photoshoot;
import project.core.model.validators.CompanyException;
import project.core.service.IPhotoshootService;
import project.web.converter.ClientConverter;
import project.web.converter.PhotographerConverter;
import project.web.converter.PhotoshootConverter;
import project.web.dto.ClientDto;
import project.web.dto.LocationDto;
import project.web.dto.PhotoshootDto;

import java.util.Set;

@RestController
public class PhotoshootController {
    public static final Logger log = LoggerFactory.getLogger(PhotoshootController.class);

    @Autowired
    private IPhotoshootService photoshootService;

    @Autowired
    private PhotoshootConverter photoshootConverter;

    @Autowired
    private ClientConverter clientConverter;

    @RequestMapping(value = "/photoshoots", method = RequestMethod.GET)
    Set<PhotoshootDto> getAllPhotoshoots(){
        //log.trace("start get photoshoots");
        Set<PhotoshootDto> photoshoots = photoshootConverter.convertModelsToDtos(photoshootService.getAllPhotoshoots());
        //log.trace("end get photoshoots={}", photoshoots);
        return photoshoots;
    }

    /*@RequestMapping(value = "/photoshoots/{id}", method = RequestMethod.GET)
    PhotoshootDto getPhotoshootById(@PathVariable Long id){
        log.trace("start get photoshoot");
        PhotoshootDto photoshoot = photoshootConverter.convertModelToDto(photoshootService.getPhotoshootById(id).get());
        log.trace("end get photoshoot={}", photoshoot);
        return photoshoot;
    }*/

    @RequestMapping(value = "/photoshoots/{id}/clients", method = RequestMethod.GET)
    Set<ClientDto> getPhotoshootClients(@PathVariable Long id){
        try{
            log.trace("start get photoshoot clients, photoshootId = {}", id);
            Set<ClientDto> clients = clientConverter.convertModelsToDtos(photoshootService.getPhotoshootClients(id));
            log.trace("end get photoshoot clients={}", clients);
            System.out.println("IN CONTROLLER: ");
            System.out.println(clients);
            return clients;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/photoshoots", method = RequestMethod.POST)
    ResponseEntity<?> savePhotoshoot(@RequestBody PhotoshootDto photoshootDto){
        //System.out.println("LOCATION IN SERVER");
        //System.out.println(locationDto);
        log.trace("start save photoshoot={}", photoshootDto);
        try{
            Photoshoot photoshoot = photoshootConverter.convertDtoToModel(photoshootDto);
            photoshootService.savePhotoshoot(photoshoot);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (CompanyException exception){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
