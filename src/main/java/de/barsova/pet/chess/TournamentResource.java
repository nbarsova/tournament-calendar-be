package de.barsova.pet.chess;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import java.util.List;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import io.quarkus.hibernate.reactive.panache.Panache;
import javax.ws.rs.core.Response;
import javax.ws.rs.WebApplicationException;
import static javax.ws.rs.core.Response.Status.CREATED;

import io.quarkus.panache.common.Sort;
import de.barsova.pet.chess.entities.Tournament;
import io.smallrye.mutiny.Uni;

@Path("/tournaments")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class TournamentResource {

    @GET
    public Uni<List<Tournament>> get() {
        return Tournament.listAll(Sort.by("name"));
    }

     @POST
    public Uni<Response> create(Tournament tournament) {
        if (tournament == null || tournament.id != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }

         return Panache.withTransaction(tournament::persist)
         .replaceWith(Response.ok(tournament).status(CREATED)::build);
    }
}
