package at.ac.tuwien.sepm.assignment.individual.persistence.impl;

import at.ac.tuwien.sepm.assignment.individual.exception.DataProcessingExceptionPersistence;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundExceptionPersistence;
import at.ac.tuwien.sepm.assignment.individual.persistence.HorseDao;
import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.rowset.serial.SerialBlob;
import java.lang.invoke.MethodHandles;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Base64;
import java.util.List;

@Repository("HorseJdbcDao")
public class HorseJdbcDao implements HorseDao {

    private static final String TABLE_NAME = "Horse";
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public HorseJdbcDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void insertHorse(Horse h) {
        LOGGER.trace("insertHorse()");
        Blob blobImg = blobMaker(h.getImageBase64());
        String prepSql = "INSERT INTO " + TABLE_NAME + " (id, name, description, race, rating, birth_date, created_at, updated_at,id_of_owner,image) " +
            "VALUES (?,?,?,?,?,?,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),?,?)";
        jdbcTemplate.update(prepSql, h.getId(), h.getName(), h.getDescription(), h.getRace(),
            h.getRating(), h.getBirth_date(), h.getIdofowner(), blobImg);
        LOGGER.debug("Horse successfully inserted into database!");
    }

    @Override
    public Horse findHorseById(Long id) throws NotFoundExceptionPersistence {
        LOGGER.trace("findHorseById({})", id);
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id=?";
        List<Horse> horses = jdbcTemplate.query(sql, new Object[]{id}, this::mapRow);
        if (horses.isEmpty()) {
            throw  new NotFoundExceptionPersistence("Horse not found.");
        }
        LOGGER.debug("Horse found!");
        return horses.get(0);
    }

    @Override
    public List<Horse> findHorseOfOwner(Long idofowner) throws NotFoundExceptionPersistence {
        LOGGER.trace("findHorseById({})", idofowner);
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id_of_owner=?";
        List<Horse> horses = jdbcTemplate.query(sql, new Object[]{idofowner}, this::mapRow);
        if (horses.isEmpty()) {
            throw  new NotFoundExceptionPersistence("This owner does not have any horses.");
        }
        LOGGER.debug("Horses of owner {} found!",idofowner);
        return horses;
    }

    public List<Horse> searchHorses(Horse h) throws NotFoundExceptionPersistence {
        LOGGER.trace("searchHorse({})", h);
        String prepSql = " SELECT * FROM " + TABLE_NAME + " WHERE ";
        Object[] arg = new Object[5];
        int[] types = new int[5];
        boolean andQualifier = false;
        if (h.getName() != null) {
            andQualifier = true;
            prepSql = prepSql + "LOWER(name) LIKE LOWER(CONCAT('%',?,'%')) ";
            arg[0] = h.getName();
            types[0] = Types.VARCHAR;
        }
        if (h.getDescription() != null) {
            if (andQualifier) prepSql = prepSql + " and ";
            andQualifier = true;
            prepSql = prepSql + "LOWER(description) LIKE LOWER(CONCAT('%',?,'%')) ";
            arg[1] = h.getDescription();
            types[1] = Types.VARCHAR;
        }
        if (h.getRating() != null) {
            if (andQualifier) prepSql = prepSql + " and ";
            andQualifier = true;
            prepSql = prepSql + "rating=? ";
            arg[2] = h.getRating();
            types[2] = Types.INTEGER;
        }
        if (h.getBirth_date() != null) {
            if (andQualifier) prepSql = prepSql + " and ";
            andQualifier = true;
            prepSql = prepSql + "birth_date<=? ";
            arg[3] = h.getBirth_date();
            types[3] = Types.DATE;
        }
        if (h.getRace() != null) {
            if (andQualifier) prepSql = prepSql + " and ";
            prepSql = prepSql + "race=? ";
            arg[4] = h.getRace();
            types[4] = Types.VARCHAR;
        }

        int count = 0;
        for (int i = 0; i < arg.length; i++) {
            if (arg[i] != null) {
                count++;
            }
        }
        Object[] rightArgs = new Object[count];
        int[] rightTypes = new int[count];
        int j = 0;
        for (int i = 0; i < arg.length; i++) {
            if (arg[i] != null) {
                rightArgs[j] = arg[i];
                rightTypes[j] = types[i];
                j++;
            }
        }
        List<Horse> horses = jdbcTemplate.query(prepSql, rightArgs, rightTypes, this::mapRow);
        if (horses.isEmpty()) {
            throw  new NotFoundExceptionPersistence("No such horses could be found!");
        }
        LOGGER.debug("Horses found!");
        return horses;
    }

    @Override
    public void deleteHorse(Long id) throws NotFoundExceptionPersistence {
        LOGGER.trace("Update horse with id {}", id);
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE id=?";
        int i = jdbcTemplate.update(sql, id);
        if (i == 0) {
            throw new NotFoundExceptionPersistence("Horse with this id does not exist.");
        }
        LOGGER.debug("Horse deleted!");
    }

    @Override
    public void updateHorse(Horse h) throws NotFoundExceptionPersistence {
        LOGGER.trace("Update horse with id {}", h.getId());
        String sql = "UPDATE " + TABLE_NAME + " SET updated_at=CURRENT_TIMESTAMP()";

        sql = sql + ", description=?";
        sql = sql + ", race=?";
        sql = sql + ", birth_date=?";
        sql = sql + ", name=?";
        sql = sql + ", rating=?";
        sql = sql + ", id_of_owner=?";
        sql = sql + ", image=?";

        sql = sql + " WHERE id=?";

        Blob blobImg=blobMaker(h.getImageBase64());

        int i = this.jdbcTemplate.update(sql, h.getDescription(), h.getRace(), h.getBirth_date(),
            h.getName(), h.getRating(), h.getIdofowner(),blobImg, h.getId());

        if (i == 0) {
            throw  new NotFoundExceptionPersistence("Horse with this id does not exist.");
        }
        LOGGER.debug("Horse was successfully updated!");
    }


    private Horse mapRow(ResultSet resultSet, int i) throws SQLException {
        Horse h = new Horse();
        h.setId(resultSet.getLong("id"));
        h.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
        h.setUpdatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime());
        h.setName(resultSet.getString("name"));
        h.setDescription(resultSet.getString("description"));
        h.setBirth_date(resultSet.getString("birth_date"));
        h.setRating(resultSet.getLong("rating"));
        h.setRace(resultSet.getString("race"));
        h.setIdofowner(resultSet.getLong("id_of_owner"));
        Blob blob = resultSet.getBlob("image");
        if (blob != null) {
            byte[] bytes = resultSet.getBlob("image").getBytes(1, (int) blob.length());
            h.setImageBase64(Base64.getEncoder().encodeToString(bytes));
        }
        return h;
    }

    private Blob blobMaker(String imageBase64){
        Blob blobImg = null;
        if (imageBase64 != null) {
            byte[] img = Base64.getDecoder().decode(imageBase64);
            try {
                blobImg = new SerialBlob(img);
            } catch (SQLException e) {
                throw new DataProcessingExceptionPersistence("Image BLOB could not be constructed!");
            }
        } else {
            blobImg = null;
        }
        return blobImg;
    }
}
