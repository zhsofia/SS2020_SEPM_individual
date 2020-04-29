package at.ac.tuwien.sepm.assignment.individual.service.impl;

import at.ac.tuwien.sepm.assignment.individual.entity.Owner;
import at.ac.tuwien.sepm.assignment.individual.exception.DataProcessingException;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundExceptionPersistence;
import at.ac.tuwien.sepm.assignment.individual.persistence.OwnerDao;
import at.ac.tuwien.sepm.assignment.individual.service.OwnerService;
import at.ac.tuwien.sepm.assignment.individual.util.Validator;

import java.lang.invoke.MethodHandles;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service("SimpleOwnerService")
public class SimpleOwnerService implements OwnerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final OwnerDao ownerDao;
    private final Validator validator;

    @Autowired
    public SimpleOwnerService(@Qualifier("OwnerJdbcDao") OwnerDao ownerDao, Validator validator) {
        this.ownerDao = ownerDao;
        this.validator = validator;
    }

    @Override
    public Owner findOneById(Long id) {
        LOGGER.trace("findOneById({})", id);
        try{
            return ownerDao.findOneById(id);
        }catch (DataAccessException e){
            throw handleDataAccessException(e);
        }catch (NotFoundExceptionPersistence e){
            throw handleNotFoundExceptionPersistense(e);
        }
    }

    @Override
    public void insertOne(Owner o) {
        LOGGER.trace("insertOne()");
        validator.validateNewOwner(o);
        LOGGER.debug("Validation successful!");
        try{
            ownerDao.insertOne(o);
        }catch (DataAccessException e){
            throw handleDataAccessException(e);
        }
    }

    @Override
    public void updateOwner(Owner o) {
        LOGGER.trace("updateOwner({})",o);
        validator.validateUpdateOwner(o);
        LOGGER.debug("Validation successful!");
        try{
            this.ownerDao.updateOwner(o);
        }catch (DataAccessException e){
           throw handleDataAccessException(e);
        }catch (NotFoundExceptionPersistence e){
            throw handleNotFoundExceptionPersistense(e);
        }
    }

    @Override
    public void deleteOwner(Long id){
        LOGGER.trace("deleteOwner({})",id);
        try{
            validator.validateDeleteOwner(id);
            LOGGER.debug("Validation successful!");
            this.ownerDao.deleteOwner(id);
        }catch (DataAccessException e){
            throw  handleDataAccessException(e);
        }catch (NotFoundExceptionPersistence e){
            throw handleNotFoundExceptionPersistense(e);
        }
    }

    @Override
    public List<Owner> findOwnerByPartOfName(Owner o){
        LOGGER.trace("findOwnerByPartOfName({})",o);
        try{
           return this.ownerDao.findOwnersByPartOfName(o);
        }catch (DataAccessException e){
            throw handleDataAccessException(e);
        }catch (NotFoundExceptionPersistence e){
            throw handleNotFoundExceptionPersistense(e);
        }
    }

    private DataProcessingException handleDataAccessException(DataAccessException e){
        return new DataProcessingException(e.getMessage());
    }

    private NotFoundException handleNotFoundExceptionPersistense(NotFoundExceptionPersistence e){
        return new NotFoundException(e.getMessage());
    }

}
