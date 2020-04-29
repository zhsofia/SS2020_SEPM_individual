package at.ac.tuwien.sepm.assignment.individual.endpoint.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Objects;

public class OwnerDto extends BaseDto {

    private String name;

    public OwnerDto() {
    }

    public OwnerDto(String name) {
        this.name = name;
    }

    public OwnerDto(@JsonProperty ("id") Long id, @JsonProperty ("name") String name,
                    @JsonProperty ("createdat") LocalDateTime created,
                    @JsonProperty ("updatedat") LocalDateTime updated) {
        super(id, created, updated);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OwnerDto)) return false;
        if (!super.equals(o)) return false;
        OwnerDto ownerDto = (OwnerDto) o;
        return Objects.equals(name, ownerDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    @Override
    protected String fieldsString() {
        return super.fieldsString() + ", name='" + name + '\'';
    }

    @Override
    public String toString() {
        return "OwnerDto{ " + fieldsString() + " }";
    }
}
