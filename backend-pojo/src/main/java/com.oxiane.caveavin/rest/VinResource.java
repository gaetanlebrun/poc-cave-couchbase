package com.oxiane.caveavin.rest;

import com.oxiane.caveavin.bean.Vin;

import javax.ws.rs.Path;

@Path("rest/vin")
public class VinResource extends CrudResource<Vin> {
  // -------------------------- Méthodes privées --------------------------

  @Override
  protected String getNormalizedId(Vin entity) {
    return getNormalizedId(entity.getDomaine().getId(), entity.getNom(), entity.getMillesime().toString());
  }
}
