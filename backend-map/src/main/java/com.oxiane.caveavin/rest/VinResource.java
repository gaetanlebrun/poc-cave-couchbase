package com.oxiane.caveavin.rest;

import javax.ws.rs.Path;
import java.util.Map;

@Path("rest/vin")
public class VinResource extends CrudResource {
  // -------------------------- Méthodes privées --------------------------

  @Override
  protected String getNormalizedId(Map<String, Object> map) {
    //noinspection unchecked
    final Map<String, Object> domaine = (Map<String, Object>) map.get("domaine");
    return getNormalizedId(
            domaine == null ? null : domaine.get("id").toString(),
            map.get("nom").toString(),
            map.get("millesime").toString());
  }
}
