package project.core.repository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import project.core.ITConfig;
import project.core.model.Client;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ITConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@DatabaseSetup("/META-INF/dbtest/db-data.xml")
public class ClientRepositoryTest {
    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void findAllWithPhotoshootsAndLocationJPQL(){
        List<Client> clients = clientRepository.findAllWithPhotoshootsAndLocationJPQL();
        assertEquals(clients.size(), 2);

        Client client1 = clients.stream().filter(c->c.getId().equals(1L)).findFirst().get();
        Client client2 = clients.stream().filter(c->c.getId().equals(2L)).findFirst().get();

        assertEquals(client1.getName(), "Ana");

        assertEquals(client1.getPhotoshoots().size(), 1);
        assertEquals(client2.getPhotoshoots().size(), 0);
        assertEquals(client1.getPhotoshoots().stream().filter(p->p.getLocation().getId().equals(1L)).collect(Collectors.toList()).size(), 1);

    }

    @Test
    public void findAllWithPhotoshootsAndLocationCriteriaAPI(){
        List<Client> clients = clientRepository.findAllWithPhotoshootsAndLocationCriteriaAPI();
        assertEquals(clients.size(), 2);

        Client client1 = clients.stream().filter(c->c.getId().equals(1L)).findFirst().get();
        Client client2 = clients.stream().filter(c->c.getId().equals(2L)).findFirst().get();

        assertEquals(client1.getName(), "Ana");

        assertEquals(client1.getPhotoshoots().size(), 1);
        assertEquals(client2.getPhotoshoots().size(), 0);

        assertEquals(client1.getPhotoshoots().stream().filter(p->p.getLocation().getId().equals(1L)).collect(Collectors.toList()).size(), 1);

    }

    @Test
    public void findAllWithPhotoshootsAndLocationSQL(){
        List<Client> clients = clientRepository.findAllWithPhotoshootsAndLocation();
        assertEquals(clients.size(), 2);

        Client client1 = clients.stream().filter(c->c.getId().equals(1L)).findFirst().get();
        Client client2 = clients.stream().filter(c->c.getId().equals(2L)).findFirst().get();

        assertEquals(client1.getName(), "Ana");

        assertEquals(client1.getPhotoshoots().size(), 1);
        assertEquals(client2.getPhotoshoots().size(), 0);

        assertEquals(client1.getPhotoshoots().stream().filter(p->p.getLocation().getId().equals(1L)).collect(Collectors.toList()).size(), 1);

    }

}
