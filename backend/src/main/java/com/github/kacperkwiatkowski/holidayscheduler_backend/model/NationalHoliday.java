package com.github.kacperkwiatkowski.holidayscheduler_backend.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "nationalHolidays_table", uniqueConstraints = {
        @UniqueConstraint(columnNames = "nationalHolidays_id")
})
public class NationalHoliday implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "nationalHolidays_id", unique = true, nullable = false)
    private int id;

    @Column(name = "nationalHolidays_name", nullable = false)
    private String name;

    @Column(name = "nationalHolidays_description", nullable = false)
    private String description;

    @Column(name = "nationalHolidays_date", nullable = false)
    private LocalDate holidayDate;

    public NationalHoliday() {
    }

    public NationalHoliday(String name, String description, LocalDate holidayDate) {
        this.name = name;
        this.description = description;
        this.holidayDate = holidayDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getHolidayDate() {
        return holidayDate;
    }

    public void setHolidayDate(LocalDate holidayDate) {
        this.holidayDate = holidayDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NationalHoliday that = (NationalHoliday) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(holidayDate, that.holidayDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, holidayDate);
    }

    @Override
    public String toString() {
        return "NationalHoliday{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", holidayDate=" + holidayDate +
                '}';
    }
}
