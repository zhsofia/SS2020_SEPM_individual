package at.ac.tuwien.sepm.assignment.individual.endpoint.mapper;

import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.OwnerDto;
import at.ac.tuwien.sepm.assignment.individual.entity.Owner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @brief contains entityToDto() method for Owner*/
@Component
public class OwnerMapper {

    public OwnerDto entityToDto(Owner owner) {
        return new OwnerDto(owner.getId(), owner.getName(), owner.getCreatedAt(), owner.getUpdatedAt());
    }

    public Owner dtoToEntity(OwnerDto ownerDto){
        return new Owner(ownerDto.getId(),ownerDto.getName(),ownerDto.getCreatedAt(),ownerDto.getUpdatedAt());
    }

    public List<OwnerDto> listOfEntityToDto(List<Owner> owners){
        List<OwnerDto> ownerDtos= new ArrayList<>();
        for (int i = 0; i < owners.size(); i++) {
            ownerDtos.add(entityToDto(owners.get(i)));
        }
        return ownerDtos;
    }
}
