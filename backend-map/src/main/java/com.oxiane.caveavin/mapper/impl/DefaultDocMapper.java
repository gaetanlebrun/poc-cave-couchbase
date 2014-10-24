package com.oxiane.caveavin.mapper.impl;

import org.codehaus.jackson.map.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.Map;

public class DefaultDocMapper extends AbstractDocMapper {
  // ------------------------------ FIELDS ------------------------------

  private ObjectMapper mapper;

  // --------------------- GETTER / SETTER METHODS ---------------------

  public ObjectMapper getMapper() {
    return mapper;
  }

  public void setMapper(ObjectMapper mapper) {
    this.mapper = mapper;
  }

  // -------------------------- PRIVATE METHODS --------------------------

  @Override
  protected Map<String, Object> basicToMap(String id, String doc) throws Exception {
    //noinspection unchecked
    final Map<String, Object> map = (Map<String, Object>) mapper.readValue(doc, Map.class);
    map.put("id", id);
    return map;
  }

  @Override
  protected String basicToString(Map<String, Object> map) throws Exception {
    return mapper.writeValueAsString(new LinkedHashMap<String, Object>(map));
  }
}
