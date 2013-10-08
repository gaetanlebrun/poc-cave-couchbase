package com.oxiane.caveavin.rest;

import com.oxiane.caveavin.bean.Domaine;
import com.oxiane.caveavin.mapper.IDocMapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("rest/domaine")
public class DomaineResource extends CrudResource<Domaine> {
  // -------------------------- Méthodes privées --------------------------

  @Override
  protected String getNormalizedId(Domaine entity) {
    return getNormalizedId(entity.getNom());
  }

  @GET
  @Path("by_name")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Domaine> find(@QueryParam("startKey") String startKey, @QueryParam("endKey") String endKey) {
    return getDao().findAll("domaine_by_name", startKey, endKey, new IDocMapper<Domaine>() {
      @Override
      public Domaine toEntity(String id, String nom) {
        final Domaine domaine = new Domaine();
        domaine.setId(id);
        domaine.setNom(nom);
        return domaine;
      }

      @Override
      public String toString(Domaine domaine) {
        return null;
      }
    });
  }
}
