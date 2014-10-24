package com.oxiane.caveavin.mapper.impl;

import com.oxiane.caveavin.exception.DocMappingException;
import com.oxiane.caveavin.mapper.IDocMapper;

import java.util.Map;

public abstract class AbstractDocMapper implements IDocMapper {
  @Override
  public Map<String, Object> toMap(String id, String doc) {
    if (doc == null) {
      return null;
    }
    try {
      return basicToMap(id, doc);
    } catch (Exception e) {
      throw new DocMappingException(e);
    }
  }

  protected Map<String, Object> basicToMap(String id, String doc) throws Exception {
    throw new UnsupportedOperationException("A rédéfinir dans les sous-classes");
  }

  protected String basicToString(Map<String, Object> map) throws Exception {
    throw new UnsupportedOperationException("A rédéfinir dans les sous-classes");
  }

  @Override
  public String toString(Map<String, Object> map) {
    if (map == null) {
      return null;
    }
    try {
      return basicToString(map);
    } catch (Exception e) {
      throw new DocMappingException(e);
    }
  }
}
