package com.oxiane.caveavin.helper;

import com.oxiane.caveavin.exception.DocMappingException;

import java.util.List;
import java.util.Map;

public interface ICrudHelper {
  Map<String, Object> create(String id, Map<String, Object> entite) throws DocMappingException;

  Map<String, Object> find(String id) throws DocMappingException;

  List<Map<String, Object>> findAll() throws DocMappingException;

  Map<String, Object> update(String id, Map<String, Object> entite) throws DocMappingException;

  void delete(String id);
}
