package at.ac.tuwien.sepm.assignment.individual.persistence;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundExceptionPersistence;
import org.springframework.dao.DataAccessException;
import at.ac.tuwien.sepm.assignment.individual.exception.DataProcessingExceptionPersistence;

import java.util.List;

/**
 * DAO (Data Access Object) Interface
 * controls the access to the Horse table in the database
 */
public interface HorseDao {

    /**
     * @param id of the horse to find.
     * @return the horse with the specified id.
     * @throws DataAccessException will be thrown if something goes wrong during the database access.
     * @throws NotFoundExceptionPersistence will be thrown if the horse could not be found in the database.
     */
    Horse findHorseById(Long id) throws NotFoundExceptionPersistence;

    /**
     * @param h horse to be added
     * @throws DataAccessException will be thrown if something goes wrong during the database access.
     * @throws DataProcessingExceptionPersistence if there is a problem with the image
     */
    void insertHorse(Horse h);

    /**
     * @param idofowner whose horses we search for
     * @throws DataAccessException will be thrown if something goes wrong during the database access.
     * @throws NotFoundExceptionPersistence will be thrown if no horses of the owner could be found in the database.
     */
    List<Horse> findHorseOfOwner(Long idofowner) throws  NotFoundExceptionPersistence;

    /**
     * @param h horse to be updated
     * @throws DataAccessException will be thrown if something goes wrong during the database access.
     * @throws NotFoundExceptionPersistence will be thrown if no horse could not be found in the database.
     * @throws DataProcessingExceptionPersistence if there is a problem with the processing of the image
     */
    void updateHorse(Horse h) throws  NotFoundExceptionPersistence;

    /**
     * @param id of horse to be deleted
     * @throws DataAccessException will be thrown if something goes wrong during the database access.
     * @throws NotFoundExceptionPersistence will be thrown if horse could not be found in the database.
     */
    public void deleteHorse(Long id) throws  NotFoundExceptionPersistence;

    /**
     * @param h horse contains search parameters
     * @return list of found horses
     * @throws DataAccessException will be thrown if something goes wrong during the database access.
     * @throws NotFoundExceptionPersistence will be thrown if no horse could be found in the database.
     */
    public List<Horse> searchHorses(Horse h) throws  NotFoundExceptionPersistence;
}
