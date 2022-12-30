package de.barsova.pet.chess.entities;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import de.barsova.pet.chess.enums.County;
import javax.persistence.Id;
import io.quarkus.hibernate.reactive.panache.PanacheEntity;

@Entity
@Cacheable
public class LocationEntity extends PanacheEntity {

    @Column(length = 100)
    public String name;

    @Column
    public float lat;

    @Column
    public float lng;

    @Column
    public County county;

}