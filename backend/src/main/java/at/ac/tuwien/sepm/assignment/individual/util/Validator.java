package at.ac.tuwien.sepm.assignment.individual.util;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.entity.Owner;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundExceptionPersistence;
import at.ac.tuwien.sepm.assignment.individual.persistence.HorseDao;
import at.ac.tuwien.sepm.assignment.individual.persistence.OwnerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class Validator {
    private HorseDao horseDao;
    private OwnerDao ownerDao;
    private String dateFormat = "yyyy-MM-dd";

    @Autowired
    public Validator(@Qualifier("HorseJdbcDao") HorseDao horseDao, @Qualifier("OwnerJdbcDao") OwnerDao ownerDao) {
        this.horseDao = horseDao;
        this.ownerDao = ownerDao;
    }

    public void validateUpdateHorse(Horse h) {
        validateRequiredFieldsHorse(h);
        validateRating(h.getRating());
        validateRace(h.getRace());
        if (h.getIdofowner() != null) {
            validateOwnersId(h.getIdofowner());
        }
        validateDate(h.getBirth_date());
        validateImage(h.getImageBase64());
    }

    public void validateNewOwner(Owner owner) throws ValidationException {
        validateOwnersName(owner.getName());
    }

    public void validateUpdateOwner(Owner owner) throws ValidationException {
        if(owner.getName()==null){
            throw new ValidationException("Owners name cannot be empty!");
        }
    }

    public void validateNewHorse(Horse h) throws ValidationException {
        validateRequiredFieldsHorse(h);
        validateRating(h.getRating());
        validateRace(h.getRace());
        if (h.getIdofowner() != null) {
            validateOwnersId(h.getIdofowner());
        }
        validateDate(h.getBirth_date());
        validateImage(h.getImageBase64());
    }

    public void validateDeleteOwner(Long id) {
        List<Horse> horses = new ArrayList<>();
        try {
            horses = horseDao.findHorseOfOwner(id);
        } catch (NotFoundExceptionPersistence e) {
        }

        if (!horses.isEmpty()) {
            throw new ValidationException("Owner owns horses and cannot be deleted!");
        }

    }

    public void validateHorsesSearch(Horse h){
        if(h.getName()==null && h.getBirth_date()==null && h.getRace()==null
            && h.getRating()==null && h.getDescription()==null){
            throw new ValidationException("At least one field has to be filled!");
        }
        if(h.getBirth_date()!=null){
            validateDate(h.getBirth_date());
        }
    }

    private void validateDate(String date){
        DateFormat format=new SimpleDateFormat(this.dateFormat);
        format.setLenient(false);
        try {
            Date d = format.parse(date);
            Date now = new Date();
            if (d.after(now)){
                throw new ValidationException("Future date invalid!");
            }
        } catch (ParseException e) {
            throw new ValidationException("Invalid date!");
        }
    }

    private void validateRequiredFieldsHorse(Horse h){
        if (h.getName() == null || h.getBirth_date() == null || h.getRace() == null || h.getRating() == null ||
            h.getName().equals("") || h.getBirth_date().equals("") || h.getRace().equals("")) {
            throw new ValidationException("A required field is empty!");
        }
    }

    private void validateRace(String race){
        if (!race.equals("ARABIAN") && !race.equals("MORGAN") && !race.equals("PAINT") && !race.equals("APPALOOSA")) {
            throw new ValidationException("Invalid race value!");
        }
    }

    private void validateRating(Long rating){
        if (rating > 5 || rating < 1) {
            throw new ValidationException("Rating value is invalid!");
        }
    }

    private void validateOwnersId(Long ownersId){
        try {
            ownerDao.findOneById(ownersId);
        } catch (NotFoundExceptionPersistence e) {
            throw new ValidationException("Owner with such an id does not exist!");
        }
    }

    private void validateOwnersName(String name){
        if (name == null || name.equals(""))
            throw new ValidationException("Owners name cannot be empty!");
    }

    private void validateImage(String image){
        try{
            Base64.getDecoder().decode(image);
        }catch (IllegalArgumentException e){
            throw new ValidationException("Not a valid image!");
        }
    }
}
