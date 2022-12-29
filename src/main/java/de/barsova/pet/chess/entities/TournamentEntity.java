package de.barsova.pet.chess.entities;

import java.util.Date;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;

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

    @ManyToOne (fetch = FetchType.LAZY)
    public LocationEntity location;
}