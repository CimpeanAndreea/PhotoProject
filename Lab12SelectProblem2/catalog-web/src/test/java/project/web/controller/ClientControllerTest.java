package project.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import project.core.model.Client;
import project.core.service.ClientService;
import project.web.converter.ClientConverter;
import project.web.dto.ClientDto;

import java.util.*;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class ClientControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientService clientService;

    @Mock
    private ClientConverter clientConverter;

    private Client client1;
    private Client client2;
    private ClientDto clientDto1;
    private ClientDto clientDto2;

    private ClientDto createClientDto(Client client) {
        ClientDto clientDto = ClientDto.builder()
                .name(client.getName())
                .email(client.getEmail())
                .phoneNumber(client.getPhoneNumber())
                .build();
        clientDto.setId(client.getId());
        clientDto.setId(client.getId());
        return clientDto;
    }

    private void initData() {
        client1 = Client.builder()
                .name("Ana")
                .email("ana@gmail.com")
                .phoneNumber("0987654321")
                .build();
        client1.setId(1l);


        client2 = Client.builder()
                .name("Iona")
                .email("iana@gmail.com")
                .phoneNumber("0987654321")
                .build();
        client2.setId(2l);

        clientDto1 = createClientDto(client1);
        clientDto2 = createClientDto(client2);

    }

    @Before
    public void setup() throws Exception{
        initMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(clientController)
                .build();
        initData();
    }

    private String toJsonString(Map<String, ClientDto> clientDtoMap) {
        try {
            return new ObjectMapper().writeValueAsString(clientDtoMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String toJsonString(ClientDto clientDto) {
        try {
            return new ObjectMapper().writeValueAsString(clientDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getClients() throws Exception {
        List<Client> clients = Arrays.asList(client1, client2);
        Set<ClientDto> clientDtos =
                new HashSet<>(Arrays.asList(clientDto1, clientDto2));
        when(clientService.getAllClients()).thenReturn(clients);
        when(clientConverter.convertModelsToDtos(clients)).thenReturn(clientDtos);

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.get("/clients"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", anyOf(is("Ana"), is("Iona"))));

        String result = resultActions.andReturn().getResponse().getContentAsString();
//        System.out.println(result);

        verify(clientService, times(1)).getAllClients();
        verify(clientConverter, times(1)).convertModelsToDtos(clients);
        verifyNoMoreInteractions(clientService, clientConverter);


    }

    @Test
    public void updateClient() throws Exception {


        //when(clientService.updateClient(client)).;
        when(clientConverter.convertDtoToModel(clientDto1)).thenReturn(client1);

//        Map<String, clientDto> clientDtoMap = new HashMap<>();
//        clientDtoMap.put("client", clientDto1);

        //System.out.println(clientDto1.getId());

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/clients/{id}", client1.getId(), clientDto1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJsonString(clientDto1)))

                .andExpect(status().isOk());


        verify(clientConverter, times(1)).convertDtoToModel(any(ClientDto.class));
        //verify(clientConverter, times(1)).convertModelToDto(client1);
        verify(clientService, times(1)).updateClient(any());
        verifyNoMoreInteractions(clientService, clientConverter);
    }



}
