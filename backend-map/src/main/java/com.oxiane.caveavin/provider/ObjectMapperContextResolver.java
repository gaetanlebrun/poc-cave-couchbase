package com.oxiane.caveavin.provider;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
public class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {
  // ------------------------------ FIELDS ------------------------------

  private ObjectMapper mapper = null;

  // --------------------------- CONSTRUCTORS ---------------------------

  public ObjectMapperContextResolver() {
    super();
    mapper = new ObjectMapper().setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
  }

  // ------------------------ INTERFACE METHODS ------------------------


  // --------------------- Interface ContextResolver ---------------------

  @Override
  public ObjectMapper getContext(Class<?> type) {
    return mapper;
  }
}
