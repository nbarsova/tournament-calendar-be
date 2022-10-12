package de.barsova.pet.chess.entities;

import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.pgclient.PgPool;

public class Tournament {
    public Long id;

    public String name;

    public Tournament() {
    }

    public Tournament(String name) {
        this.name = name;
    }

    public Tournament(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    private static Tournament from(Row row) {
        return new Tournament(row.getLong("id"), row.getString("name"));
    }

    public static Multi<Tournament> findAll(PgPool client) {
        return client.query("SELECT id, name FROM tournament ORDER BY name ASC").execute()
        .onItem().transformToMulti(set -> Multi.createFrom().iterable(set))
        .onItem().transform(Tournament::from);
    }
}