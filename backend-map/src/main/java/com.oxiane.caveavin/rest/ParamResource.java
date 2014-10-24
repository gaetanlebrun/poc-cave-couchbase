package com.oxiane.caveavin.rest;

import com.oxiane.caveavin.dao.ICouchDAO;
import com.oxiane.caveavin.mapper.IDocMapper;
import com.oxiane.caveavin.mapper.impl.AbstractDocMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Path("rest/param")
public class ParamResource extends AbstractResource {
  // ------------------------------ FIELDS ------------------------------

  private final IDocMapper regionByNameMapper = new AbstractDocMapper() {
    @Override
    public Map<String, Object> toMap(String id, String doc) {
      Map<String, Object> map = new LinkedHashMap<String, Object>();
      map.put("id", id);
      map.put("nom", doc);
      return map;
    }
  };
  private IDocMapper regionMapper;
  private IDocMapper caracteristiqueVinMapper;
  private ICouchDAO dao;

  // --------------------- GETTER / SETTER METHODS ---------------------

  public void setCaracteristiqueVinMapper(IDocMapper caracteristiqueVinMapper) {
    this.caracteristiqueVinMapper = caracteristiqueVinMapper;
  }

  public void setRegionMapper(IDocMapper regionMapper) {
    this.regionMapper = regionMapper;
  }

  // -------------------------- PUBLIC METHODS --------------------------

  @GET
  @Path("{idRegion}/appellations")
  @Produces(MediaType.APPLICATION_JSON)
  public List<String> appellations(@PathParam("idRegion") String idRegion) {
    final Map<String, Object> map = dao.find(idRegion, regionMapper);
    //noinspection unchecked
    return map == null ? Collections.<String>emptyList() : (List<String>) map.get("appellations");
  }

  @GET
  @Path("regions/by_name")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Map<String, Object>> regionsByName(@QueryParam("startKey") String startKey, @QueryParam("endKey") String endKey) {
    return dao.findAll("region_by_name", startKey, endKey, regionByNameMapper);
  }

  @GET
  @Path("vin/caracteristiques")
  @Produces(MediaType.APPLICATION_JSON)
  public Map<String, Object> types() {
    return dao.find("param::vin::caracteristiques", caracteristiqueVinMapper);
  }

  public void setDao(ICouchDAO dao) {
    this.dao = dao;
  }
}
