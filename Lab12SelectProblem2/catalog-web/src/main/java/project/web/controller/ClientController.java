package project.web.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import project.core.model.Client;
import project.core.model.Photoshoot;
import project.core.model.validators.CompanyException;
import project.core.service.IClientService;
import project.web.converter.ClientConverter;
import project.web.converter.PhotoshootConverter;
import project.web.dto.ClientDto;
import project.web.dto.ClientsDto;
import project.web.dto.PhotoshootDto;

import java.util.Set;


@RestController
public class ClientController {
    public static final Logger log = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private IClientService clientService;

    @Autowired
    private ClientConverter clientConverter;

    @Autowired
    private PhotoshootConverter photoshootConverter;

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    Set<ClientDto> getAllClients(){
        log.trace("start get clients");
        Set<ClientDto> clients = clientConverter.convertModelsToDtos(clientService.getAllClients());
        log.trace("end get clients={}", clients);
        return clients;
    }

    @RequestMapping(value = "/clients/{id}/photoshoots", method = RequestMethod.GET)
    Set<PhotoshootDto> getClientPhotoshoot(@PathVariable Long id){
        log.trace("start get client photoshoots, clientId = {}", id);
        Set<PhotoshootDto> photoshoots = photoshootConverter.convertModelsToDtos(clientService.getClientPhotoshoots(id));
        log.trace("end get client photoshoots = {}", photoshoots);
        return photoshoots;
    }

    //@Transactional
    @RequestMapping(value = "/clients/{id}/photoshoots", method = RequestMethod.POST)
    ResponseEntity<?> addPhotoshoot(@PathVariable Long id, @RequestBody PhotoshootDto photoshootDto){
        log.trace("start add photoshooot to client, cliendId={}, photoshoot={}", id, photoshootDto);
        try{
            Photoshoot photoshoot = photoshootConverter.convertDtoToModel(photoshootDto);
            Client client = clientService.findClient(id).get();
            client.addPhotoshoot(photoshoot);
            clientService.updateClient(client);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (CompanyException exception){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    ResponseEntity<?> saveClient(@RequestBody ClientDto clientDto){
        log.trace("start save client={}", clientDto);
        try{
            Client client = clientConverter.convertDtoToModel(clientDto);
            clientService.saveClient(client);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (CompanyException exception){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/clients/{id}", method = RequestMethod.PUT)
    ResponseEntity<?> updateClient(@PathVariable Long id, @RequestBody ClientDto clientDto){
        log.trace("start update client={}", clientDto);
        try{
            System.out.println(clientDto);
            //System.out.println(clientConverter.convertDtoToModel(clientDto));
            clientService.updateClient(clientConverter.convertDtoToModel(clientDto));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (CompanyException exception){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteClient(@PathVariable Long id){
        log.trace("start delete client with id={}", id);
        try{
            clientService.deleteClient(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (CompanyException exception){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/clients/filter/{name}", method = RequestMethod.GET)
    Set<ClientDto> filterClients(@PathVariable String name){
        log.trace("start filter clients by name={}", name);
        Set<ClientDto> clients = clientConverter.convertModelsToDtos(clientService.filterClients(name));
        log.trace("end filter clients={}", clients);
        return clients;
    }
}
