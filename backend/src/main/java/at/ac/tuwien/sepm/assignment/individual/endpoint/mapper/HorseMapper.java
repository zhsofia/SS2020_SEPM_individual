package at.ac.tuwien.sepm.assignment.individual.endpoint.mapper;

import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.HorseDto;
import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HorseMapper {

    public Horse dtoToEntity(HorseDto horseDto){
        return new Horse(horseDto.getId(),horseDto.getCreatedAt(),horseDto.getUpdatedAt(),
            horseDto.getName(),horseDto.getBirthdate(),horseDto.getDescription(),
            horseDto.getRating(),horseDto.getRace(),horseDto.getIdofowner(),horseDto.getImageBase64());
    }

    public HorseDto entityToDto(Horse horse){
        return new HorseDto(horse.getId(),horse.getCreatedAt(),horse.getUpdatedAt(),horse.getName(),
            horse.getBirth_date(),horse.getDescription(),horse.getRating(),
            horse.getRace(), horse.getIdofowner(),horse.getImageBase64());
    }

    public List<HorseDto> listOfEntityToDto(List<Horse> horses){
        List<HorseDto> horseDtos=new ArrayList<>();
        if(horses!=null) {
            for (int i = 0; i < horses.size(); i++) {
                horseDtos.add(entityToDto(horses.get(i)));
            }
        }
        return horseDtos;
    }
}

