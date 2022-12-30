package de.barsova.pet.chess;

import javax.enterprise.context.ApplicationScoped;

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
import de.barsova.pet.chess.entities.TournamentEntity;
import io.smallrye.mutiny.Uni;

@Path("/tournaments")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class TournamentEndpoint {

    @GET
    public Uni<List<TournamentEntity>> get() {
        return TournamentEntity.listAll(Sort.by("name"));
    }

     @POST
    public Uni<Response> create(TournamentEntity tournament) {
        if (tournament == null || tournament.id != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }
         System.out.println("why are we here?"+tournament.name+" "+tournament.date+tournament.location);

         return Panache.withTransaction(tournament::persist)
         .replaceWith(Response.ok(tournament).status(CREATED)::build);
    }
}
