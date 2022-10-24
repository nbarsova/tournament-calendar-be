package de.barsova.pet.chess.entities;

import java.util.Date;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
@Entity
@Cacheable
public class Tournament extends PanacheEntity {

    @Column(length = 40, unique = true)
    public String name;

    @Column(length = 200)
    public String description;

    @Column
    public Date date;

    @Column(length = 40)
    public String location;

}