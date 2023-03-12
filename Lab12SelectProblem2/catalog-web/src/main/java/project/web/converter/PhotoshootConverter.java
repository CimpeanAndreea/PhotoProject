package project.web.converter;

import org.springframework.stereotype.Component;
import project.core.model.BaseEntity;
import project.core.model.Photoshoot;
import project.web.dto.PhotoshootDto;

import java.sql.Date;
import java.util.stream.Collectors;

@Component
public class PhotoshootConverter extends BaseConverter<Photoshoot, PhotoshootDto> {
    @Override
    public Photoshoot convertDtoToModel(PhotoshootDto dto) {
        Photoshoot photoshoot = Photoshoot.builder()
                .price(dto.getPrice())
                .date(Date.valueOf(dto.getDate()))
                .NoMaxClients(dto.getNoMaxClients())
                .location(dto.getLocation())
                .build();
        photoshoot.setId(dto.getId());
        return photoshoot;
    }

    @Override
    public PhotoshootDto convertModelToDto(Photoshoot photoshoot) {
        PhotoshootDto dto = PhotoshootDto.builder()
                .price(photoshoot.getPrice())
                .date(photoshoot.getDate().toString())
                .NoMaxClients(photoshoot.getNoMaxClients())
                .location(photoshoot.getLocation())
                .clients(photoshoot.getClients().stream()
                    .map(BaseEntity::getId).collect(Collectors.toSet()))
                .build();
        dto.setId(photoshoot.getId());
        return dto;
    }
}
