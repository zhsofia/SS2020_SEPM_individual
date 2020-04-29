package at.ac.tuwien.sepm.assignment.individual.unit.persistence;

import static org.junit.jupiter.api.Assertions.*;

import at.ac.tuwien.sepm.assignment.individual.entity.Owner;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundExceptionPersistence;
import at.ac.tuwien.sepm.assignment.individual.persistence.OwnerDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

public abstract class OwnerDaoTestBase {

    @Autowired
    OwnerDao ownerDao;

    @Test
    @DisplayName("Finding owner by non-existing ID should throw NotFoundException")
    public void findingOwnerById_nonExisting_shouldThrowNotFoundException() {
        assertThrows(NotFoundExceptionPersistence.class,
            () -> ownerDao.findOneById(-1L));
    }

    @Test
    @DisplayName("Inserting valid owner")
    public void insertingOwner_valid() {
        assertDoesNotThrow(() ->
            ownerDao.insertOne(new Owner(null, "Test", null, null)));
        try {
            assertEquals("Test", ownerDao.findOwnersByPartOfName(new Owner(null, "Test",
                null, null)).get(0).getName());
        }catch (NotFoundExceptionPersistence e){
            fail();
        }
    }

    @Test
    @DisplayName("Updating owner with invalid (empty) name")
    public void updateOwner_with_invalid_name() {
        assertThrows(DataAccessException.class,
            () -> ownerDao.updateOwner(new Owner(1L, null, null, null)));
    }

}
