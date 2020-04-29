package at.ac.tuwien.sepm.assignment.individual.persistence;

import at.ac.tuwien.sepm.assignment.individual.entity.Owner;
import at.ac.tuwien.sepm.assignment.individual.exception.DataProcessingException;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundExceptionPersistence;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * DAO (Data Access Object) Interface
 * controls the access to the Owner table in the database
 */
public interface OwnerDao {

    /**
     * @param id of the owner to find.
     * @return the owner with the specified id.
     * @throws DataAccessException will be thrown if something goes wrong during the database access.
     * @throws NotFoundExceptionPersistence   will be thrown if the owner could not be found in the database.
     */
    Owner findOneById(Long id) throws NotFoundExceptionPersistence;

    /**
     * @param o owner to be added
     * @throws DataAccessException will be thrown if something goes wrong during the database access.
     */
    void insertOne(Owner o);

    /**
     * @param o owner to be updated
     * @throws DataAccessException will be thrown if something goes wrong during the database access.
     * @throws NotFoundExceptionPersistence   will be thrown if the owner could not be found in the database.
     */
    void updateOwner(Owner o) throws NotFoundExceptionPersistence;

    /**
     * @param id of owner to be deleted
     * @throws DataAccessException will be thrown if something goes wrong during the database access.
     * @throws NotFoundExceptionPersistence will be thrown if the owner could not be found in the database.
     */
    void deleteOwner(Long id) throws NotFoundExceptionPersistence;

    /**
     * @param o owner whose name is searched for
     * @return a List of Owners, whose name contain o's name
     * @throws DataAccessException will be thrown if something goes wrong during the database access.
     * @throws NotFoundExceptionPersistence   will be thrown if no such owner could be found in the database.
     */
    public List<Owner> findOwnersByPartOfName(Owner o) throws NotFoundExceptionPersistence;
}
