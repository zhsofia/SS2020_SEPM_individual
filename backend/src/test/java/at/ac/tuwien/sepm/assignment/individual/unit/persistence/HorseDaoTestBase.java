package at.ac.tuwien.sepm.assignment.individual.unit.persistence;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundExceptionPersistence;
import at.ac.tuwien.sepm.assignment.individual.persistence.HorseDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import javax.validation.ValidationException;

import static org.junit.jupiter.api.Assertions.*;

public abstract class HorseDaoTestBase {

    @Autowired
    HorseDao horseDao;

    @Test
    @DisplayName("Insert horse with invalid date.")
    public void insertHorse_with_invalid_date() {
        assertThrows(DataAccessException.class,
            () -> horseDao.insertHorse(new Horse(null, null, null, "TestHorse",
                "invalid date", "description", 5L, "PAINT", 1L,null)));
    }

    @Test
    @DisplayName("Update horse with empty description")
    public void updateHorse_with_empty_description(){
        Horse h = new Horse(2L,null,null,"TestHorse",
            "2020-01-02", null, 5L, "PAINT", 1L,null);
        assertDoesNotThrow(()->horseDao.updateHorse(h));
        try{
        assertNull(horseDao.searchHorses(h).get(0).getDescription());
        }catch (NotFoundExceptionPersistence e){
            fail();
        }
    }

}
