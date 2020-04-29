package at.ac.tuwien.sepm.assignment.individual.service;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.util.ValidationException;
import at.ac.tuwien.sepm.assignment.individual.exception.DataProcessingException;

import java.util.List;

/**
 * Service Layer for Horses
 */
public interface HorseService {

    /**
     * @brief validates and sends the horse to the persistence level
     * @param horse the horse to validate and insert
     * @throws ValidationException if entity is invalid
     * @throws DataProcessingException  will be thrown if something goes wrong during data processing.
     * */
    void insertHorse(Horse horse) throws ValidationException;

    /**
     * @param id of horse to find
     * @throws NotFoundException if no horse with this id exists in the database
     * @throws DataProcessingException  will be thrown if something goes wrong during data processing.
     */
    Horse getHorse(Long id);

    /**
     * @param h horse to be updated
     * @throws NotFoundException if horse does not exist in the database
     * @throws DataProcessingException  will be thrown if something goes wrong during data processing.
     * @throws ValidationException if entity is invalid
     */
    void updateHorse(Horse h);

    /**
     * @param id of horse to delete
     * @throws NotFoundException if no horse with this id exists in the database
     * @throws DataProcessingException  will be thrown if something goes wrong during data processing.
     */
    void deleteHorse(Long id);


    /**
     * @param h horse with search parameters
     * @throws NotFoundException if no horse is found
     * @throws
     */
    List<Horse> searchHorses(Horse h);
}
