package de.barsova.pet.chess.entities;

import java.util.Date;
import javax.persistence.*;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;


@Entity
@Cacheable
public class TournamentEntity extends PanacheEntity {


    @Column(length = 40, unique = true)
    public String name;

    @Column(length = 200)
    public String description;

    @Column
    public Date date;

    @ManyToOne
    @JoinColumn(name = "locationId")
    public LocationEntity location;
}