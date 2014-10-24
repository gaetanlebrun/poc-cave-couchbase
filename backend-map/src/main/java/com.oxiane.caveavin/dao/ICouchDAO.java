package com.oxiane.caveavin.dao;

import com.oxiane.caveavin.exception.DocMappingException;
import com.oxiane.caveavin.mapper.IDocMapper;

import java.util.List;
import java.util.Map;

public interface ICouchDAO {
  Map<String, Object> create(String id, Map<String, Object> map, IDocMapper mapper) throws DocMappingException;

  void delete(String id);

  Map<String, Object> find(String id, IDocMapper mapper) throws DocMappingException;

  List<Map<String, Object>> findAll(String viewName, IDocMapper mapper) throws DocMappingException;

  Map<String, Object> update(String id, Map<String, Object> map, IDocMapper mapper) throws DocMappingException;

  List<Map<String, Object>> findAll(String viewName, String startKey, String endKey, IDocMapper mapper) throws DocMappingException;
}
