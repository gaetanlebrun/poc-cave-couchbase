package com.oxiane.caveavin.rest;

import com.oxiane.caveavin.mapper.IDocMapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Path("rest/domaine")
public class DomaineResource extends CrudResource {
  // -------------------------- Méthodes privées --------------------------

  @Override
  protected String getNormalizedId(Map<String, Object> map) {
    return getNormalizedId(map.get("nom").toString());
  }

  @GET
  @Path("by_name")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Map<String, Object>> find(@QueryParam("startKey") String startKey, @QueryParam("endKey") String endKey) {
    return getDao().findAll("domaine_by_name", startKey, endKey, new IDocMapper() {
      @Override
      public Map<String, Object> toMap(String id, String doc) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("id", id);
        map.put("nom", doc);
        return map;
      }

      @Override
      public String toString(Map<String, Object> map) {
        return null;
      }
    });
  }
}
