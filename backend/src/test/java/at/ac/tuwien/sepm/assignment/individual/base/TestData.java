package at.ac.tuwien.sepm.assignment.individual.base;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.entity.Owner;

public interface TestData {

    /**
     * URI Data
     */
    String BASE_URL = "http://localhost:";
    String HORSE_URL = "/horses";
    String OWNER_URL = "/owners";

    /**
     * Owner Data
     */
    static Owner getNewOwner() {
        return new Owner("Owner");
    }

    static Owner getNewOwner(String name) {
        return new Owner(name);
    }

    static Owner getNewOwnerWithId() {
        return new Owner(1L, "Owner");
    }

    /**
     * Horse Data
     */
    static Horse getNewHorse() {
        return new  Horse();
    }

}
