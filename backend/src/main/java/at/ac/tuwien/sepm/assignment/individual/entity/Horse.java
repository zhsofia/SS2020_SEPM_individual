package at.ac.tuwien.sepm.assignment.individual.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Horse extends BaseEntity {

    private String name;
    private String birth_date;
    private String description;
    private Long rating;
    private String race;
    private Long idofowner;
    private String imageBase64;

    public Horse(Long id, LocalDateTime createdAt, LocalDateTime updatedAt,
                 String name, String birth_date, String description, Long rating,
                 String rase, Long idofowner, String imageBase64){
        super(id, createdAt, updatedAt);
        this.name=name;
        this.birth_date=birth_date;
        this.description=description;
        this.rating=rating;
        this.race=rase;
        this.imageBase64=imageBase64;
        this.idofowner=idofowner;
    }

    public Horse(){}

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public Long getIdofowner() {
        return idofowner;
    }

    public void setIdofowner(Long idofowner) {
        this.idofowner = idofowner;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(),
            name, description, birth_date, rating, race, idofowner);
    }
    @Override
    public boolean equals(Object o){
        if (this==o) return true;
        if(!(o instanceof Horse)) return false;
        if(!super.equals(o)) return false;
        Horse horse = ((Horse) o);
        return this.rating==horse.rating && this.name.equals(horse.name) &&
            this.birth_date.equals(horse.birth_date) && this.description.equals(horse.description) &&
            this.race.equals(horse.race) && this.idofowner==horse.idofowner;
    }

    @Override
    public String toString() {
        return "Horse{" + super.fieldsString() + ", " + this.fieldsString() + "}";
    }

    public String fieldsString() {
        return " name=" + name +
            ", description=" + description +
            ", birth-date=" + birth_date +
            ", rating=" + rating +
            ", race=" + race +
            ", idofowner=" + idofowner;
    }
}
