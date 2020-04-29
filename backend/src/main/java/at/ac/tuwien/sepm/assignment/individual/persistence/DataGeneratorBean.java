package at.ac.tuwien.sepm.assignment.individual.persistence;

import java.lang.invoke.MethodHandles;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

/**
 * This component is only created, if the profile {@code datagen} is active
 * You can activate this profile by adding {@code -Dspring.profiles.active=datagen} to your maven command line
 */
@Configuration
@Profile("datagen")
public class DataGeneratorBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private DataSource source;

    public DataGeneratorBean(DataSource source) {
        this.source = source;
    }

    /**
     * Executed once when the component is instantiated. Inserts some dummy data.
     */
    @PostConstruct
    void insertDummyData() {
        try {
            ScriptUtils.executeSqlScript(source.getConnection(), new ClassPathResource("sql/insertData.sql"));
        } catch (Exception e) {
            LOGGER.error("Error inserting test data", e);
        }
    }
}
