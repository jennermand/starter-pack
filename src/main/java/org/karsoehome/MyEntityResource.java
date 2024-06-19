package org.karsoehome;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public interface MyEntityResource extends PanacheEntityResource<MyEntity, Long> {
    @GET
    @Path("/my-entity/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    default MyEntity getMyEntity(@PathParam("id") Long id) {


        MyEntity entity = MyEntity.findById(id);

        return entity;
        //return
    }

    @POST
    @Path("/my-entity")
    @Consumes(MediaType.APPLICATION_JSON)
    default Response addEntity(MyEntity entity) {
        if (entity == null || entity.field == null || entity.field.trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Field cannot be null or empty").build();
        }

        entity.persist();
        return Response.status(Response.Status.CREATED).entity("Entity added successfully").build();
    }

}