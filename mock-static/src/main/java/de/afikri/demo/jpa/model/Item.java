package de.afikri.demo.jpa.model;


import lombok.*;

import javax.persistence.*;



@Entity
@Data
@NoArgsConstructor
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE )
    Integer id;
    String name;
    public Item(String name) {
        this.name = name;
    }
    @Embedded
    @Setter(AccessLevel.NONE)
    private EntityMetaInfo entityMetaInfo = new EntityMetaInfo();


}
