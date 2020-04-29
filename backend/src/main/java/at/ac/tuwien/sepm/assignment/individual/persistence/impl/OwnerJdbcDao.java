package at.ac.tuwien.sepm.assignment.individual.persistence.impl;

import at.ac.tuwien.sepm.assignment.individual.entity.Owner;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundExceptionPersistence;
import at.ac.tuwien.sepm.assignment.individual.persistence.OwnerDao;
import java.lang.invoke.MethodHandles;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("OwnerJdbcDao")
public class OwnerJdbcDao implements OwnerDao {

    private static final String TABLE_NAME = "Owner";
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public OwnerJdbcDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Owner findOneById(Long id)  throws NotFoundExceptionPersistence {
        LOGGER.trace("Get owner with id {}", id);
        final String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id=?";
        List<Owner> owners = jdbcTemplate.query(sql, new Object[] { id }, this::mapRow);

        if (owners.isEmpty()){
            throw new NotFoundExceptionPersistence("Could not find owner with id " + id);
        }
        LOGGER.debug("Owner found!");
        return owners.get(0);
    }

    @Override
    public List<Owner> findOwnersByPartOfName(Owner o) throws NotFoundExceptionPersistence{
        LOGGER.trace("FindOwnerByPartOfName({})",o.getName());
        final String sql = "SELECT * FROM " + TABLE_NAME + " WHERE name LIKE (CONCAT('%',?,'%'))";
        List<Owner> owners = jdbcTemplate.query(sql,new Object[] {o.getName()}, this::mapRow);

        if (owners.isEmpty()){
            throw new NotFoundExceptionPersistence("Could not find any owners.");
        }
        LOGGER.debug("Owners found!");
        return owners;

    }

    @Override
    public void updateOwner(Owner o) throws NotFoundExceptionPersistence{
        LOGGER.trace("Update Owner with id {}", o.getId());
        String sql = "UPDATE owner SET name=? , updated_at=CURRENT_TIMESTAMP() WHERE id=?";
        int i = jdbcTemplate.update(sql,o.getName(),o.getId());

        if(i==0){
            throw new NotFoundExceptionPersistence("Owner with this id does not exist!");
        }
        LOGGER.debug("Owner successfully updated!");
    }

    @Override
    public void insertOne(Owner o) {
        LOGGER.trace("Insert owner "+ o);
        final String sql = "INSERT INTO " + TABLE_NAME +" (ID, NAME, CREATED_AT, UPDATED_AT)"+ " VALUES " +
            "(?,?, CURRENT_TIMESTAMP(),  CURRENT_TIMESTAMP()"+")";
        jdbcTemplate.update(sql,o.getId(),o.getName());
        LOGGER.debug("Owner inserted!");
    }

    @Override
    public void deleteOwner(Long id) throws NotFoundExceptionPersistence{
        LOGGER.trace("Delete owner with id " + id);
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE id=?";
        int i = jdbcTemplate.update(sql,id);
        if(i==0){
            throw new NotFoundExceptionPersistence("Owner with this id does not exist!");
        }
        LOGGER.debug("Owner deleted!");
    }

    private Owner mapRow(ResultSet resultSet, int i) throws SQLException {
        final Owner owner = new Owner();
        owner.setId(resultSet.getLong("id"));
        owner.setName(resultSet.getString("name"));
        owner.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
        owner.setUpdatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime());
        return owner;
    }

}
