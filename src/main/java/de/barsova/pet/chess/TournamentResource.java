package de.barsova.pet.chess;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.GET;

import de.barsova.pet.chess.entities.Tournament;
import io.quarkus.runtime.StartupEvent;
import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/tournaments")
@ApplicationScoped
public class TournamentResource {

    @Inject
    io.vertx.mutiny.pgclient.PgPool client;

    @Inject
    @ConfigProperty(name = "tournaments.schema.create", defaultValue = "true")
    boolean schemaCreate;

    void config(@Observes StartupEvent ev) {
        if (schemaCreate) {
            initdb();
        }
    }

    private void initdb() {
        client.query("DROP TABLE IF EXISTS tournament").execute()
        .flatMap(r -> client.query("CREATE TABLE tournament (id SERIAL PRIMARY KEY, name TEXT NOT NULL)").execute())
        .flatMap(r -> client.query("INSERT INTO tournament (name) VALUES ('Test1')").execute())
        .flatMap(r -> client.query("INSERT INTO tournament (name) VALUES ('Test2')").execute())
        .flatMap(r -> client.query("INSERT INTO tournament (name) VALUES ('Test3')").execute())
        .await().indefinitely();
    }
    @GET
    public Multi<Tournament> get() {
        return Tournament.findAll(client);
    }

/*
    @POST
    public Uni<Response> create(TournamentEntity tournament) {
        return Panache.<Fruit>withTransaction(tournament::persist)
        .onItem().transform(inserted -> Response.created(URI.create("/tournaments/" + inserted.id)).build());
    }
    */
}