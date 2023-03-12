package project.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.core.model.Client;
import project.core.model.Photoshoot;
import project.core.model.validators.CompanyException;
import project.core.model.validators.ValidatorException;
import project.core.repository.PhotoshootRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PhotoshootService implements IPhotoshootService{

    public static final Logger log = LoggerFactory.getLogger(PhotoshootService.class);

    @Autowired
    private PhotoshootRepository photoshootRepository;

    @Autowired
    private Environment environment;

    @Override
    @Transactional
    public List<Photoshoot> getAllPhotoshoots() {
        String configuration = this.environment.getProperty("repositoryConfiguration");

        // log.trace("getAllPhotoshoots --- method entered");
        List<Photoshoot> result;

        if(configuration.equals("JPQL")){
            System.out.println("JPQL");
            result = this.photoshootRepository.findAllWithClientsAndLocationJPQL();
        }
        else if(configuration.equals("API")){
            System.out.println("API");
            result = this.photoshootRepository.findAllWithClientsAndLocationCriteriaAPI();
        }
        else{
            System.out.println("SQL");
            result = this.photoshootRepository.findAllWithClientsAndLocation();
        }

        //log.trace("getAllPhotoshoots: result{}", result);

        return result;
    }

    @Override
    public void savePhotoshoot(Photoshoot photoshoot) {
        Photoshoot p = photoshootRepository.save(photoshoot);
        log.trace("savePhotoshoot - method finished photoshoot={}", p);
    }

    @Override
    public Set<Client> getPhotoshootClients(Long photoshootId) {
        log.trace("getPhotoshootClients, photoshootId={}", photoshootId);

        List<Photoshoot> photoshoots = this.getAllPhotoshoots();
        for(Photoshoot photoshoot : photoshoots){
            if(photoshoot.getId().equals(photoshootId))
                return photoshoot.getClients();
        }
        return null;

    }

    @Override
    public Optional<Photoshoot> getPhotoshootById(Long id) {
        return this.photoshootRepository.findById(id);
    }
}
