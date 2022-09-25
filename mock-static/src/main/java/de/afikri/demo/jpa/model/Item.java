package de.afikri.demo.jpa.model;


import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


import java.time.LocalDate;

import static java.time.LocalDate.now;

@Entity
@Data
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE )
    Integer id;
    String name;
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    LocalDate createdDateStamp;
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    LocalDate updatedDateStamp;

    @PrePersist
    private void setCreateDateStamp(){
        this.createdDateStamp = now();
    }
    @PreUpdate
    private void setUpdatedDateStamp(){
        this.updatedDateStamp = now();
    }
}
