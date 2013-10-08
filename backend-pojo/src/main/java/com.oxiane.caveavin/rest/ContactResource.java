package com.oxiane.caveavin.rest;

import com.oxiane.caveavin.bean.Message;

import javax.ws.rs.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

@Path("rest/message")
public class ContactResource extends CrudResource<Message> {
  // -------------------------- Méthodes publiques --------------------------

  @Override
  protected String getNormalizedId(Message entity) {
    return getNormalizedId(new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()), entity.getEmail());
  }
}
