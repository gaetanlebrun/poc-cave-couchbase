package com.oxiane.caveavin.mapper.impl;

import com.oxiane.caveavin.mapper.IDocMapper;

import java.util.LinkedHashMap;
import java.util.Map;

public class DocWithTypeMapper extends DefaultDocMapper implements IDocMapper {
  // ------------------------------ FIELDS ------------------------------

  private String type;

  // --------------------- GETTER / SETTER METHODS ---------------------

  public void setType(String type) {
    this.type = type;
  }

  // -------------------------- PUBLIC METHODS --------------------------

  @Override
  public Map<String, Object> basicToMap(String id, String doc) throws Exception {
    final Map<String, Object> map = super.basicToMap(id, doc);
    map.remove("type");
    return map;
  }

  @Override
  public String basicToString(Map<String, Object> map) throws Exception {
    Map<String, Object> newMap = new LinkedHashMap<String, Object>(map);
    newMap.put("type", this.type);
    return getMapper().writeValueAsString(newMap);
  }
}
