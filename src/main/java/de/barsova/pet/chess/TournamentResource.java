package de.barsova.pet.chess;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;

import de.barsova.pet.chess.entities.Tournament;
import io.quarkus.runtime.StartupEvent;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.net.URI;

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

@POST
public Uni<Response> create(Tournament tournament) {
        return tournament.save(client)
            .onItem().transform(id -> URI.create("/tournaments/" + id))
            .onItem().transform(uri -> Response.created(uri).build());
    }
}