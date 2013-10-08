package com.oxiane.caveavin.rest;

import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.Normalizer;
import java.util.List;
import java.util.Map;


public abstract class CrudResource extends AbstractResource {
  // -------------------------- Méthodes privées --------------------------

  protected String getNormalizedId(String... values) {
    return Normalizer.normalize(StringUtils.join(values, "::"), Normalizer.Form.NFD)
            .replaceAll("[^\\p{ASCII}]", "")
            .replaceAll(" ", "_")
            .toLowerCase();
  }

  // -------------------------- Méthodes publiques --------------------------

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Map<String, Object> create(Map<String, Object> map) {
    return getDao().create(getNormalizedId(map), map);
  }

  protected abstract String getNormalizedId(Map<String, Object> map);

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
  public Map<String, Object> find(@PathParam("id") String id) {
    return getDao().find(id);
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Map<String, Object>> findAll() {
    return getDao().findAll();
  }

  @PUT
  @Path("{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Map<String, Object> update(@PathParam("id") String id, Map<String, Object> entity) {
    return getDao().update(id, entity);
  }
}
