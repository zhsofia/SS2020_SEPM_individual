package at.ac.tuwien.sepm.assignment.individual.endpoint;

import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.HorseDto;
import at.ac.tuwien.sepm.assignment.individual.exception.DataProcessingException;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.util.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import at.ac.tuwien.sepm.assignment.individual.endpoint.mapper.HorseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.invoke.MethodHandles;
import java.util.List;

import at.ac.tuwien.sepm.assignment.individual.service.HorseService;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(HorseEndpoint.BASE_URL)
public class HorseEndpoint {
    static final String BASE_URL = "/horses";
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final HorseMapper horseMapper;
    private final HorseService horseService;


    @Autowired
    public HorseEndpoint(@Qualifier("SimpleHorseService") HorseService horseService, HorseMapper horseMapper){
        this.horseMapper=horseMapper;
        this.horseService = horseService;
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public HorseDto getHorseById(@PathVariable("id") Long id){
        LOGGER.info("GET " + BASE_URL + " " + id);
        try {
            return horseMapper.entityToDto(horseService.getHorse(id));
        }catch (Exception e){
            String loggerErrorMessage = "getHorseById(): id="+id;
            throw exceptionHandler(e,loggerErrorMessage);
        }
    }



    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    public void insertHorse(@RequestBody HorseDto horseDto){
        LOGGER.info("POST " + BASE_URL + " " + horseDto);
        try{
            horseService.insertHorse(horseMapper.dtoToEntity(horseDto));
        }catch (Exception e){
            String loggerErrorMessage = "insertHorse(): "+horseDto;
            throw exceptionHandler(e,loggerErrorMessage);
        }
    }

    @PutMapping(value = "")
    public void updateHorse(@RequestBody HorseDto horseDto){
        LOGGER.info("PUT " + BASE_URL + " " + horseDto);
        try{
            horseService.updateHorse(horseMapper.dtoToEntity(horseDto));
        }catch (Exception e){
            String loggerErrorMessage = "updateHorse(): "+horseDto;
            throw exceptionHandler(e,loggerErrorMessage);
        }
    }

    @DeleteMapping(value = "/{id}")
    public void deleteHorse(@PathVariable Long id){
        LOGGER.info("DELETE " + BASE_URL + "/"+id);
        try{
            horseService.deleteHorse(id);
        }catch (Exception e){
            String loggerErrorMessage = "deleteHorse(): id="+id;
            throw exceptionHandler(e,loggerErrorMessage);
        }
    }

    @GetMapping(value = "/search")
    public List<HorseDto> searchHorses(@RequestParam(value = "name",required = false) String name,
                                       @RequestParam(value = "description",required = false) String description,
                                       @RequestParam(value = "birthdate", required = false) String birthdate,
                                       @RequestParam(value = "rating",required = false) Long rating,
                                       @RequestParam(value = "race",required = false) String race){

        HorseDto horseDto=new HorseDto(null,null,null,name,birthdate,description,rating,race,null,null);
        LOGGER.info("GET " + BASE_URL +"/search " +horseDto);
        try{
            List<HorseDto> result=horseMapper.listOfEntityToDto(horseService.searchHorses(horseMapper.dtoToEntity(horseDto)));
            return result;
        }catch (Exception e){
            String loggerErrorMessage = "searchHorses: "+horseDto;
            throw exceptionHandler(e,loggerErrorMessage);
        }
    }

    private ResponseStatusException exceptionHandler(Exception e, String loggerErrorMessage) {
        LOGGER.error(loggerErrorMessage,e);
        if(e instanceof ValidationException){
            return new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }else if(e instanceof NotFoundException){
            return  new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }else if(e instanceof DataProcessingException){
            return new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage(), e);
        }else{
            return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}
