package at.ac.tuwien.sepm.assignment.individual.endpoint.dto;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Objects;

public class HorseDto extends BaseDto {
    private String name;
    private String birthdate;
    private String description;
    private Long rating;
    private String race;
    private Long idofowner;
    private String imageBase64;

    public HorseDto(){}

    public HorseDto(@JsonProperty ("id") Long id,@JsonProperty ("createdat") LocalDateTime createdat,
                    @JsonProperty ("updatedat") LocalDateTime updatedat,
                    @JsonProperty ("name") String name,
                    @JsonProperty ("birthdate") String  birthdate,@JsonProperty ("description") String description,
                    @JsonProperty ("rating") Long rating, @JsonProperty ("race") String race,
                    @JsonProperty ("idofowner") Long idofowner,
                    @JsonProperty ("imageBase64") String imageBase64) {
        super(id, createdat, updatedat);
        this.name = name;
        this.birthdate = birthdate;
        this.description = description;
        this.rating = rating;
        this.race=race;
        this.idofowner=idofowner;
        this.imageBase64=imageBase64;
    }

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

    public void setRace(String rase) {
        this.race = rase;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
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
            name, description, birthdate, rating);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HorseDto)) return false;
        if (!super.equals(o)) return false;
        HorseDto horse = ((HorseDto) o);
        return this.rating == horse.rating && this.name.equals(horse.name) &&
            this.birthdate.equals(horse.birthdate) && this.description.equals(horse.description);
    }

    @Override
    public String toString() {
        return "HorseDto{" + super.fieldsString() + ", " + this.fieldsString() + "}";
    }

    public String fieldsString() {
        String image="";
        if(this.imageBase64!=null) image =", image";
        return " name=" + name +
            ", description=" + description +
            ", birth-date=" + birthdate +
            ", rating=" + rating +
            ", race=" + race +
            ", idofowner=" + idofowner + image;
    }

}
