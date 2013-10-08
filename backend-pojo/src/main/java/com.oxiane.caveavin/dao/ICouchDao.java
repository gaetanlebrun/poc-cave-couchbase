package com.oxiane.caveavin.dao;

import com.oxiane.caveavin.exception.DocMappingException;
import com.oxiane.caveavin.mapper.IDocMapper;

import java.util.List;

/**
 * User: LEBRUN_G
 * Date: 30/09/13
 * Time: 14:01
 */
public interface ICouchDao<V> {
  // -------------------------- Méthodes publiques --------------------------

  V create(String id, V entite) throws DocMappingException;

  void delete(String id);

  V find(String id) throws DocMappingException;

  List<V> findAll() throws DocMappingException;

  List<V> findAll(String viewName, String startKey, String endKey, IDocMapper<V> mapper) throws DocMappingException;


  V update(String id, V entite) throws DocMappingException;
}
