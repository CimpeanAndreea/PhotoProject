package project.core.service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
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
import project.core.model.Photoshoot;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ITConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@DatabaseSetup("/META-INF/dbtest/db-data.xml")
public class ClientServiceTest {

    @Autowired
    private IClientService clientService;

    @Test
    public void getAllClients(){
        List<Client> clients = clientService.getAllClients();
        assertEquals(clients.size(), 2);
    }


    @Test
    public void filterClients(){
        List<Client> clients = clientService.filterClients("na");
        assertEquals(clients.size(), 2);
    }

    @Test
    public void findClient(){
        Client client = clientService.findClient(1L).get();
        assertEquals(client.getName(), "Ana");
    }

    @Test
    public void getClientPhotoshoots(){
        Set<Photoshoot> photoshoots = clientService.getClientPhotoshoots(1L);
        assertEquals(photoshoots.size(), 1);
    }

    @Test
    public void updateClient(){
        Client newClient = new Client("NEW", "new@new.com", "0987654321", null);
        newClient.setId(1L);
        clientService.updateClient(newClient);
        assertEquals(clientService.findClient(1L).get().getName(), "NEW");
    }

    @Test
    public void deleteClient(){
        clientService.deleteClient(1L);
        assertEquals(clientService.getAllClients().size(), 1);
    }







    /*@Test
    public void saveClient(){
        Client newClient = new Client("Ion", "ion@gmail.com", "0123456789",null);
        clientService.saveClient(newClient);
        assertEquals(clientService.getAllClients().size(), 3);
    }*/

}
