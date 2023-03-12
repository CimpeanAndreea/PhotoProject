package project.web.converter;

import org.springframework.stereotype.Component;
import project.core.model.BaseEntity;
import project.core.model.Client;
import project.web.dto.ClientDto;

import java.util.stream.Collectors;

@Component
public class ClientConverter extends BaseConverter<Client, ClientDto> {

    @Override
    public Client convertDtoToModel(ClientDto dto) {
        System.out.println("DTO");
        System.out.println(dto);
        Client client = Client.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .build();
        client.setId(dto.getId());
        System.out.println(client);
        return client;
    }

    @Override
    public ClientDto convertModelToDto(Client client) {
        ClientDto dto = ClientDto.builder()
                .email(client.getEmail())
                .name(client.getName())
                .phoneNumber(client.getPhoneNumber())
                .build();
        dto.setId(client.getId());
        return dto;
    }
}
