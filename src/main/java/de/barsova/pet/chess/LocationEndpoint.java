package de.barsova.pet.chess;

import de.barsova.pet.chess.entities.LocationEntity;
import java.util.List;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.enterprise.context.ApplicationScoped;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;

@Path("/locations")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class LocationEndpoint {

    @GET
    public Uni<List<LocationEntity>> get() {
        return LocationEntity.listAll(Sort.by("name"));
    }
}