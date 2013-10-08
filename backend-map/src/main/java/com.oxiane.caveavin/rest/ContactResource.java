package com.oxiane.caveavin.rest;

import javax.ws.rs.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Path("rest/message")
public class ContactResource extends CrudResource {
  // -------------------------- Méthodes publiques --------------------------

  @Override
  protected String getNormalizedId(Map<String, Object> map) {
    return getNormalizedId(
            new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()),
            map.get("email").toString());
  }
}
