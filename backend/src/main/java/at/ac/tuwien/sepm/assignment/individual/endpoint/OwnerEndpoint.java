package at.ac.tuwien.sepm.assignment.individual.endpoint;

import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.OwnerDto;
import at.ac.tuwien.sepm.assignment.individual.endpoint.mapper.OwnerMapper;
import at.ac.tuwien.sepm.assignment.individual.exception.DataProcessingException;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.service.OwnerService;
import java.lang.invoke.MethodHandles;
import java.util.List;

import at.ac.tuwien.sepm.assignment.individual.util.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(OwnerEndpoint.BASE_URL)
public class OwnerEndpoint {

    static final String BASE_URL = "/owners";
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final OwnerService ownerService;
    private final OwnerMapper ownerMapper;

    @Autowired
    public OwnerEndpoint(@Qualifier ("SimpleOwnerService") OwnerService ownerService, OwnerMapper ownerMapper) {
        this.ownerService = ownerService;
        this.ownerMapper = ownerMapper;
    }

    @GetMapping(value = "/{id}")
    public OwnerDto getOneById(@PathVariable("id") Long id) {
        LOGGER.info("GET " + BASE_URL + "/{}", id);
        try {
            return ownerMapper.entityToDto(ownerService.findOneById(id));
        } catch (Exception e) {
            String loggerErrorMessage = "getOneById(): "+id;
            throw exceptionHandler(e,loggerErrorMessage);
        }
    }

    @PostMapping(value="")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewOwner(@RequestBody OwnerDto ownerDto){
        LOGGER.info("POST " + BASE_URL + " " + ownerDto);
        try {
            ownerService.insertOne(ownerMapper.dtoToEntity(ownerDto));
        }catch (Exception e) {
            String loggerErrorMessage = "addNewOwner(): "+ownerDto;
            throw exceptionHandler(e,loggerErrorMessage);
        }
    }

    @PutMapping(value = "")
    public void updateOwner(@RequestBody OwnerDto ownerDto){
        LOGGER.info("PUT " + BASE_URL + " " + ownerDto);
        try{
            ownerService.updateOwner(ownerMapper.dtoToEntity(ownerDto));
        } catch (Exception e) {
            String loggerErrorMessage = "updateOwner(): "+ownerDto;
            throw exceptionHandler(e,loggerErrorMessage);
        }
    }

    @DeleteMapping(value = "/{id}")
    public void deleteOwner(@PathVariable Long id){
        LOGGER.info("DELETE "+BASE_URL+"/"+id);
        try{
            ownerService.deleteOwner(id);
        } catch (Exception e) {
            String loggerErrorMessage = "deleteOwner(): id="+id;
            throw exceptionHandler(e,loggerErrorMessage);
        }
    }

    @GetMapping(value = {"/search"})
    public List<OwnerDto> findOwnerByPartOfName(@RequestParam("name") String name){
        LOGGER.info("GET " + BASE_URL + '/'+name);
        OwnerDto ownerDto = new OwnerDto();
        ownerDto.setName(name);
        try{
            return ownerMapper.listOfEntityToDto(ownerService.findOwnerByPartOfName(ownerMapper.dtoToEntity(ownerDto)));
        }catch (Exception e) {
            String loggerErrorMessage = "findOwnerByPartOfName(): "+ownerDto;
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
