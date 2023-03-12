package project.web.converter;

import org.springframework.stereotype.Component;
import project.core.model.Client;
import project.core.model.Location;
import project.web.dto.LocationDto;

@Component
public class LocationConverter extends BaseConverter<Location, LocationDto> {


    @Override
    public Location convertDtoToModel(LocationDto dto) {
        Location location = Location.builder()
                .name(dto.getName())
                .entranceFee(dto.getEntranceFee())
                .owner(dto.getOwner())
                .timeLimit(dto.getTimeLimit())
                .address(dto.getAddress())
                .build();
        location.setId(dto.getId());
        return location;
    }

    @Override
    public LocationDto convertModelToDto(Location location) {
        LocationDto dto = LocationDto.builder()
                .name(location.getName())
                .entranceFee(location.getEntranceFee())
                .owner(location.getOwner())
                .timeLimit(location.getTimeLimit())
                .address(location.getAddress())
                .build();
        dto.setId(location.getId());
        return dto;
    }
}
