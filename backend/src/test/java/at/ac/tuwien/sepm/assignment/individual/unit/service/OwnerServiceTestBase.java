package at.ac.tuwien.sepm.assignment.individual.unit.service;

import at.ac.tuwien.sepm.assignment.individual.entity.Owner;
import at.ac.tuwien.sepm.assignment.individual.service.HorseService;
import at.ac.tuwien.sepm.assignment.individual.service.OwnerService;
import at.ac.tuwien.sepm.assignment.individual.util.ValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import static org.junit.jupiter.api.Assertions.*;

public abstract class OwnerServiceTestBase {

    @Autowired
    OwnerService ownerService;

    @Test
    @DisplayName("Updating valid owner")
    public void updateOwner_valid(){
        assertDoesNotThrow(() ->
            ownerService.updateOwner(new Owner(2L,"TestName",null,null)));
        assertEquals("TestName",ownerService.findOneById(2L).getName());
    }

    @Test
    @DisplayName("Deleting owner which owns horses.")
    public void deleteOwner_which_has_horses(){
        assertThrows(ValidationException.class,
            ()->ownerService.deleteOwner(6L));
    }
}
