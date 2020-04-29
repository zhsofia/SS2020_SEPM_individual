package at.ac.tuwien.sepm.assignment.individual.unit.service;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.service.HorseService;
import at.ac.tuwien.sepm.assignment.individual.util.ValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class HorseServiceTestBase {

    @Autowired
    HorseService horseService;

    @Test
    @DisplayName("Insert horse with empty name.")
    public void insertHorse_with_empty_name() {
        assertThrows(ValidationException.class,
            () -> horseService.insertHorse(new Horse(null, null, null, null,
                "2020-11-23", "description", 5L, "PAINT", 1L,null)));
    }

    @Test
    @DisplayName("Delete a horse.")
    public void deleteHorse() {
        assertDoesNotThrow(() -> horseService.deleteHorse(1L));
        assertThrows(NotFoundException.class,()->horseService.getHorse(1L));
    }


}
