package project.core.service;

import project.core.model.Client;
import project.core.model.Photoshoot;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IPhotoshootService {
    List<Photoshoot> getAllPhotoshoots();
    void savePhotoshoot(Photoshoot photoshoot);

    Set<Client> getPhotoshootClients(Long photoshootId);

    Optional<Photoshoot> getPhotoshootById(Long id);
}

