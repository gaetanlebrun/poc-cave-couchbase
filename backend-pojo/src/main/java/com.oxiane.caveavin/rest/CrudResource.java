package com.oxiane.caveavin.rest;

import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.Normalizer;
import java.util.List;


public abstract class CrudResource<V> extends AbstractResource<V> {

  // -------------------------- PRIVATE METHODS --------------------------

  protected String getNormalizedId(String... values) {
    return Normalizer.normalize(StringUtils.join(values, "::"), Normalizer.Form.NFD)
            .replaceAll("[^\\p{ASCII}]", "")
            .replaceAll(" ", "_")
            .toLowerCase();
  }

  // -------------------------- PUBLIC METHODS --------------------------

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public V create(V entity) {
    return getDao().create(getNormalizedId(entity), entity);
  }

  protected abstract String getNormalizedId(V entity);

  @DELETE
  @Path("{id}")
  @Produces(MediaType.APPLICATION_OCTET_STREAM)
  public Response delete(@PathParam("id") String id) {
    getDao().delete(id);
    return Response.status(200).build();
  }

  @GET
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public V find(@PathParam("id") String id) {
    return getDao().find(id);
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<V> findAll() {
    return getDao().findAll();
  }

  @PUT
  @Path("{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public V update(@PathParam("id") String id, V entity) {
    return getDao().update(id, entity);
  }
}
