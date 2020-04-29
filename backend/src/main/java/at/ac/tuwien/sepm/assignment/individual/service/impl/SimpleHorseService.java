package at.ac.tuwien.sepm.assignment.individual.service.impl;

import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.HorseDto;
import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.exception.DataProcessingException;
import at.ac.tuwien.sepm.assignment.individual.exception.DataProcessingExceptionPersistence;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundExceptionPersistence;
import at.ac.tuwien.sepm.assignment.individual.persistence.HorseDao;
import at.ac.tuwien.sepm.assignment.individual.persistence.OwnerDao;
import at.ac.tuwien.sepm.assignment.individual.service.HorseService;
import at.ac.tuwien.sepm.assignment.individual.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.lang.invoke.MethodHandles;
import java.util.List;

@Service("SimpleHorseService")
public class SimpleHorseService implements HorseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final HorseDao horseDao;
    private final Validator validator;

    @Autowired
    public SimpleHorseService(@Qualifier("HorseJdbcDao") HorseDao horseDao, Validator validator) {
        this.horseDao = horseDao;
        this.validator = validator;
    }

    @Override
    public void insertHorse(Horse horse) {
        LOGGER.trace("insertHorse()");
        validator.validateNewHorse(horse);
        LOGGER.debug("Validation successful!");
        try {
            horseDao.insertHorse(horse);
        } catch (DataAccessException e) {
            throw handleDataAccessException(e);
        } catch (DataProcessingExceptionPersistence e){
            throw handleDataProcessingExceptionPersistence(e);
        }
    }

    @Override
    public Horse getHorse(Long id) {
        LOGGER.trace("getHorse({})", id);
        try {
            return horseDao.findHorseById(id);
        } catch (DataAccessException e) {
            throw  handleDataAccessException(e);
        }catch (NotFoundExceptionPersistence e){
            throw handleNotFoundExceptionPersistence(e);
        }
    }

    @Override
    public void updateHorse(Horse h) {
        LOGGER.trace("updateHorse({})", h);
        validator.validateUpdateHorse(h);
        LOGGER.debug("Validation successful!");
        try {
            horseDao.updateHorse(h);
        } catch (DataAccessException e) {
            throw  handleDataAccessException(e);
        }catch (NotFoundExceptionPersistence e){
            throw handleNotFoundExceptionPersistence(e);
        }catch (DataProcessingExceptionPersistence e){
            throw handleDataProcessingExceptionPersistence(e);
        }
    }

    @Override
    public void deleteHorse(Long id){
        LOGGER.trace("deleteHorse({})",id);
        try{
            horseDao.deleteHorse(id);
        } catch (DataAccessException e){
            throw  handleDataAccessException(e);
        }catch (NotFoundExceptionPersistence e){
            throw handleNotFoundExceptionPersistence(e);
        }
    }

    @Override
    public List<Horse> searchHorses(Horse h){
        LOGGER.trace("searchHorses({})",h);
        validator.validateHorsesSearch(h);
        LOGGER.debug("Validation successful!");
        try{
            return horseDao.searchHorses(h);
        }catch (DataAccessException e) {
            throw handleDataAccessException(e);
        }catch (NotFoundExceptionPersistence e){
            throw handleNotFoundExceptionPersistence(e);
        }
    }

    private DataProcessingException handleDataAccessException(DataAccessException e){
        return new DataProcessingException(e.getMessage());
    }

    private NotFoundException handleNotFoundExceptionPersistence(NotFoundExceptionPersistence e){
        return new NotFoundException(e.getMessage());
    }

    private DataProcessingException handleDataProcessingExceptionPersistence(DataProcessingExceptionPersistence e){
        return new DataProcessingException(e.getMessage());
    }

}
