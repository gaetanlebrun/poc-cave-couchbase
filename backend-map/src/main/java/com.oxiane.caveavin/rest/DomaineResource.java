package com.oxiane.caveavin.rest;

import com.oxiane.caveavin.dao.ICouchDAO;
import com.oxiane.caveavin.mapper.impl.AbstractDocMapper;

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
  // ------------------------------ FIELDS ------------------------------

  // -------------------------- Méthodes privées --------------------------
  private ICouchDAO dao;

  // --------------------- GETTER / SETTER METHODS ---------------------

  public void setDao(ICouchDAO dao) {
    this.dao = dao;
  }

  // -------------------------- PRIVATE METHODS --------------------------

  @Override
  protected String getNormalizedId(Map<String, Object> map) {
    return getNormalizedId(map.get("nom").toString());
  }

  // -------------------------- PUBLIC METHODS --------------------------

  @GET
  @Path("by_name")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Map<String, Object>> find(@QueryParam("startKey") String startKey, @QueryParam("endKey") String endKey) {
    return dao.findAll("domaine_by_name", startKey, endKey, new AbstractDocMapper() {
      @Override
      public Map<String, Object> toMap(String id, String doc) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("id", id);
        map.put("nom", doc);
        return map;
      }
    });
  }
}
