package com.oxiane.caveavin.dao;

import com.oxiane.caveavin.exception.DocMappingException;
import com.oxiane.caveavin.mapper.IDocMapper;

import java.util.List;
import java.util.Map;

/**
 * User: LEBRUN_G
 * Date: 30/09/13
 * Time: 14:01
 */
public interface ICouchDao {
  // -------------------------- Méthodes publiques --------------------------

  Map<String, Object> create(String id, Map<String, Object> entite) throws DocMappingException;

  void delete(String id);

  Map<String, Object> find(String id) throws DocMappingException;

  List<Map<String, Object>> findAll() throws DocMappingException;

  Map<String, Object> update(String id, Map<String, Object> entite) throws DocMappingException;

  List<Map<String, Object>> findAll(String viewName, String startKey, String endKey, IDocMapper mapper) throws DocMappingException;
}
