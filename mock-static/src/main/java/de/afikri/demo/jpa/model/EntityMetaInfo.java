package de.afikri.demo.jpa.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDate;

import static java.time.LocalDate.now;

@Data
@Embeddable
public class EntityMetaInfo {
    @Setter(AccessLevel.NONE)
    LocalDate createdDateStamp;
    @Setter(AccessLevel.NONE)
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
