package at.ac.tuwien.sepm.assignment.individual.service;

import at.ac.tuwien.sepm.assignment.individual.entity.Owner;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.util.ValidationException;
import at.ac.tuwien.sepm.assignment.individual.exception.DataProcessingException;

import java.util.List;

/**
 * Service Layer for Owners
 */
public interface OwnerService {


    /**
     * @param id of the owner to find.
     * @return the owner with the specified id.
     * @throws DataProcessingException  will be thrown if something goes wrong during data processing.
     * @throws NotFoundException will be thrown if the owner could not be found in the system.
     */
    Owner findOneById(Long id);

    /**
     * @param o owner to be added
     * @throws ValidationException if something is wrong with the entity
     * @throws DataProcessingException  will be thrown if something goes wrong during data processing.
     * */
    void insertOne(Owner o);

    /**
     * @param o owner to be added
     * @throws ValidationException if something is wrong with the entity
     * @throws DataProcessingException  will be thrown if something goes wrong during data processing.
     * @throws NotFoundException will be thrown if the owner could not be found in the system.
     * */
     void updateOwner(Owner o);

     /**
      * @param id of owner to be deleted
      * @throws DataProcessingException  will be thrown if something goes wrong during data processing.
      * @throws NotFoundException will be thrown if the owner could not be found in the system.
      * */
     void deleteOwner(Long id);

    /**
     * @param o owner whose name is searched after
     * @throws DataProcessingException  will be thrown if something goes wrong during data processing.
     * @throws NotFoundException will be thrown if no such owner could be found in the system.
     * */
     List<Owner> findOwnerByPartOfName(Owner o);
}
